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

    public ServerSocket servidor = new ServerSocket(port);
    private Vector<Ball> list = new Vector();

    public BouncingBalls() throws IOException {
        setPreferredSize(new Dimension(width, height));
            EntradaBalls entradas = new EntradaBalls();
    }

    public synchronized void paintChildren(Graphics g) {
        for (Ball ball : list) {
            ball.paint(g);
            ball.move();
        }
    }


    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BouncingBalls());
        frame.setLocation(50, 50);
        frame.pack();
        frame.setVisible(true);



    }
    public class BallThread extends Thread{

        public boolean cont = false;
        public Ball ball;
        public BallThread (Ball ball) {
            this.ball = ball;
            this.start();
        }

        public synchronized void run() {
            cont = true;
            while(cont) {

                System.out.println( Thread.currentThread() + "moving" +ball.getID());
                    ball.move();
                repaint();

                try {
                    Thread.sleep(50);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public class EntradaBalls extends Thread {

        private Socket socket;

        public  EntradaBalls(Socket socket) {
            this.socket = socket;
        }
        public EntradaBalls() {
        this.start();
        }
        public synchronized void run(){
            while(true) {
                try {
                    Socket cliente = servidor.accept();
                    ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                    Ball ball = (Ball) entrada.readObject();
                    list.add(ball);
                    paintChildren(getGraphics());
                    BallThread t = new BallThread(ball);
                    cliente.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}