import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class BouncingBalls extends JPanel {
    public final static int width = 500;
    public final static int height = 400;
    private final static int port = 12345;

    public ServerSocket server = new ServerSocket(port);
    private Vector<Ball> list = new Vector();

    public BouncingBalls() throws IOException {
        setPreferredSize(new Dimension(width, height));
        EntradaBalls entradas = new EntradaBalls();
    }

    public synchronized void paintChildren(Graphics g) {
        for (Ball ball : list) {
            ball.paint(g);
        }
    }

    public synchronized void paintChildren(Graphics g, Ball b) {

        b.paint(g);

    }

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BouncingBalls());
        frame.setLocation(50, 50);
        frame.pack();
        frame.setVisible(true);


    }

    public class BallThread extends Thread {

        public boolean cont = false;
        public Ball ball;
        private ServerSocket servidor;
        private Socket cliente;
        private int trava = 0;


        public BallThread(Ball ball) {
            this.ball = ball;
            this.start();
        }


        public BallThread(ServerSocket server, Socket cliente) {
            this.servidor = server;
            this.cliente = cliente;
            this.start();
        }


        public synchronized void run() {
            try {
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                Ball bola = (Ball) entrada.readObject();
                list.add(bola);
                paintChildren(getGraphics());

                while (this.trava != 30) {
                    bola.move();
                    System.out.println(Thread.currentThread());
                    repaint();
                    Thread.sleep(50);
                    trava++;
                    if(trava==30) {
                        cliente.close();
                        System.out.println("Cliente desligado "+cliente.getPort());
                        trava= 30;
                        list.remove(bola);
                        repaint();

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class EntradaBalls extends Thread {

        public EntradaBalls() {
            this.start();
        }

        public synchronized void run() {
            while (true) {
                try {
                    Socket cliente = server.accept();
                    BallThread t = new BallThread(server, cliente);
                    System.out.println("-----------------");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}