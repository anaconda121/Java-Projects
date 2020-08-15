import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class BasicEnemy extends GameObject {

    Random rand = new Random();
    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 4;
        velY = 4;
    }

    public void tick() {
        // System.out.println("Vel y Before: " + velY);
        // System.out.println("Vel x before: " + velX);

        x += velX;
        y += velY;

        // System.out.println("X: " + x);
        // System.out.println("Y: " + y);

        if (y <= 0 || y >= Game.height - 32) {
            // reversing direction of enemy class if we collide w/ edge of screen
            velY *= -1;
            // System.out.println("Vely: " + velY);
        }

        if (x <= 0 || x >= Game.width - 13) {
            velX *= -1;
            // System.out.println("Velx: " + velX);
        }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail , Color.red, 16, 16, 0.019f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
}