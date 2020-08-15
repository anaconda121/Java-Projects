import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;

public class Menu extends MouseAdapter {
    
    public void mousePressed (MouseEvent e) {

    }

    public void mouseReleased (MouseEvent e) {

    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(200, 100, 100, 64);
    }
}