

public class City {
	private int x;
	private int y;
	
	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int measureDistance(City city) {
		int deltaX = (city.getX() - this.getX());
		int deltaY = (city.getY() - this.getY());
		int distance = (int) Math.sqrt( (deltaX*deltaX) + (deltaY*deltaY) );
		return distance;
	}
	@Override
    public String toString(){
        String s = "x: " + x + ", y:" + y;
        return s;
    }
}
