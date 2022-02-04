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
    public ServerSocket servidor = new ServerSocket(12345);
    private Vector<Ball> list = new Vector();

    public BouncingBalls() throws IOException {
        setPreferredSize(new Dimension(width, height));


            BallThread ballMove = new BallThread();
            ballMove.start();

            EntradaBalls entradas = new EntradaBalls();
            entradas.start();

//
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public synchronized void mouseClicked(MouseEvent e) {
//                Ball ball = new Ball(e.getX(), e.getY());
//                list.add(ball);
//                paintChildren(getGraphics());
//            }
//        });


    }

    public synchronized void paintChildren(Graphics g) {
        for (Ball ball : list) {
            ball.paint(g);
            ball.move();
        }
    }

//    public synchronized void moveBalls(Graphics g, Ball b) {
//        Thread t1 = new Thread(() -> {
//            while (true) {
//                b.paint(g);
//                b.move();
//
//            }
//
//        });
//        t1.start();
//
//
//    }




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

        public synchronized void run() {
            cont = true;
            while(cont) {
                for (Ball b : list ){
                    b.move();
//                    repaint();

                }
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

        public EntradaBalls(Socket socket) {
            this.socket = socket;
        }
        public EntradaBalls() {

        }
        public void run(){
            while(true) {
                try {
                    Socket cliente = servidor.accept();
                    ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                    Ball ball = (Ball) entrada.readObject();
                    list.add(ball);
                    paintChildren(getGraphics());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}