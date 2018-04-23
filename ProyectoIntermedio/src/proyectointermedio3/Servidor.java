package proyectointermedio3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Servidor extends Thread {
    private String nombreCliente;
    public Cliente usuario;
    private BufferedReader entrada;
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    //Método constructor
    public Servidor(Cliente usuario) throws IOException {
        this.usuario = usuario;
        Socket s = usuario.getS();
        entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
        nombreCliente = s.getInetAddress().getCanonicalHostName();
        System.out.println("Conexión aceptada desde " + s.getRemoteSocketAddress());        //Devuelve la conexión de donde se conecto el servidor
        clientes.add(new Cliente(s.getInetAddress().getCanonicalHostName(), s.getInetAddress()));
        clientes.get(clientes.size()).isOn = true;
    }

    //Método para que reciba el flujo de entrada (las cadenas)
    public String recibir() throws IOException {
        String str = entrada.readLine();
        return str;
    }
    
    //Método para cerrar el flujo de entrada
    public void cerrar() throws IOException {
        entrada.close();
    }
    
    public void list(){
    	for(int i = 0; i < clientes.size(); i++) {
        	System.out.println("Cliente " + i + ": " + clientes.get(i).getNombre());
        }
    }
    
  	public void textTo(){
    	list();
    	System.out.print("Ingresa el indice del usuario a quien deseas mandar un mensaje: ");
        
    }
    

    @Override
    public void run() {
        try {
            String cadena = "";
            while (!cadena.equals("salir")) {
            	cadena = recibir();
                cadena = cadena.toUpperCase();
                
                switch (cadena) {
                    case "LIST":
                        list();
                        break;
                        
                    case "SALIR":
                        System.out.println("El usuario " + nombreCliente  + " ha abandonado el chat");
                        break;
                        
                    case "TEXT_TO":
                        
                        break;
                        
                    case "QUIT":
                        clientes.remove(usuario);
                        break;
                        
                    case "TEXT":
                        
                        break;
                        
                    case "SEND_FILE":
                        
                        break;
                        
                    default:
                        System.out.println("Comando no válido!");
                        break;
                }
                //System.out.println(nombreCliente + " dice: " + cadena);  
            }
            
            
        } catch (IOException e) {
            System.out.println("Se cerró la conexión con " + nombreCliente);
        } finally {
            try {
                cerrar();
            } catch (IOException e) {}
        }
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        ss = new ServerSocket(0);
        System.out.println("Servidor aceptando conexiones en el puerto " + ss.getLocalPort());//Con este método veo qué puerto es el que está aceptando la conexión
        while (true) {
            Socket cliente = ss.accept();  //Está a la espera de la conexión
            Servidor hilo = new Servidor(cliente);
            hilo.start();
            
        }
    }

}


