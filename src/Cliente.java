import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente extends Thread {
    private final static int port = 12345;
    private final static String host = "localhost";
    @Override
    public synchronized void run() {
        try {
            Thread.sleep(2000);
            Socket socket = new Socket(host, port);
            if (socket.isConnected()) {
                System.out.println("Client is connect "+"\n"+ "information port connection : "+socket.getLocalPort());
            }
            Ball ball = new Ball(10, 0);
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(ball);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
