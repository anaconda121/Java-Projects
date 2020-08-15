import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keydown = new boolean[4];
    
    public KeyInput(Handler handler) {
        this.handler = handler;

        for (int i = 0; i < keydown.length; i++) {
            keydown[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        //keyMovements(e, 5, -5, -5, 5);

        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
        
            if (tempObject.getId() == ID.Player) {

                //key events for player 1
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                    keydown[0] = true;
                } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    keydown[3] = true;
                } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                    keydown[1] = true;
                } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    keydown[2] = true;
                }
            } 
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    } 

    public void keyReleased (KeyEvent e) {
        //keyMovements(e, 0, 0, 0, 0);
        
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
        
            if (tempObject.getId() == ID.Player) {

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    keydown[0] = false;
                } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    keydown[3] = false;
                } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    keydown[1] = false;
                } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    keydown[2] = false;
                }

                //fine tuning player movements
                if(!keydown[0] && !keydown[1]) {
                    tempObject.setVelY(0);
                }

                if(!keydown[2] && !keydown[3]) {
                    tempObject.setVelX(0);
                }
             } 
        }
    }

    

	public void keyTyped(KeyEvent e) {

    }
}