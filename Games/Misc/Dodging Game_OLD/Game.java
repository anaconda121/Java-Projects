import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int width = 640;
    public static final int height = width / 12 * 9;
    public int frames = 0;
    private Thread thread;
    private boolean running = false;
    Random rand = new Random();
    private HUD hud = new HUD();
    private Handler handler = new Handler();
    private Spawn spawner;
    private Menu menu;

    public enum STATE {
        Menu,
        Game;
    };

    public STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(width, height, "Dodging Game", this);
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        menu = new Menu();

        if (gameState == STATE.Game) {
            handler.addObject(new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler));
            for (int i = 0; i < 2; i++) {
                handler.addObject(new BasicEnemy(rand.nextInt(width), rand.nextInt(height), ID.BasicEnemy, handler));
                // handler.addObject(new Player(200, 200, ID.Player2));
            }
        }   
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        

        // game loop
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();

                delta--;
            } 

            if (running) {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }


    private void tick() {
        handler.tick();

        if (gameState == STATE.Game) {
            hud.tick();
            spawner.tick();
        } else if (gameState == STATE.Menu) {
            menu.tick();
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        handler.render(g);

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu) {
            menu.render(g);
        }
        
        g.dispose();
        bs.show();
    }

    //restricton method- works for all objs
    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String args[]){
        new Game();
    }
}