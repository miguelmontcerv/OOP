import java.net.*;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class ChatClient {

    PrintStream writer;
    Socket client;
    String preguntas[] = {"Dame la Fecha y Hora",
        "Dime un Chiste",
        "Como te Llamas?",
        "Que Edad Tienes?",
        "Cual es tu color favorito?",
        "Cual es tu deporte preferido?",
        "En donde Vives?",
        "En donde Estudias?",
        "¿Eres Inteligente?",
        "Cuentame una historia",
        "¿Cuanto es 2 + 2?",
        "¿De que trata tu pelicula favorita?"
		
    };
	
    String pregunta;

    public ChatClient() {
		JLabel seleccione = new JLabel("Seleccione una opcion o pregunta a responder");
		Font fuente=new Font("Arial", Font.BOLD, 40);
		seleccione.setFont(fuente);
        pregunta = (String) JOptionPane.showInputDialog(null, seleccione,
                "CHAT", JOptionPane.DEFAULT_OPTION, null, preguntas, preguntas[0]);
        try {
            client = new Socket("localhost", 5000);
            writer = new PrintStream(client.getOutputStream());
            writer.println(pregunta);
            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            String cadena = bf.readLine();
            JOptionPane.showMessageDialog(null, cadena);
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        while (true) {
            ChatClient j = new ChatClient();
        }
    }
}
