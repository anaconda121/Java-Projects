import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class EnemyBoss extends GameObject {

    Random rand = new Random();
    private Handler handler;
    private int timer = 100;
    private int timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public void tick() {
        x += velX;
        y += velY;

        //getting boss onto screen
        if (timer <= 0) {
            velY = 0;
        } else {
            timer--;
        }

        //getting boss to move around the screen
        if (timer <= 0) {
            timer2--;
        }

        if (timer2 <= 0) {
            if(velX == 0) {
                velX = 2;
            }

            if (velX > 0) {
                velX += 0.005f;
            } else if (velX < 0) {
                velX -= 0.005f;
            }

            velX = Game.clamp(velX, -10, 10);

            int spawn = rand.nextInt(2);
            if (spawn == 0) {
                handler.addObject(new EnemyBossBullet((int) x + 48, (int) y - 70, ID.BasicEnemy, handler));
            }
        }

        if (x <= 0 || x >= Game.width - 100) {
            velX *= -1;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y - 70, 96, 96);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y - 20, 96, 96);
    }
  
}