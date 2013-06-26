package Terrain;

import Utils.Vector3d;

public interface Terrain{	
	
	final Vector3d blue = new Vector3d(0, 0, 1.0);
	final Vector3d green = new Vector3d(0, 1.0, 0);
	final Vector3d white = new Vector3d(1.0, 1.0, 1.0);
	
	public double getAltitude(double x, double y);
	public Vector3d getColour(double x, double y);
	
}