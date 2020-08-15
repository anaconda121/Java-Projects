import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class MenuParticle extends GameObject {

    Random rand = new Random();
    private Handler handler;

    private int red = rand.nextInt(256);
    private int green = rand.nextInt(256);
    private int blue = rand.nextInt(256);
    int dir = 0;

    private Color col;

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        dir = rand.nextInt(2);

        if (dir == 0) {
            velX = 2;
            velY = 6;
        } else if (dir == 1){
            velX = 8;
            velY = 6;
        }
  
        col = new Color(red, green, blue);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.height - 32) {
            velY *= -1;
        }

        if (x <= 0 || x >= Game.width - 13) {
            velX *= -1;
        }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail , col, 16, 16, 0.019f, handler));
    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
}