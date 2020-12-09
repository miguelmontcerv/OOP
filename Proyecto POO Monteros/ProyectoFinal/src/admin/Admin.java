/*	Proyecto Final de Programación Orientada a Objetos
	2CM4
	Alumnos: Monteros Cervantes Miguel Angel
    		 Martínez Ortiz Fabiola Yahel
                 Martínez Ramírez Bryan Jair
*/
package admin;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.border.Border;

public class Admin extends JFrame{
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    //private JFrame frameP;
    //private Container main;
    String mensaje;
    
    //Medidas de los cuadros 
    int altoMenu=0,  menuy, altoBoton;
    int anchoCuad=0, menux,  anchoBoton;
    
    //Colores del programa
    //rojo
    int r1=255, r2=83, r3=83;
    //azul
    int a1=11, a2=61, a3=145;
    Border line = BorderFactory.createLineBorder(Color.gray, 1, true);
    
    JPanel menu, main;
    JPanel principal;
    JPanel boton1, boton2;
  
    private MouseListener evt;
    String actualpanel="";
    
    ImageIcon una;
    //Tipo de fuente
    String letra="Arial";
    int Titulo=30;
    int Cuerpo=22;
    int Boton=18;
      
    JLabel logo;
    JTextField nickname;
    JPasswordField pass;
    
    
    public Admin() throws ClassNotFoundException, SQLException{
        menu = new JPanel();
        main = new JPanel();
        
        medidas();
        //imagen();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(0, 0, menux, menuy);
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);
        main.setBounds(0,0,menux,menuy);
        main.setBackground(Color.WHITE);
       
        menu.setBackground(new java.awt.Color(r1, r2, r3));
        menu.setSize(menux, altoMenu);
        menu.setLocation(0,0);
        add(menu);
        menu.setVisible(false);
        
        
        ImageIcon icon2=  new ImageIcon(this.getClass().getResource("Logo.jpg"));
        Image img = icon2.getImage();
        img=img.getScaledInstance((int)(menux*0.2),(int)(menuy*0.2), java.awt.Image.SCALE_SMOOTH);
        Image newimg = img.getScaledInstance((int)(menux*0.2),(int)(menuy*0.2), java.awt.Image.SCALE_SMOOTH);
        logo = new JLabel ();
        ImageIcon newIcon = new ImageIcon(newimg);
        logo.setIcon(newIcon);
        ((JPanel)getContentPane()).setOpaque(false);
        logo.setHorizontalAlignment(JLabel.LEFT);
        logo.setVerticalAlignment(JLabel.CENTER);
        getLayeredPane().add(logo, JLayeredPane.FRAME_CONTENT_LAYER);
        logo.setSize((int)(menux*0.2),(int)(menuy*0.2));
        logo.setLocation((int)(menux*0.40),(int)(menuy*0.1));
        
        main.add(logo);
        
        JLabel nick = new JLabel("Nickname:");
        nick.setFont(new java.awt.Font(letra, Font.BOLD, Cuerpo));
        nick.setSize((int)(menux*0.1),(int)(menuy*0.1));
        nick.setLocation((int)(menux*0.3),(int)(menuy*0.4));
        main.add(nick);
        nick.setHorizontalAlignment(JLabel.CENTER);
        nick.setVerticalAlignment(JLabel.CENTER);
        
        nickname= new JTextField();
        nickname.setSize((int)(menux*0.3),(int)(menuy*0.1));
        nickname.setLocation((int)(menux*0.4),(int)(menuy*0.4));
        main.add(nickname);
        nickname.setEditable(true); 
        
        JLabel pass1 = new JLabel("Password:");
        pass1.setFont(new java.awt.Font(letra, Font.BOLD, Cuerpo));
        pass1.setSize((int)(menux*0.1),(int)(menuy*0.1));
        pass1.setLocation((int)(menux*0.3),(int)(menuy*0.6));
        main.add(pass1);
        pass1.setHorizontalAlignment(JLabel.CENTER);
        pass1.setVerticalAlignment(JLabel.CENTER);
        pass= new JPasswordField();
        pass.setSize((int)(menux*0.3),(int)(menuy*0.1));
        pass.setLocation((int)(menux*0.4),(int)(menuy*0.6));
        main.add(pass);
        pass.setEditable(true);
        
        boton1=new RoundedPanel(40);
        boton1.setOpaque(false);
        boton1.setBackground(new java.awt.Color(a1,a2,a3));
        boton1.setSize((int)(menux*0.2),(int)(menuy*0.1));
        boton1.setLocation((int)(menux*0.40),(int)(menuy*0.8));
        main.add(boton1);
        
        JLabel aceptar = new JLabel("ACEPTAR");
        aceptar.setFont(new java.awt.Font(letra, Font.BOLD, Cuerpo)); 
        aceptar.setForeground(Color.white);
        aceptar.setSize((int)(menux*0.2),(int)(menuy*0.1));
        aceptar.setHorizontalAlignment(JLabel.CENTER);
        aceptar.setVerticalAlignment(JLabel.CENTER);
        boton1.add(aceptar);
        
        aceptar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){ 
                /*try {
                    selecusuario();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                JOptionPane.showMessageDialog(rootPane, "Has accedido correctmanente");
                      main.removeAll();
                      main.validate();
                      main.repaint();
                      new Album();
                
              } 
            });
        
        boton1.setLayout(null);
        add(main);
        main.validate();
        main.repaint();
        main.setLayout(null);
        setVisible(true);
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        new Admin();
    }
    
    public void selecusuario() throws ClassNotFoundException, SQLException{
        String aux="";
        String usu;
        usu = nickname.getText();
        char[] arrayC = pass.getPassword(); 
              String pass1 = new String(arrayC);
              
              if(usu.equals("")||pass1.equals(""))
              {
                  System.out.println("no hay datos");
                  JOptionPane.showMessageDialog(rootPane, "Teclee datos");
              }
              else
              {
                    Connection cone=null;
                    Statement estado=null;
                    ResultSet resul=null;
                    try{
                        //Cambiar usuario y contraseña para el acceso a la BD
                            //Para conectar con SQL utilizar siguientes dos lineas de código eimplementar BD (script viene en archivo)
                            //Class.forName("com.mysql.jdbc.Driver");
                            //cone=DriverManager.getConnection("jdbc:mysql://localhost/usuarios","postgres","");
                            // usuario = rtecla2020     contraseña: poo2cm4
                        Class.forName("org.postgresql.Driver");
                        cone=DriverManager.getConnection("jdbc:postgresql://localhost/usuarios","postgres","");
                        estado=cone.createStatement();
                    }
                    catch(SQLException error){
                       // System.out.println(error.toString());
                        System.out.println("No hay base");
                    }
                    try{
                        estado=(Statement) cone.createStatement();
                        resul=estado.executeQuery("select * from admin where correo='"+usu+"';");
                        if(resul.next()){
                            aux=resul.getString("pass");
                            System.out.println(aux);
                            System.out.println(usu);
                        }
                    }
                    catch(SQLException error){
                        //System.out.println(error.toString());
                        System.out.println("No hay usuario");
                    }
                 cone.close();
                  
                  if (pass1.equals(aux)){
                      JOptionPane.showMessageDialog(rootPane, "Has accedido correctmanente");
                      main.removeAll();
                      main.validate();
                      main.repaint();
                      new Album();
                      
                  }
                  else
                      JOptionPane.showMessageDialog(rootPane, "Usuario no válido");
              }   
    }
    
     public void medidas(){
        //Obtiene el tamaño de la pantalla
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //muestra la informacion por la consola de java
        System.out.println("Tamaño de pantalla: " + d.width + "x" + d.height);
        menux=d.width;
        menuy=d.height;
        altoMenu= (int) (0.07 *menuy);
    } 
     
     class RoundedPanel extends JPanel{
        private int cornerRadius = 15;

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            graphics.setColor(getBackground());
            
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        }
    }
     
     
     class Album extends JFrame implements ActionListener, Runnable{
         private JPanel Superior, Inferior, Botonera, abajo, izq, der;
	//private JPanel panel;
	private JButton botones[], iniciar, atras, adelante, zoom, comentar,eli_com;
	private ImageIcon imagenes [];
        
        private Thread cambio;
        
	Container c = getContentPane();
	Icon icono;
	JLabel foto,comentario[], aux;
	int i, t, convertido, res;
	boolean it;
	String test;
	JTextField texto, come;
	JSlider slider=new JSlider(0,110,15);
        
        
         public Album(){             
            texto = new JTextField();
            come = new JTextField(); 
            
            zoom = new JButton("Zoom"); 
            comentar = new JButton("Comentar");
            eli_com = new JButton("Eliminar Comentario");
            
            comentario = new JLabel[28];
            c.setLayout(new  FlowLayout(FlowLayout.CENTER));
            c.setBounds(0,0, menux, menuy);
            
            Superior = new JPanel();
            Superior.setLayout(new GridLayout(4,7));

            cambio = new Thread(this);
            
            botones=new JButton[28];
            imagenes = new ImageIcon[28];
            imagenes[0]= new ImageIcon(this.getClass().getResource("1.png"));
            imagenes[1]= new ImageIcon(this.getClass().getResource("2.png"));
            imagenes[2]= new ImageIcon(this.getClass().getResource("3.png"));
            imagenes[3]= new ImageIcon(this.getClass().getResource("4.png"));
            imagenes[4]= new ImageIcon(this.getClass().getResource("5.png"));
            imagenes[5]= new ImageIcon(this.getClass().getResource("6.png"));
            imagenes[6]= new ImageIcon(this.getClass().getResource("7.png"));
            imagenes[7]= new ImageIcon(this.getClass().getResource("8.png"));
            imagenes[8]= new ImageIcon(this.getClass().getResource("9.png"));
            imagenes[9]= new ImageIcon(this.getClass().getResource("10.png"));
            imagenes[10]= new ImageIcon(this.getClass().getResource("11.png"));
            imagenes[11]= new ImageIcon(this.getClass().getResource("12.png"));
            imagenes[12]= new ImageIcon(this.getClass().getResource("13.png"));
            imagenes[13]=new ImageIcon(this.getClass().getResource("14.png")); 
            imagenes[14]= new ImageIcon(this.getClass().getResource("15.png"));
            imagenes[15]= new ImageIcon(this.getClass().getResource("16.png"));
            imagenes[16]= new ImageIcon(this.getClass().getResource("17.png"));
            imagenes[17]= new ImageIcon(this.getClass().getResource("18.png"));
            imagenes[18]= new ImageIcon(this.getClass().getResource("19.png"));
            imagenes[19]= new ImageIcon(this.getClass().getResource("20.png"));
            imagenes[20]= new ImageIcon(this.getClass().getResource("21.png"));
            imagenes[21]= new ImageIcon(this.getClass().getResource("22.png"));
            imagenes[22]= new ImageIcon(this.getClass().getResource("23.png"));
            imagenes[23]= new ImageIcon(this.getClass().getResource("24.png"));
            imagenes[24]= new ImageIcon(this.getClass().getResource("25.png"));
            imagenes[25]= new ImageIcon(this.getClass().getResource("26.png"));
            imagenes[26]= new ImageIcon(this.getClass().getResource("27.png"));
            imagenes[27]= new ImageIcon(this.getClass().getResource("28.png"));

            iniciar = new JButton ("Presentacion");
            atras = new JButton ("Anterior");
            adelante = new JButton ("Siguiente");

            izq = new JPanel();
            izq.setLayout(new GridLayout(1,5));
            izq.add(atras);

            aux = new JLabel();

            for(i=0; i<28; i++){
		botones[i] = new JButton("" + (i + 1));
                botones[i].setBounds(0, 0, 75, 60);
                icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(botones[i].getWidth(), botones[i].getHeight(), Image.SCALE_DEFAULT));
                botones[i].setIcon(icono);
                botones[i].setBackground(java.awt.Color.black);
                Superior.add(botones[i]);
                botones[i].addActionListener(this);
                comentario[i] = new JLabel("");
		}
	
	Inferior = new JPanel();
	//Inferior.setBounds(new Rectangle(0, 0, 550, 340));
	Inferior.setLayout(null);
        Inferior.setBackground(Color.white);
	Dimension d = new Dimension(550,340);
	Inferior.setPreferredSize(d);
	//Inferior.setLayout(new FlowLayout(FlowLayout.LEFT));
	Inferior.setVisible(true);
	i = 0;
	icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340,0));
	foto = new JLabel();	
	foto.setIcon(icono);
	Inferior.add(foto);
	
	der = new JPanel();
        der.setLayout(new GridLayout(1,5));
	der.add(adelante);
	
	
	Botonera = new JPanel();
        Botonera.setLayout(new GridLayout(1,5));
        Botonera.setBackground(Color.white);	
	Botonera.add(iniciar);
	atras.addActionListener(this);
	adelante.addActionListener(this);
	iniciar.addActionListener(this);
	
	abajo = new JPanel();
        abajo.setLayout(new GridLayout(1,1));
	abajo.add(aux);
	
	Botonera.add(slider);
	
	slider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            convertido = slider.getValue();
		if (convertido < 10)
               	convertido=9;
            zoomcito();
        }
    });
         
	
	Botonera.add(come);
	Botonera.add(comentar);
	Botonera.add(eli_com);
	comentar.addActionListener(this);
	eli_com.addActionListener(this);
	
	foto.setSize(550,340);
	Botonera.setSize(900,200);
	izq.setSize(350,340);
	der.setSize(350,340);
	abajo.setSize(850,100);
	
	c.add(Superior); c.add(izq); c.add(Inferior);c.add(der);c.add(abajo);c.add(Botonera);
		
	setSize(900, 730);
        setVisible(true);	
    }     
	
	public void actionPerformed(ActionEvent e) {
		
		JButton btn = (JButton)e.getSource();
		
		if(btn==comentar){
                        if(!comentario[i].getText().equals(""))
                            comentario[i].setText(comentario[i].getText()+".     "+ come.getText());
                        else
                            comentario[i].setText(come.getText());
                        come.setText("");
			adelantarfoto();
			atrasarfoto();
		}
		if(btn==eli_com){
			comentario[i].setText("");
			adelantarfoto();
			atrasarfoto();
		}
		if (btn==adelante){
			adelantarfoto();
		}
		if (btn==atras)	{
			atrasarfoto();
		}
		if (btn == iniciar) {
                    if (iniciar.getText().equals("Detener")) {
                        iniciar.setText("Presentacion");
                        t = 10000;
                        iniciar.setEnabled(false);
                    } else {
                        t = 1;
                        if (iniciar.getText().equals("Presentacion")) {
                            iniciar.setText("Detener");
                            cambio.start();
                        }
                    }
                }
            else {
                 for (res = 0; res < 28; res++) {                     
                    slider.setValue(15);
                     test = btn.getText().toString();
                     convertido = Integer.parseInt(test);
                     convertido--;
                     if (convertido == res) {
                         i = res;
                         icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340, 0));
                         foto.setIcon(icono);
                         aux.setBounds(0, 0, 200, 200);
                         aux.setText("                                         " + comentario[i].getText());
                     }
                 }
             }
         }
	
	void adelantarfoto(){
                slider.setValue(15);
		i++;
		if(i==29)
			i=0;
		icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340,0));
		foto.setIcon(icono);
		aux.setText("                                         "+comentario[i].getText());
		
	}
	
	void atrasarfoto(){
            slider.setValue(15);
            i--;
             if (i == -1) {
                 i = 28;
             }
             icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340, 0));
             foto.setIcon(icono);
             aux.setText("                                         " + comentario[i].getText());
         }
	
	public void run() {            
             while (true) {
                 try {
                     adelantarfoto();
                     cambio.sleep(t * 1500);
                 } catch (Exception e) {
                     e.getMessage();
                 }
             }
         }
	
	 void zoomcito() {
             icono = new ImageIcon(imagenes[i].getImage().getScaledInstance((340 * convertido) / 10, (340 * convertido) / 10, 0));
             foto.setIcon(icono);
        }
    }
}     
