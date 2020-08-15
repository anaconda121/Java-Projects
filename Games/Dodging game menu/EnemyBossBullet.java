import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class EnemyBossBullet extends GameObject {

    Random rand = new Random();
    private Handler handler;

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (rand.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public void tick() {
        x += velX * 1.5;
        y += velY * 1.5;

        if (y >= Game.height) {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
}