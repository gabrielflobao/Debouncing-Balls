public class ClienteMulti {
    public static void main(String[] args) {
        for (;;) {
            try {
                Thread.sleep(500);
                Cliente c1 = new Cliente();
                c1.run();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
