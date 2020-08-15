import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random rand = new Random();
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {

        scoreKeep++;

        if (scoreKeep >= 1000) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            //additions to game if user gets to a new level
            if (hud.getLevel() == 2) {
                hud.setHealth(HUD.getHealth() + 20);

                for (int i = 0; i < 2; i++) {
                    handler.addObject(new BasicEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.BasicEnemy, handler));
                }
            } 

            if (hud.getLevel() == 3) {
                hud.setHealth(HUD.getHealth() + 20);

                handler.addObject(new BasicEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.BasicEnemy, handler));
                handler.addObject(new FastEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.FastEnemy, handler));
            }

            if (hud.getLevel() == 4) {
                hud.setHealth(HUD.getHealth() + 20);
                
                for (int i = 0; i < 2; i++) {
                    handler.addObject(new BasicEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.BasicEnemy, handler));
                }
                handler.addObject(new FastEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.FastEnemy, handler));
            }

            if (hud.getLevel() == 5) {
                hud.setHealth(HUD.getHealth() + 20);
                handler.addObject(new BasicEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.BasicEnemy, handler));
                handler.addObject(new FastEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.FastEnemy, handler));
                handler.addObject(new SmartEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.SmartEnemy, handler));
            }

            if (hud.getLevel() == 6) {
                hud.setHealth(HUD.getHealth() + 20);

                for (int i = 0; i < 2; i++) {
                    handler.addObject(new SmartEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.SmartEnemy, handler));
                }
            }

            if (hud.getLevel() == 7) {
                hud.setHealth(HUD.getHealth() + 40);
            }

            if (hud.getLevel() == 8) {
                hud.setHealth(HUD.getHealth() + 40);

                handler.addObject(new FastEnemy(rand.nextInt(Game.width - 50), rand.nextInt(Game.height- 50), ID.FastEnemy, handler));
            }

            if (hud.getLevel() == 10) {
                hud.setHealth(HUD.getHealth() + 100);
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.width / 2 ) - 100, -120, ID.EnemyBoss, handler));
            }
        }
    }
}