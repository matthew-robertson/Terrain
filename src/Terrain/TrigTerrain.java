package Terrain;

import Utils.Vector3d;

public class TrigTerrain implements Terrain{
	public double alpha;
	public double beta;
	
	public TrigTerrain(double a, double b){
		alpha = a;
		beta = b;
	}
	@Override
	public double getAltitude(double x, double y) {
		return (.5 +   Math.cos(x * alpha) + Math.sin(y * beta));
	}

	@Override
	public Vector3d getColour(double x, double y) {
		return new Vector3d(0.5 + 0.5 * Math.sin(getAltitude(x, y)), 0.5 + 0.5 * Math.cos(getAltitude(x, y)), 0);
	}
	
}