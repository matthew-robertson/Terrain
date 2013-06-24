package Terrain;

import Utils.Vector3d;

public class DiamondSquareTerrain implements Terrain{
	public DiamondSquareTerrain(){
		
	}
	@Override
	public double getAltitude(double x, double y) {
		return 0;
	}

	@Override
	public Vector3d getColour(double x, double y) {
		return null;
	}
	
}