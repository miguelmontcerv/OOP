import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;	

public class topo extends JFrame implements ActionListener,Runnable{
	
	private JPanel panel;
	private JButton botones[], iniciar;
	private JLabel marcador ;
	private ImageIcon imagenes [];
	private int topos;
	private Thread cambio;
	
	
	
	public topo(){
		
	topos=0;
	
	cambio = new Thread(this);
	
	cambio.start();
	
	this.setTitle("Pegale al Topo");
	
	setLayout(new GridLayout(5, 4));
	
	botones=new JButton[20];
	
	imagenes = new ImageIcon[2];
	imagenes[0]= new ImageIcon("2.jpg");
	imagenes[1]= new ImageIcon("1.jpg");
	
	iniciar = new JButton ("Reiniciar");
	marcador= new JLabel ("Puntaje: 0 ");
	
	crear();
	
	this.add(iniciar);
	this.add(marcador);
	this.setVisible(true);
	
	
	int i;
	
	iniciar.addActionListener(this);
	
	for(i=0; i<20; i++)
		botones[i].addActionListener(this);
	
	setSize(450, 350); setVisible(true);
	}
	
	

	
	public  void crear()
	{
		Random r = new Random();
		int i, x1;
		
		for(i=0; i<20; i++)
		{
			x1=Math.abs(r.nextInt()%5);
			
			if (x1==0)
				botones[i]= new JButton ((imagenes[0]));
			else
				botones[i]= new JButton ((imagenes[1]));
			botones[i].setBackground(java.awt.Color.white);
			add(botones[i]);
		}
		
		
	}
	
	
	public void reiniciar()
	{
		Random r = new Random();
		int i,x1;
		
		for(i=0; i<20; i++)
		{
			botones[i].setEnabled(true);
			x1=Math.abs(r.nextInt()%5);
			
				if (x1==0)
					botones[i].setIcon(imagenes[0]);
				else
					botones[i].setIcon(imagenes[1]);
		}
	}
	

	
	public void actionPerformed(ActionEvent e) {
		
		JButton btn = (JButton)e.getSource();
		
		if (btn.getIcon()==imagenes[0])
		{
			btn.setIcon(imagenes[1]);
			btn.setEnabled(false);
			topos++;
		}
		else 
			if (btn==iniciar){
				topos = 0;
				reiniciar();	
			}
		
		
		marcador.setText("Puntaje: " + topos);
	}
	
	public void run ()
	{
		int t;
		Random r = new Random();
		
		t=Math.abs(r.nextInt()%5)+1;
		while (true){
		try 
		{
			cambio.sleep(t*1000);
			reiniciar();
			cambio.start();
		}catch (Exception e){e.getMessage();}
		
		
		}
	}
	
	public static void main (String argv [])
	{
		new topo();
	}
}