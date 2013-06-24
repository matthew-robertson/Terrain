package Terrain;

import Utils.Vector3d;

public interface Terrain{	
	
	public double getAltitude(double x, double y);
	public Vector3d getColour(double x, double y);
	
}