import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class Calc extends JFrame implements ActionListener{
	JButton btnc;
	JLabel etiq;
	JTextField ani;
	int num;
	
	public Calc(){
		setLayout(null);
		
		btnc = new JButton ("Calcular edad");
    btnc.setBounds(26,60,150,30);
		etiq = new JLabel("0");
    etiq.setBounds(80,110,100,30);
		ani = new JTextField (5);
    ani.setBounds(30,15,150,30);
		num = 0;
		add(btnc); add(etiq); add(ani);
		btnc.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		num = Integer.parseInt(ani.getText());
		num = 2019 - num;
		String resultado = Integer.toString(num);
		etiq.setText(resultado);
	}
	
	public static void main (String args[]){
		Calc calc1 = new Calc();
		calc1.setBounds(150,150,200,200);
		calc1.setVisible(true);
		calc1.setLocationRelativeTo(null);
		calc1.setResizable(false);
	}

}
