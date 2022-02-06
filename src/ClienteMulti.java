public class ClienteMulti {
    public static void main(String[] args) {
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        c1.run();
        c2.run();
        c1.interrupt();

    }
}
