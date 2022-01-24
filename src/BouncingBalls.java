import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class BouncingBalls extends JPanel {
    public final static int width = 500;
    public final static int height = 400;
    public ServerSocket servidor = new ServerSocket(12345);
    private Ball ball = new Ball(128, 127);
    private Vector<Ball> list = new Vector();

    public BouncingBalls() throws IOException {
        setPreferredSize(new Dimension(width, height));
        BallThread ballMove = new BallThread();
        ballMove.start();




        addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mouseClicked(MouseEvent e) {
                Ball ball = new Ball(e.getX(), e.getY());
                list.add(ball);
                paintChildren(getGraphics());
            }
        });


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

    public synchronized void paintBalls() {
        int x_volatil = 74;
        int y_volatil = 0;
        try {
            Socket socket = servidor.accept();

            Ball ball = new Ball(x_volatil * -1, y_volatil = 0);
            list.add(ball);
            x_volatil++;


        } catch (IOException e) {
            e.printStackTrace();
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

        public synchronized void run() {
            cont = true;
            while(cont) {
                for (Ball b : list ){
                    ball.move();
                    repaint();

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


}