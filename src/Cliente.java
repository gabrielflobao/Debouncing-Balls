import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {
    private final static int port = 12345;
    private final static String host = "localhost";
    Scanner ler = new Scanner(System.in);

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(2000);
            Socket socket = new Socket(host, port);
            clienteConectado(socket);
            Ball ball = new Ball(0, 0);
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(ball);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clienteConectado(Socket socket) {
        if (socket.isConnected()) {
            System.out.println("Client is connect " + "\n" + "information port connection : " + socket.getLocalPort());
        }
    }

}
