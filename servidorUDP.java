import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class servidorUDP {

    public static void main(String[] args) throws IOException {

        final int PUERTO = 5000; // Puerto donde el servidor escucha
        byte[] buffer = new byte[1024]; // Buffer para almacenar los datos

        try {
            System.out.println("Iniciando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO); // Crea el socket UDP en el puerto 5000

            while (true) {
                // DatagramPacket para recibir datos del cliente
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                // Recibe el datagrama del cliente
                socketUDP.receive(peticion);
                System.out.println("Recibo la información del cliente");

                // Convierte los datos recibidos en un String
                String mensaje = new String(peticion.getData());
                System.out.println("Mensaje del cliente: " + mensaje);

                // Obtiene la dirección y puerto del cliente
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                // Prepara la respuesta para el cliente
                mensaje = "Hola mundo desde el servidor";
                buffer = mensaje.getBytes();

                // Crea el datagrama de respuesta
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                // Envía la respuesta al cliente
                System.out.println("Envio la respuesta al cliente");
                socketUDP.send(respuesta);
            }

        } catch (SocketException ex) {
            Logger.getLogger(servidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}