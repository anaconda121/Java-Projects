import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    //protected means that x and y can only be accessed by objects that inherit gameobject
    protected float x;
    protected float y;
    protected ID id;
    protected float velX;
    protected float velY;

    public float getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getVelX() {
		return this.velX;
	}

    public void setVelX(int velX){
		this.velX = velX;
    }
    
    public float getVelY() {
		return this.velY;
	}

    public void setVelY(int velY){
		this.velY = velY;
    }
    
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

}