import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
public class camion extends Frame implements ActionListener {
	Button botones[]=new Button[45];
	Button conta;
	int contador;
  	Label etiq;
  
	public camion(){
		setLayout(new GridLayout(12,4));
		contador=0;
		for(int i=1; i<45; i++){
      			add(botones[i]=new Button(""+i));
			botones[i].addActionListener(this);
			botones[i].setBackground(java.awt.Color.green);
		}
		add(etiq = new Label("0"));
		add(conta = new Button("Reservados"));
		conta.addActionListener(this);
    		setSize(320, 300); setVisible(true);    
	}
	public void actionPerformed(ActionEvent e) {
    		if(e.getSource()==conta){
      			String resultado = Integer.toString(contador);
      			etiq.setText(resultado);
    		}
    		else{
      			contador++;
      			Button btn=(Button)e.getSource();	
      			btn.setEnabled(false);
      			btn.setBackground(java.awt.Color.red);
      			contador = contador++;
    		}
	}  
	public static void main(String s[]){ 
		new camion(); 
	}
}
