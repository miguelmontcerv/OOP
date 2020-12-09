import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client implements ActionListener{
	JFrame ventana;
	JTextField pregunta;
	JLabel respuesta;
	JButton enviar;
	public Client(){
		ventana = new JFrame("Chatbot");
		pregunta = new JTextField();
		respuesta = new JLabel();
		enviar = new JButton("Enviar");
		enviar.addActionListener(this);
		ventana.setLayout(new GridLayout(3,1));
		ventana.add(pregunta);
		ventana.add(enviar);
		ventana.add(respuesta);
		ventana.setSize(500,500);
		ventana.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String preg = (String) pregunta.getText();
		String resp;
		try{
            Registry registry = LocateRegistry.getRegistry(null);
            Hello stub = (Hello) registry.lookup("Hello");
			resp = stub.printMsg(preg);
			if(resp.equals(""))
				respuesta.setText(":c");
			else{
				respuesta.setText(resp);
			}

        }catch(Exception ex){
            System.out.println("Nope C");
        }
	}

	public static void main(String[] args){
		new Client();	
	}
}
