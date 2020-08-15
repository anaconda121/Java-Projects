import java.util.Scanner;
public class shape {
    public static void main(String[] args) {
        shape shape = new shape();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many points are there in your shape?");
        int points = sc.nextInt();

        for (int i = 0; i < points; i++) {
            System.out.println("Enter the x and y coordinates for point number " + (i + 1) + " : (separate by space)");
            int x = sc.nextInt();
            int y = sc.nextInt();
            shape.set_num_points(points, x, y);
        }
        sc.close();
    }

    private int num_points;
    private int x;
    private int y;

    public shape(int points, int x_coord, int y_coord) {
        num_points = points;
        x = x_coord;
        y = y_coord;
    }

    public shape() {

    }

    public int getNum_points() {
        return this.num_points;
    }

    public int getX() {
        return this.x;
    }

    public void setX (int x) {
        this.x = x;
    }

    public int getY () {
        return this.y;
    }

    public void setY (int y) {
        this.y = y;
    }

    public point[] set_num_points(int num_points, int x, int y) {
        this.num_points = num_points;
        point []point1 = new point[num_points];

        for (int i = 0; i < num_points; i++) {
            point1[i].setX(x);
            point1[i].setY(y);
        }

        return point1;
    }
}