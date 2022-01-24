import java.io.IOException;

public class ConectaBallCliente {
    public static void main(String[] args) throws IOException {

        ClienteBall cliente = new ClienteBall("localhost",12343,new Ball(0,0));

    }
}
