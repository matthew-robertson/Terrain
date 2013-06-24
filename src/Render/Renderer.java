package Render;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import Client.Engine;
import Terrain.FlatTerrain;
import Terrain.Terrain;
import Terrain.TrigTerrain;
import Utils.Vector3d;

public class Renderer{	
	public void update(Engine engine, Camera cam){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		cam.updateView();
		drawTerrain(new TrigTerrain(0.5, 0.5), engine.roomsize);
		//drawTerrain(new FlatTerrain(0), engine.roomsize);
		
		//drawRoom(engine.roomsize);
		Display.sync(70);
		Display.update();
		
	}
	
	private void drawTerrain(Terrain terrain, int roomsize){
		GL11.glPushMatrix();
		GL11.glColor3d(5.0f, 0.5f, 0f);
		GL11.glTranslated(0, 0, 0);
		GL11.glBegin(GL11.GL_POINTS);
		{
			Vector3d colour;
			for (double i = -roomsize; i < roomsize; i+= 0.2){
				for (double j = -roomsize; j < roomsize; j+= 0.2){
					colour = terrain.getColour(i, j);
					GL11.glColor3d(colour.x, colour.y, colour.z);
					GL11.glVertex3d(i, terrain.getAltitude(i,  j), j);
				}
			}			
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	private void drawRoom(int roomsize){
		GL11.glPushMatrix();
		GL11.glColor3d(5.0f, 0.5f, 0f);
		GL11.glTranslatef(0, 0, 0);
		GL11.glBegin(GL11.GL_QUADS);
		{
			//Back face
			GL11.glColor3f(1, 0, 0);
			GL11.glVertex3f(-roomsize, roomsize, -roomsize);
			GL11.glVertex3f(roomsize, roomsize, -roomsize);
			GL11.glVertex3f(roomsize, -roomsize, -roomsize);
			GL11.glVertex3f(-roomsize, -roomsize, -roomsize);
			
			//Front face
			/*GL11.glColor3f(0, 1, 1);
			GL11.glVertex3f(-roomsize, roomsize, roomsize);
			GL11.glVertex3f(roomsize, roomsize, roomsize);
			GL11.glVertex3f(roomsize, -roomsize, roomsize);
			GL11.glVertex3f(-roomsize, -roomsize, roomsize);*/
			
			//Left face
			GL11.glColor3f(0, 1, 0);
			GL11.glVertex3f(-roomsize, roomsize, -roomsize);
			GL11.glVertex3f(-roomsize, roomsize, roomsize);
			GL11.glVertex3f(-roomsize, -roomsize, roomsize);
			GL11.glVertex3f(-roomsize, -roomsize, -roomsize);
			
			//Right face
			GL11.glColor3f(0, 0, 1);
			GL11.glVertex3f(roomsize, roomsize, -roomsize);
			GL11.glVertex3f(roomsize, roomsize, roomsize);
			GL11.glVertex3f(roomsize, -roomsize, roomsize);
			GL11.glVertex3f(roomsize, -roomsize, -roomsize);
			
			//Top face
			GL11.glColor3f(1, 1, 0);
			GL11.glVertex3f(roomsize, roomsize, roomsize);
			GL11.glVertex3f(-roomsize,roomsize, roomsize);
			GL11.glVertex3f(-roomsize, roomsize, -roomsize);
			GL11.glVertex3f(roomsize, roomsize, -roomsize);
			
			//Bottom face
			GL11.glColor3f(1, 0, 1);
			GL11.glVertex3f(roomsize, -roomsize, roomsize);
			GL11.glVertex3f(-roomsize, -roomsize, roomsize);
			GL11.glVertex3f(-roomsize, -roomsize, -roomsize);
			GL11.glVertex3f(roomsize, -roomsize, -roomsize);
			
		}
		GL11.glEnd();
		GL11.glPopMatrix();
		
	}
	
	
	private void drawSphere(float x, float y, float z, float radius, Vector3d vec) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        //GL11.glColor3f(vec.x, vec.y, vec.z);
        Sphere s = new Sphere();
        s.draw(radius, 50, 50);
        GL11.glPopMatrix();
    }	
}