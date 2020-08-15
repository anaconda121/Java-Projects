import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {

    Handler handler;
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        // edge detection for player
        x = Game.clamp(x, 0, Game.width - 49);
        y = Game.clamp(y, 0, Game.height - 73);

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //checking to see if enemy interacts with player
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    //collision effect, lose health
                    HUD.health -= 1;
                }
            }

            if (tempObject.getId() == ID.EnemyBoss) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    //collision effect for touching boss, lose health
                    HUD.health -= 10;
                }
            }
        }
    }

    public void render(Graphics g) {
        if (id == ID.Player) {

            Graphics2D g2d = (Graphics2D) g;

            g.setColor(Color.red);
            g2d.draw(getBounds());
            
            g.setColor(Color.BLUE);
            g.fillRect((int)x, (int)y, 32, 32);
        }

        /*
         * else if (id == ID.Player2) { g.setColor(Color.BLUE); g.fillRect(x, y, 32,
         * 32); }
         */
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
        
}