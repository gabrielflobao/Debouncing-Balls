import java.awt.*;
import java.io.Serializable;

public class Ball implements Serializable {
    Color color = Color.red;
    int x;
    int y;
    int diameter = 30;
    int dx = 3;
    int dy = 6;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        color = new Color(x % 256, y % 256, (x + y) % 256);
        dx = x % 10 + 1;
        dy = y % 10;

    }

    public synchronized void move() {
//        if (x < 0 || x > BouncingBalls.width) {
//            dx = -dx;
//
//        }
//        if (y < 0 || y == BouncingBalls.height) {
//            dy = -dy;
//            x += dx;
//            y += dy;
//
//        }
        x++;
    }
    public void paint(Graphics paint) {
        paint.setColor(color);
        paint.fillOval(x,y,diameter,diameter);

    }
}
