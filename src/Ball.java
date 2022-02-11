import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

public class Ball implements Serializable {
    private Color color = Color.red;
    private int x = 0;
    private int y = 0;
    private int diameter = 30;
    private int dx = 3;
    private int dy = 6;
    private String id;
    private boolean conectado = true;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        color = new Color((x % 256), (y % 256), (x + y) % 256);
        dx = x % 10 + 1;
        dy = y % 10 + 1;
        criarID();

    }
    public void criarID() {
        this.id = UUID.randomUUID().toString();
    }
    public String getID() {
        return this.id;
    }


    public synchronized void move() {
//        int delta_y = BouncingBalls.height;
        if (x < 0 || x > BouncingBalls.width) {
            dx = -dx;

        }
        if (y < 0 || y == BouncingBalls.height) {
            dy = -dy;
        }
        x += dx;
        y += dy;

    }

    public synchronized void paint(Graphics paint) {
        paint.setColor(color);
        paint.fillOval(x, y, diameter, diameter);

    }
    public void desconectarBola() {
        this.conectado = false;
    }
    public boolean ballIsConnect() {
        return this.conectado;
    }
}
