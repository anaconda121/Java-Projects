import javax.swing.*;
import java.awt.*;

public class graphics extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2D = (Graphics2D) g;

        //making a line
        g2D.setColor(Color.MAGENTA);
        g2D.setStroke(new BasicStroke(20));
        g2D.drawLine(0, 0, 400, 400);

        //drawing polyline
        int [] xPoints = {50, 100, 150, 200, 250, 300, 350};
        int [] yPoints = {50, 100, 4, 200, 77, 300, 350};    
        int numPoints = xPoints.length;
        g2D.setColor(Color.GREEN);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawPolyline(xPoints, yPoints, numPoints);

        //adding text to canvas
        g2D.setFont(new Font("Comic Sans", Font.ITALIC, 25));
        g2D.setColor(Color.RED);
        g2D.drawString("tryhard", 100, 100);
    }
}