import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clienteUDP {

    public static void main(String[] args) {

        final int PUERTO_SERVIDOR = 5000; // Puerto del servidor
        byte[] buffer = new byte[1024]; // Buffer para enviar y recibir datos

        try {
            // Cambia "192.168.1.10" por la IP del servidor en la red local o pública
            InetAddress direccionServidor = InetAddress.getByName("192.168.1.10"); // IP del servidor

            DatagramSocket socketUDP = new DatagramSocket(); // Crea un socket UDP para el cliente
            
            String mensaje = "Hola mundo desde el cliente"; // Mensaje que el cliente va a enviar
            
            buffer = mensaje.getBytes(); // Convierte el mensaje a bytes
            
            // Crea el datagrama con el mensaje, la dirección del servidor y el puerto
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
            
            System.out.println("Envio el datagrama");
            socketUDP.send(pregunta); // Envía el datagrama al servidor
            
            // Prepara un datagrama vacío para recibir la respuesta del servidor
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            
            socketUDP.receive(peticion); // Recibe la respuesta del servidor
            System.out.println("Recibo la respuesta del servidor");
            
            // Convierte los datos recibidos en un String
            mensaje = new String(peticion.getData());
            System.out.println("Mensaje del servidor: " + mensaje);
            
            socketUDP.close(); // Cierra el socket UDP
            
        } catch (SocketException ex) {
            Logger.getLogger(clienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(clienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(clienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}