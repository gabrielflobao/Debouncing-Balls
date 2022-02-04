import java.util.concurrent.ConcurrentLinkedDeque;

public class Application {
    public static void main(String[] args) {
        Cliente c = new Cliente();
        Cliente c1 = new Cliente();
        c.start();
        c1.start();
    }
}
