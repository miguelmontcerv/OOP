import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
public class camion extends JFrame implements ActionListener {
	
	JButton botones[]=new JButton[45];
	Button conta;
	int contador;
  	Label etiq;
	JLabel todos;
	String test, mostra, aux;
	JPanel Superior, Inferior;
	Container c = getContentPane();
  
	public camion(){
		
		c.setLayout(new GridLayout(2,1));
		
		Superior = new JPanel();
        Superior.setLayout(new GridLayout(12,4));
		contador=0;
		for(int i=1; i<45; i++){
      			Superior.add(botones[i]=new JButton(""+i));
			botones[i].addActionListener(this);
			botones[i].setBackground(java.awt.Color.green);
		}
		
		Superior.add(conta = new Button("Reservados"));
		conta.addActionListener(this);
		Superior.add(etiq = new Label("0"));
	
		Inferior = new JPanel();
		todos = new JLabel("Ocupados");
		Font auxFont=todos.getFont(); 
		todos.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 12));
		Inferior.add(todos);
		mostra = " ";
		aux = " ";
		
		c.add(Superior); c.add(Inferior);
		
		Superior.setSize(350,400);
		Inferior.setSize(350,40);
		
		setSize(350, 450); setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
    		if(e.getSource()==conta){
      			String resultado = Integer.toString(contador);
      			etiq.setText(resultado);
				mostra = mostra + aux;
				todos.setText(" " + mostra);
				aux = " "; test = " ";
    		}
    		else{
      			contador++;
      			JButton btn=(JButton)e.getSource();	
      			btn.setEnabled(false);
      			btn.setBackground(java.awt.Color.red);
				test = btn.getText().toString();
				aux = test + "," + aux;
      			contador = contador++;
    		}
	}  
		
	public static void main(String s[]){ 
		new camion(); 
	}
}
