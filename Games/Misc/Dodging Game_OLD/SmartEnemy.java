import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class SmartEnemy extends GameObject {

	Random rand = new Random();
    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                player = handler.object.get(i);
            }
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        //smart enemy distance
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;

        //distance formula
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY())); 

        velX = (int) ((-1.0 / distance) * diffX);
        velY = (int) ((-1.0 / distance) * diffY);


        if (y <= 0 || y >= Game.height - 32) {
            // reversing direction of enemy class if we collide w/ edge of screen
            velY *= -1;
            // System.out.println("Vely: " + velY);
        }

        if (x <= 0 || x >= Game.width - 13) {
            velX *= -1;
            // System.out.println("Velx: " + velX);
        }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail , Color.GREEN, 16, 16, 0.019f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
}