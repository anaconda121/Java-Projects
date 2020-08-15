public class point {
	private int x;
	private int y;

	public point(int start_x, int start_y){
		x = start_x;
		y = start_y;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static double calc_distance(point point1, point point2) {
		double x = point2.getX() - point1.getX();
		double y = point2.getY() - point1.getY();
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public static void main(String[] args){
		point point1 = new point(3, 4);
		point point2 = new point(6, 8);

		for( int x = 0; x<3; x++) {
			for(int y = 0; y < 2; y++) {			  
				System.out.println(x + ", "+y);
			}	
		}

		int a = 5;
		int b = 7;
		int temp = a;
		a = b;
		b = temp;
		System.out.println(a + ", " + b);
	}

	public static void swap(int a, int b) {
		
	}
}