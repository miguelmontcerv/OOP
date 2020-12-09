
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;
import javax.swing.*;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Font;

public class ChatServer {

    ServerSocket ss;
    Socket c;
    PrintStream writer;
    Date fecha = new Date();
    DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    HashMap<String, String> lista;
    String chistes[] = {
        "Doctor, tengo todo el cuerpo cubierto de pelo¿Qué padezco?” Padece uzté un ozito.",
        "Mamá, en el cole me llaman despistado.” Niño, que esta no es tu casa.",
        "¿Pero qué haces hablando con un tenis.” Porque dice “CONVERSE”.",
        "Parece que su tos está mejor.?” Sí, estuve practicando toda la noche.",
        "Si los zombies se deshacen con el paso del tiempo ¿zombiodegradables?"
    };

    public ChatServer() {
        lista = new HashMap<>();
        lista.put("Dame la Fecha y Hora", "Hora y fecha: " + hourdateFormat.format(fecha));
        lista.put("Dime un Chiste", "");
        lista.put("Como te Llamas?", "Soy solo un chat, no tengo nombre");
        lista.put("Que Edad Tienes?", "veinti, treinta, no espera, me crearon hoy.");
        lista.put("Cual es tu color favorito?", "El color de sus ojos es mi favorito");
        lista.put("Cual es tu deporte preferido?", "Entrar al deforma es mi deporte favorito");
        lista.put("En donde Vives?", "En tu computadora");
        lista.put("En donde Estudias?", "En el Internet");
        lista.put("¿Eres inteligente?", "Solo a veces");
        lista.put("Cuentame una historia", "Erase una vez habia un raton. Se murio. Fin.");
        lista.put("¿Cuanto es 2 + 2?", "Segun mis calculos super avanzados la respuesta a tu pregunta es 4.");
        lista.put("¿De que trata tu pelicula favorita?", "De un niño que se va solo a nueva york en navidad(Mi pobre angelito 2)");
        try {
            ss = new ServerSocket(5000);
            try {
                //System.out.print("Conexion Establecida ");
                while (true) {
                    String respuesta;
					//Font fuente = new Font("Calibri", 0, 20);
					//respuesta.setFont(fuente);
                    c = ss.accept();
                    //System.out.print("Conexion Establecida ");
                    InputStreamReader in = new InputStreamReader(c.getInputStream());
                    BufferedReader bf = new BufferedReader(in);
                    String preguntac = bf.readLine();
                    if ("Dime un Chiste".equals(preguntac)) {
                        int random = (int) (Math.random() * 4);
                        respuesta = chistes[random];
                        writer = new PrintStream(c.getOutputStream());
                        writer.println(respuesta);
                    } else {
                        respuesta = lista.get(preguntac);
                        writer = new PrintStream(c.getOutputStream());
                        writer.println(respuesta);
                    }
                }
            } catch (IOException e) {
                ss.close();
                System.err.println(e);
            }
            ss.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        ChatServer s = new ChatServer();
    }
}
