package Utils;

public class Triangle{
	public Vector3d[] points = new Vector3d[3];
	public Vector3d normal;
	public Vector3d[] colour = new Vector3d[3];
	
	public Triangle(Vector3d p0, Vector3d colour0, Vector3d p1, Vector3d colour1, Vector3d p2, Vector3d colour2){
		points[0] = p0;
		colour[0] = colour0;
		points[1] = p1;
		colour[1] = colour1;
		points[2] = p2;
		colour[2] = colour2;
	}
}