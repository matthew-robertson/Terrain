package Terrain;

import Utils.Vector3d;

public class FlatTerrain implements Terrain{
	public double z;
	
	public FlatTerrain(double a){
		z = a;
	}

	@Override
	public double getAltitude(double x, double y) {
		return z;
	}

	@Override
	public Vector3d getColour(double x, double y) {
		return new Vector3d(.5 + .5 * Math.sin(x), .5 + .5 * Math.cos(getAltitude(x, y)), .5 + .5* Math.cos(y));
	}
	
}