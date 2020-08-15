import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    
    //enemy positions
    private int[] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,
    750, 775, 800,825,850};
    private int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    //eneny images
    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    private int score = 0;

    //snake position
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    private int moves = 0;

    //snake direction
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;

    //mouth positions
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int lengthOfSnake = 3;

    private Timer timer;
    private int delay = 100;

    private ImageIcon titleImage;
    private ImageIcon snakeImage;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); //disables tap and shift tab
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {

        if (moves == 0) {
            //if moves == 0, game has just started, define default snake position
             snakexlength[2] = 50;
             snakexlength[1] = 75;
             snakexlength[0] = 100;

             snakeylength[2] = 100;
             snakeylength[1] = 100;
             snakeylength[0] = 100;
        }

        //draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(20, 10, 851, 55);

        //draw title image
        titleImage = new ImageIcon("assets/snaketitle.jpg");
        titleImage.paintIcon(this, g, 21, 11);

        //draw border
        g.setColor(Color.white);
        g.drawRect(20, 74, 851, 577);

        //drawing background
        g.setColor(Color.BLACK);
        g.fillRect(20, 74, 851, 577);

        //drawing snake
        rightmouth = new ImageIcon("assets/rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

		for (int a = 0; a < lengthOfSnake; a++) {
            //a should be 0 because first part of snake is head, if a != 0, then we need to draw body
            if (a == 0 && right) {
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            if (a == 0 && left) {
                leftmouth = new ImageIcon("assets/leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            if (a == 0 && up) {
                upmouth = new ImageIcon("assets/upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            if (a == 0 && down) {
                downmouth = new ImageIcon("assets/downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            if (a != 0) {
                snakeImage = new ImageIcon("assets/snakeimage.png");
                snakeImage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
        }

        enemyimage = new ImageIcon("assets/enemy.png");

        //checking collision with snake head
        if ((enemyxpos[xpos] == snakexlength[0]) && (enemyypos[ypos] == snakeylength[0])) {
            lengthOfSnake++;
            score += 10;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        //paint enemy image
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + score, 780, 30);

        //length of snake
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Snake Length: " + lengthOfSnake, 750, 50);

        //checking for collision with head of snake
        for (int x = 1; x < lengthOfSnake; x++) {
            if(snakexlength[x] == snakexlength[0] && snakeylength[x] == snakeylength[0]){
                //reseting game
                right = false;
                left = false;
                up = false;
                down= false;
                lengthOfSnake = 3;

                snakexlength[2] = 50;
                snakexlength[1] = 75;
                snakexlength[0] = 100;
   
                snakeylength[2] = 100;
                snakeylength[1] = 100;
                snakeylength[0] = 100;

                score = 0;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Game Over! You have lost! Press enter to restart!!", 250, 350);
                break;
            } 
        }
        g.dispose();

    }

    public void snakePositions(boolean l, boolean r, boolean u, boolean d, boolean current, boolean opCurrent) {
        moves++;
        left = l;
        right = r;
        up = u;
        down = d; 
        
        boolean nameCurrent = current;
        boolean nameOpCurrent = opCurrent;
        
        if (!nameOpCurrent) {
            nameCurrent = true;
        } else if (!nameCurrent) {
            nameOpCurrent = true;
            nameCurrent = false;
        }
    }

	public void keyPressed(KeyEvent e) {

        //detecting user input
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            snakePositions(false, true, false, false, true , false);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            snakePositions(true, false, false, false, true , false);
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //restart game
            moves = 0;
            score = 0;
            lengthOfSnake = 3;
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            snakePositions(false, false, true, false, true , false);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            snakePositions(false, false, false, true, true , false);
        }
    }

    public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (right) {
            for (int r = lengthOfSnake - 1; r >= 0; r--) {
                //transferring coordinates of snake part in front of rth element
                snakeylength[r+1] = snakeylength[r];
            }

            for(int k= lengthOfSnake; k >= 0; k--) {
                if (k == 0) {
                    //transferring coordinates of snake part in front of kth element
                    snakexlength[k] = snakexlength[k] + 25;
                } else {
                    //transferring coordinates of snake part in front of kth element
                    snakexlength[k] = snakexlength[k - 1];
                }

                //handle edge of right part of screen
                if (snakexlength[k] >= 851) {
                    snakexlength[k] = 25;
                }
            }

            repaint();
        }

        if (left) {
            for (int r = lengthOfSnake - 1; r >= 0; r--) {
                //transferring coordinates of snake part in front of rth element
                snakeylength[r+1] = snakeylength[r];
            }

            for(int k= lengthOfSnake; k >= 0; k--) {
                if (k == 0) {
                    //transferring coordinates of snake part in front of kth element
                    snakexlength[k] = snakexlength[k] - 25;
                } else {
                    //transferring coordinates of snake part in front of kth element
                    snakexlength[k] = snakexlength[k - 1];
                }

                //handle edge of right part of screen
                if (snakexlength[k] <= 20) {
                    snakexlength[k] = 850;
                }
            }

            repaint();
        }

        if (up) {
            for (int r = lengthOfSnake - 1; r >= 0; r--) {
                //transferring coordinates of snake part in front of rth element
                snakexlength[r+1] = snakexlength[r];
            }

            for(int k = lengthOfSnake; k >= 0; k--) {
                if (k == 0) {
                    //transferring coordinates of snake part in front of kth element
                    snakeylength[k] = snakeylength[k] - 25;
                } else {
                    //transferring coordinates of snake part in front of kth element
                    snakeylength[k] = snakeylength[k - 1];
                }

                //handle edge of right part of screen
                if (snakeylength[k] <= 50) {
                    snakeylength[k] = 625;
                }
            }

            repaint();
        }

        if (down) {
            for (int r = lengthOfSnake - 1; r >= 0; r--) {
                //transferring coordinates of snake part in front of rth element
                snakexlength[r+1] = snakexlength[r];
            }

            for(int k = lengthOfSnake; k >= 0; k--) {
                if (k == 0) {
                    //transferring coordinates of snake part in front of kth element
                    snakeylength[k] = snakeylength[k] + 25;
                } else {
                    //transferring coordinates of snake part in front of kth element
                    snakeylength[k] = snakeylength[k - 1];
                }

                //handle edge of right part of screen
                if (snakeylength[k] >= 650) {
                    snakeylength[k] = 75;
                }
            }

            repaint();
        }
    }
}