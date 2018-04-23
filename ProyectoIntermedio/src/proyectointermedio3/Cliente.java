/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointermedio3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.net.InetAddress;



public class Cliente {
    private String nombre;
    private PrintStream ps;
    private InetAddress host;
    private int port;       //Porque con 0 toma un valor aleatorio
    private String add = " ";
    public Boolean isOn = false;
    
    public Cliente() {
        
    }
    
    public Cliente(String nombre, InetAddress host){
        this.nombre = nombre;
        this.host = host;
    }
    
    public String getNombre(){
    	return nombre;
    }
    
    public void setNombre(String nombre){
    	this.nombre = nombre;
    }
    
    public void setAdd(String add) {
        this.add = add;
    }
    
    public String getAdd() {
        return add;
    }
    
    public void setHost(InetAddress host) {
        this.host = host;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public void conectar() throws IOException {
        Socket s = new Socket(host, port);
        ps = new PrintStream(s.getOutputStream());
    }
    
    public void enviar(String mensaje) throws IOException {
        ps.println(mensaje);
    }
    
    public void cerrar() throws IOException {
        ps.close();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        
        Cliente cliente = new Cliente();
        
        System.out.print("Host: ");//Para ver a que dir ip se va a conectar
        String x = teclado.readLine();
        cliente.setHost(InetAddress.getByName(x));
        
        System.out.print("Puerto: ");
        cliente.setPort(Integer.parseInt(teclado.readLine()));
        
        while (!cliente.getAdd().equals("ADD")) {
            System.out.print("Comando: ");
         cliente.setAdd(teclado.readLine());
        
            if (cliente.getAdd().equals("ADD")) {
                cliente.conectar();
                System.out.println("Ha sido agregado al chat");
            } else {
                System.out.println("Debe entrar al chat");
            }
        }
        
        
        
        String cadena = "";
        
        while (!cadena.equals("salir")) {
            System.out.print("Comando: ");
            cadena = teclado.readLine();
            
            switch (cadena) {
                case "LIST":
                    System.out.println("Para listar");
                    for (int i = 0; i <= Servidor.clientes.size(); i++) {
                        System.out.println("Cliente " + i + ": " + Servidor.clientes.get(i));
                    }
                    break;
            }
            
            cliente.enviar(cadena);
        }
        
        cliente.cerrar();
        
    }
    
}



/*public class Cliente {
    public String nombre;
    public String host;
    public int port;
    public Boolean isOn = false;
    private PrintStream ps;
    
    public Cliente() {
        
    }
    
   
    public Cliente(String nombre, String host){
        this.nombre = nombre;
        //this.host = host;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public void conectar() throws IOException {
        Socket s = new Socket(host, port);
        ps = new PrintStream(s.getOutputStream());
    }
    
    public void enviar(String mensaje) throws IOException {
        ps.println(mensaje);
    }
    
    public void cerrar() throws IOException {
        ps.close();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        
        Cliente cliente = new Cliente();
        
        System.out.print("Host: ");//Para ver a que dir ip se va a conectar
        cliente.setHost(teclado.readLine());
        
        System.out.print("Puerto: ");
        cliente.setPort(Integer.parseInt(teclado.readLine()));
        
        cliente.conectar();
        
        String cadena = "";
        
        while (!cadena.equals("salir")) {
            System.out.print("Mensaje: ");
            cadena = teclado.readLine();
            
            cliente.enviar(cadena);
        }
        
        cliente.cerrar();
        
    }
    
}*/
