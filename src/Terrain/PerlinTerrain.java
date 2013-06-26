package Terrain;

import java.util.Random;

import Utils.Vector3d;

public class PerlinTerrain implements Terrain{
	
	private Random rng;
	double roughness;
	private double[][] terrain;
	
	public PerlinTerrain(int size, double roughness){
		rng = new Random();
		this.roughness = roughness;// / size;
		terrain = new double[size + 1][size + 1];
		int xh = terrain.length - 1;
        int yh = terrain.length - 1;

        // set the corner points
        terrain[0][0] = rng.nextDouble() + 0.5f;
        terrain[0][yh] = rng.nextDouble() + 0.5f;
        terrain[xh][0] = rng.nextDouble() + 0.5f;
        terrain[xh][yh] = rng.nextDouble() + 0.5f;

        // generate the fractal
        generate(0, 0, xh, yh);
		
	}
	
	 private double roughen(double v, int l, int h) {
	        return v + roughness * rng.nextGaussian() * (h - l);
	    }
	
	public void generate(int xl, int yl, int xh, int yh){
		 int xm = (xl + xh) / 2;
	        int ym = (yl + yh) / 2;
	        if ((xl == xm) && (yl == ym)) return;

	        terrain[xm][yl] = 0.5f * (terrain[xl][yl] + terrain[xh][yl]);
	        terrain[xm][yh] = 0.5f * (terrain[xl][yh] + terrain[xh][yh]);
	        terrain[xl][ym] = 0.5f * (terrain[xl][yl] + terrain[xl][yh]);
	        terrain[xh][ym] = 0.5f * (terrain[xh][yl] + terrain[xh][yh]);

	        double v = roughen(0.5f * (terrain[xm][yl] + terrain[xm][yh]), xl + yl, yh
	                + xh);
	        terrain[xm][ym] = v;
	        terrain[xm][yl] = roughen(terrain[xm][yl], xl, xh);
	        terrain[xm][yh] = roughen(terrain[xm][yh], xl, xh);
	        terrain[xl][ym] = roughen(terrain[xl][ym], yl, yh);
	        terrain[xh][ym] = roughen(terrain[xh][ym], yl, yh);

	        generate(xl, yl, xm, ym);
	        generate(xm, yl, xh, ym);
	        generate(xl, ym, xm, yh);
	        generate(xm, ym, xh, yh);
	}
	
	@Override
	public double getAltitude(double x, double y) {
		return terrain[(int)x][(int)y];
	}

	public Vector3d getColour(double x, double y) {
		//return new Vector3d(0.5 + 0.5 * Math.sin(x), 0.5 - 0.5 * Math.cos(y), 0);
		double alt = getAltitude(x, y);
		alt = alt / 460;
		if (alt < .5){
			return blue.Vector3dAdd(green.Vector3dSubtract(blue).scalarMultiple((alt - 0.0) / 0.5));
		}
		else {
			return green.Vector3dAdd(white.Vector3dSubtract(green).scalarMultiple((alt - 0.5) / 0.5));
		}
	}
	
}