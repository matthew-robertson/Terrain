package Terrain;

import java.util.Random;

import Utils.Vector3d;

public class DiamondSquareTerrain implements Terrain{
	private Double[][] terrain;
	private double roughness, min, max;
	private int divisions;
	private Random rng;
	private Vector3d blue = new Vector3d(0, 0, 1.0);
	private Vector3d green = new Vector3d(0, 1.0, 0);
	private Vector3d white = new Vector3d(1.0, 1.0, 1.0);
	
	public DiamondSquareTerrain(int lod, double roughness){
		this.roughness = roughness;
		this.divisions = 1 << lod;
		terrain = new Double[divisions + 1][divisions + 1];
		rng = new Random();
		for (int i = 0; i < divisions; i++){
			for (int j = 0; j < divisions; j++){
				terrain[i][j] = 0.0;
			}
		}
		terrain[0][0] = rnd();
		terrain[0][divisions] = rnd();
		terrain[divisions][0] = rnd();
		terrain[divisions][divisions] = rnd();
		double rough = roughness;
		
		for (int i = 0; i < lod; i++){
			int q = 1 << i, r = 1 << (lod - i), s = r >> 1;
			for (int j = 0; j < divisions; j += r){
				for (int k = 0; k < divisions; k += r){
					diamond(j, k, r, rough);
				}
			}
			if (s > 0){
				for(int j = 0; j <= divisions; j += s){
					for (int k = (j + s) % r; k <= divisions; k += r){
						square(j - s, k - s, r, rough);
					}
				}
			}
			rough *= roughness;
		}
		
		min = max = terrain[0][0];
		for (int i = 0; i <= divisions; i++){
			for (int j = 0; j <= divisions; j++){
				if (terrain[i][j] <= min) min = terrain[i][j];
				else if (terrain[i][j] >= max) max = terrain[i][j];
			}
		}
		
	}
	
	private void diamond(int x, int y, int side, double scale){
		if (side > 1){
			int half = side / 2;
			double avg = (terrain[x][y] + terrain[x + side][y] + terrain[x + side][y + side] + terrain[x][y + side]) * .25;
			terrain[x + half][y = half] = avg + rnd() * scale;
		}
	}
	
	private void square(int x, int y, int side, double scale){
		int half = side / 2;
		double avg = 0.0;
		double sum = 0.0;
		if (x >= 0){
			avg += terrain[x][y + half];
			sum += 1.0;
		}
		if (y >= 0){
			avg += terrain[x + half][y];
			sum += 1.0;
		}
		if (x + side <= divisions){
			avg += terrain[x + side][y + half];
			sum += 1.0;
		}
		if (y + side <= divisions){
			avg += terrain[x + half][y + side];
			sum += 1.0;
		}
		terrain[x + half][y + half] = avg / sum + rnd() * scale;
	}
	
	private double rnd(){
		return 2. * rng.nextDouble() - 1.0;
	}
	
	@Override
	public double getAltitude(double x, double y) {
		double alt = terrain[(int) (x * divisions)][(int) (y * divisions)];
		return (alt - min) / (max - min);
	}

	@Override
	public Vector3d getColour(double x, double y) {
		double alt = getAltitude(x, y);
		if (alt < .5){
			return blue.Vector3dAdd(green.Vector3dSubtract(blue).scalarMultiple((alt - 0.0) / 0.5));
		}
		else {
			return green.Vector3dAdd(white.Vector3dSubtract(green).scalarMultiple((alt - 0.5) / 0.5));
		}
	}
	
}