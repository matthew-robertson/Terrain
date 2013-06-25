package Client;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Input.InputHandler;
import Render.Camera;
import Render.Renderer;
import Terrain.DiamondSquareTerrain;
import Terrain.Terrain;
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
		camera = new Camera(this, new Vector3d(0,0,0), new Vector3d(Math.PI,0,Math.PI), 45.0f, 0.1f, 200f);
		double exaggeration = 15;
		int lod = 10;
		int steps = 1 << lod;
		Vector3d[][] map = new Vector3d[steps + 1][steps + 1];
		Vector3d[][] colours = new Vector3d[steps + 1][steps + 1];
		terrain = new DiamondSquareTerrain(lod, 0.75);
		for (int i = 0; i < steps; i++){
			for (int j = 0; j < steps; j++){
				double x = 1.0 * i / steps, z = 1.0 * j / steps;
				double altitude = terrain.getAltitude(x, z);
				map[i][j] = new Vector3d(x, altitude * exaggeration, z);
				colours[i][j] = terrain.getColour(x, z);
			}
		}
		
		// The actual game loop
		while (!Display.isCloseRequested()){
			//If there aren't too many particles to kill your computer, and it's not too soon
			
			
			input.pollInput(this, camera);
			render.update(this, map, colours, camera);
		}
		Display.destroy();
	}	
}