package Utils;
public class Vector3d{
	public double x;
	public double y;
	public double z;
	
	public Vector3d(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double dotProduct(Vector3d vec){
		double sum = this.x * vec.x + this.y * vec.y + this.z * vec.z;
		return sum;
	}
	
	public Vector3d Vector3dSubtract(Vector3d vec){
		return (new Vector3d(x - vec.x, y - vec.y, z - vec.z));
	}
	
	public Vector3d Vector3dAdd(Vector3d vec){
		return (new Vector3d(x + vec.x, y + vec.y, z + vec.z));
	}
	
	public double lengthSquared(){
		return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z,  2);
	}
	
	public double length(){
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z,  2));
	}
	
	public Vector3d scalarMultiple(float scalar){
		return (new Vector3d(x * scalar, y * scalar, z * scalar));
	}
	
	public String toString()
	{
		return "<" + x + "," + y + "," + z + ">";
	}
}