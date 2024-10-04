import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class servidor {

    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket sc = null;
        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            // Escuchar continuamente por nuevas conexiones
            while (true) {
                sc = servidor.accept();
                System.out.println("Cliente conectado");

                // Crear un nuevo hilo para manejar al cliente
                ClienteHandler clienteHandler = new ClienteHandler(sc);
                new Thread(clienteHandler).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

// Clase que manejará la comunicación con cada cliente
class ClienteHandler implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            // Leer mensaje del cliente
            String mensaje = in.readUTF();
            System.out.println("Mensaje del cliente: " + mensaje);

            // Enviar respuesta al cliente
            out.writeUTF("Hola desde el servidor en la laptop de Iram");


            // Cerrar conexión
            socket.close();
            System.out.println("Cliente desconectado");

        } catch (IOException ex) {
            Logger.getLogger(ClienteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}