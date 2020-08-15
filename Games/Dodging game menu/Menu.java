import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler = new Handler();
    public static final int width = 640;
    public static final int height = width / 12 * 9;
    private Random rand = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }
    
    public void mousePressed (MouseEvent e) {
        //storing x and y pos of where mouse clicked
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            //checking to see if user clicked on play buttton
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler));

                handler.clearEnemys();

                for (int i = 0; i < 2; i++) {
                    handler.addObject(new BasicEnemy(rand.nextInt(width), rand.nextInt(height), ID.BasicEnemy, handler));
                }
            }

            //checking to see if user clicked on help buttton
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
            }

            if (game.gameState == Game.STATE.Help) {
                if (mouseOver(mx, my, 210, 350, 200, 64)) {
                    game.gameState = Game.STATE.Menu;
                    return;
                }
            }

            //checking to see if user clicked on quit buttton
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        
    }

    public void mouseReleased (MouseEvent e) {

    }

    //checks if mouse is in range of certain boxes
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {
    }

    public void render(Graphics g) {

        if (game.gameState == Game.STATE.Menu) {
            //menu system bones
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Dodging Game", 135, 50);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);

        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 15);

            //help heading
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 50);

            //help content
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Use WASD keys / arrow keys to move your player.", 30, 150);
            g.drawString("Try to dodge the heads of the enemies flying across the screen to win!", 30, 170);

            //back button
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 240, 400);
        }

    }
}
