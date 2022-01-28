import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",12345);
        Ball ball = new Ball(0,0);
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        saida.writeObject(ball);

    }
}
