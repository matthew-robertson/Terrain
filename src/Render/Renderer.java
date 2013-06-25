package Render;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import Client.Engine;
import Terrain.Terrain;
import Utils.Vector3d;

public class Renderer{	
	public void update(Engine engine, Vector3d[][] map, Vector3d[][] colour, Camera cam){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		cam.updateView();
		drawTerrain(map, colour, engine.roomsize);
		//drawTerrain(new FlatTerrain(0), engine.roomsize);
		
		//drawRoom(engine.roomsize);
		Display.sync(70);
		Display.update();
		
	}
	
	private void drawTerrain(Vector3d[][] map, Vector3d[][] colours, int roomsize){
		GL11.glPushMatrix();
		GL11.glColor3d(5.0f, 0.5f, 0f);
		GL11.glTranslated(0, 0, 0);
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			Vector3d colour;
			for (int i = 0; i < 100; i++){
				for (int j = 0; j < 100; j++){
					colour = colours[i][j];
					GL11.glColor3d(colour.x, colour.y, colour.z);
					GL11.glVertex3d(i, map[i][j].y, j);
					GL11.glVertex3d(i, map[i][j + 1].y, j + 1);
					GL11.glVertex3d(i + 1, map[i + 1][j].y, j);
					
					colour = colours[i + 1][j + 1];
					GL11.glVertex3d(i + 1, map[i + 1][j + 1].y, j + 1);
					GL11.glVertex3d(i, map[i][j + 1].y, j + 1);
					GL11.glVertex3d(i + 1, map[i + 1][j].y, j);
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