import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class HUD {

    public static int health = 100;
    private int greenValue = 255;
    private int healthColor = 0;
    private int score = 0;
    private int level = 1;

    
    public static int getHealth() {
        return health;
    }

    public void setHealth( int health) {
		HUD.health = health;
    }
    
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void tick() {
        health = (int) Game.clamp(health, 0, 100);

        //params for color changing health bar
        greenValue = (int) Game.clamp(greenValue, 0, 255);
        greenValue = health * 2;

        //score and level changes
        score++;

        //params for color changing text for health
        healthColor = (int) Game.clamp(healthColor, 0, 100);
        healthColor = health * 2;
    }

    public void render(Graphics g) {
        //drawing health bar
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);

        //drawing health bar
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, health * 2, 32);
        
        //drawing health percentaged
        if (health >= 50){
            g.setColor(Color.black);
        } else if (health < 50) {
            g.setColor(Color.orange);
        } 
        
        g.drawString("Health: " + health + "%", 25, 30);
        g.setFont(new Font("TimesRoman", Font.BOLD, 24)); 

        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.setFont(new Font("TimesRoman", Font.BOLD, 18)); 
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }
    
}