package Client;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Input.InputHandler;
import Render.Camera;
import Render.Renderer;
import Terrain.PerlinTerrain;
import Terrain.Terrain;
import Terrain.TrigTerrain;
import Utils.Triangle;
import Utils.Vector3d;

/**
 * Main Physics engine, controls the renderer and so on
 * @author Matt
 *
 */
public class Engine{
	public int width;
	public int height;
	public int depth;
	long lastFrame;
	long lastball;
	public int roomsize;
	public Terrain terrain;
	
	InputHandler input;
	Renderer render;
	Camera camera;
	
	public Engine(int width, int height, int depth, int roomsize){
		this.width = width;
		this.height = height;
		this.roomsize = roomsize;
		input = new InputHandler();
		render = new Renderer();	
		this.start(render);		
	}
	
	public void start(Renderer render){
		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Derp 2.0");
			Display.create();
		} 
		catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		this.gameLoop();
	}
	
	/**
	 * Game loop.
	 * Check if there are enough particles
	 * Step each particle forward in time
	 * Check for and resolve collisions
	 */
	public void gameLoop(){
		camera = new Camera(this, new Vector3d(0,0,0), new Vector3d(Math.PI,0,Math.PI), 45.0f, 0.1f, 300f);
		double exaggeration = 0.1;
		int lod = 8;
		int steps = 1 << lod;
		
		int numTriangles = steps * steps * 2;
		Triangle[] triangles = new Triangle[numTriangles];
		int triangle = 0;
		double[][] map = new double[steps + 1][steps + 1];
		Vector3d[][] colours = new Vector3d[steps + 1][steps + 1];
		terrain = new PerlinTerrain(steps, 0.7);
		//terrain = new TrigTerrain(.1, 0.1);
		for (int i = 0; i < steps; i++){
			for (int j = 0; j < steps; j++){
				triangles[triangle ++] = new Triangle(new Vector3d(i, exaggeration * terrain.getAltitude(i, j), j), terrain.getColour(i, j), 
													new Vector3d(i + 1, exaggeration * terrain.getAltitude(i + 1, j), j), terrain.getColour(i + 1, j), 
													new Vector3d(i,exaggeration * terrain.getAltitude(i, j + 1), j + 1), terrain.getColour(i, j + 1));
				triangles[triangle ++] = new Triangle(new Vector3d(i + 1, exaggeration * terrain.getAltitude(i + 1, j), j), terrain.getColour(i + 1, j),
													new Vector3d(i + 1, exaggeration * terrain.getAltitude(i + 1, j + 1), j + 1), terrain.getColour(i + 1, j + 1),
													new Vector3d(i, exaggeration * terrain.getAltitude(i, j + 1), j + 1), terrain.getColour(i, j + 1));
			}
		}
		
		// The actual game loop
		while (!Display.isCloseRequested()){
						
			input.pollInput(this, camera);
			render.update(this, map, colours, triangles, steps, camera);
		}
		Display.destroy();
	}	
}