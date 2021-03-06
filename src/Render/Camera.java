package Render;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import Client.Engine;
import Utils.Vector3d;

public class Camera{
	private Vector3d position;
	private Vector3d rotation;
	public Vector3d lightPosition;
	
	private float fov;
	private float aspect;
	private float near;
	private float far;
	private int maxAngle;
	
	public Camera(Engine engine, Vector3d position, Vector3d rotation, float fov, float near, float far){
		this.position = position;
		this.rotation = rotation;
		this.fov = fov;
		this.near = near;
		lightPosition = new Vector3d(0, 0, 0);
		this.far = far;
		this.maxAngle = 80;
		aspect = engine.width / engine.height;
		initProjection();
	}
	
	private void initProjection(){
		Mouse.setGrabbed(true);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(fov,  aspect, near, far);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0, 0.75f, 1, 1);
		GL11.glClearDepth(1);
		GL11.glPointSize(10);
		//GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glCullFace(GL11.GL_BACK);
		//GL11.glShadeModel(GL11.GL_SMOOTH);
		//GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glEnable(GL11.GL_LIGHT0);
		//GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		//GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{.05f, .05f, .05f, 1f}));
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, asFloatBuffer(new float[]{.5f, .5f, .5f, 1}));
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, asFloatBuffer(new float[]{0, 0, 0, 1f}));
	}
	
	public void updateView(){
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, asFloatBuffer(new float[]{(float) lightPosition.x, (float) lightPosition.y, (float) lightPosition.z, 1f}));
		GL11.glRotated(rotation.x, 1, 0, 0);
		GL11.glRotated(rotation.y, 0, 1, 0);
		GL11.glRotated(rotation.z, 0, 0, 1);
		GL11.glTranslated(position.x, position.y, position.z);
	}
	
	/**
	 * Move the camera based on its rotation
	 * @param amount amount to move
	 * @param dir left/right vs up/down
	 */
	public void move(float amount, int dir){
		this.setPosition(this.getPosition().Vector3dAdd(new Vector3d(
				(float)(amount * Math.cos(Math.toRadians(this.getRotation().y + 90 * dir))),
				0,
				(float)(amount * Math.sin(Math.toRadians(this.getRotation().y + 90 * dir))))));
	}
	
	/**
	 * I actually have no idea what this does or why it's needed.
	 * @param values
	 * @return
	 */
	private static FloatBuffer asFloatBuffer(float[] values){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public Camera setPosition(Vector3d vec){
		position = vec;
		return this;
	}
	
	public Camera setRotation(Vector3d vec){
		rotation = vec;
		return this;
	}
	
	/**
	 * getter
	 */
	public int getMaxAngle(){
		return maxAngle;
	}
	public float getAspect(){
		return aspect;
	}
	
	public float getFOV(){
		return fov;
	}
	
	public float getNear(){
		return near;
	}
	
	public float getFar(){
		return far;
	}
	
	public Vector3d getPosition(){
		return position;
	}
	
	public Vector3d getRotation(){
		return rotation;
	}
}