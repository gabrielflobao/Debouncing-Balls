import java.io.IOException;
import java.net.*;

public class ClienteBall extends Socket {
    private Ball ball;

    public ClienteBall(Ball ball) {
        this.ball = ball;
    }

    public ClienteBall(Proxy proxy, Ball ball) {
        super(proxy);
        this.ball = ball;
    }

    public ClienteBall(SocketImpl impl, Ball ball) throws SocketException {
        super(impl);
        this.ball = ball;
    }

    public ClienteBall(String host, int port, Ball ball) throws IOException {
        super(host, port);
        this.ball = ball;
    }

    public ClienteBall(InetAddress address, int port, Ball ball) throws IOException {
        super(address, port);
        this.ball = ball;
    }

    public ClienteBall(String host, int port, InetAddress localAddr, int localPort, Ball ball) throws IOException {
        super(host, port, localAddr, localPort);
        this.ball = ball;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void avisoConectado() {
        System.out.println("Cliente - "+getLocalAddress() + " - "+ getLocalPort() + ", está conectado" );
    }
}
