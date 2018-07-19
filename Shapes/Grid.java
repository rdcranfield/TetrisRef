package Shapes;

public class Grid implements IGrid {
	int[][] grid;
	int width, height;
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		grid = new int[width][height];   
	}
	@Override
	public int[][] getGrid() {
		return grid;
	}
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public int getHeight() {
		return height;
	}

}
