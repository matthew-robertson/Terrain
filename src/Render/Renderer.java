package Render;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import Client.Engine;
import Terrain.Terrain;
import Utils.Triangle;
import Utils.Vector3d;

public class Renderer{	
	public void update(Engine engine, double[][] map, Vector3d[][] colour, Triangle[] triangles, int steps, Camera cam){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		cam.updateView();
		renderTriangles(triangles, map);
		//drawTerrain(map, colour, steps);
		Display.sync(70);
		Display.update();
		
	}
	
	private void renderTriangles(Triangle[] triangles, double[][] map){
		GL11.glPushMatrix();
		GL11.glColor3d(5.0f, 0.5f, 0f);
		GL11.glTranslated(0, 0, 0);
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			for (int i = 0; i < triangles.length; i++){
				Vector3d colour = triangles[i].colour[0];
				GL11.glColor3d(colour.x, colour.y, colour.z);
				int d = 10;
				GL11.glVertex3d(triangles[i].points[0].x / d, triangles[i].points[0].y / d, triangles[i].points[0].z / d);
				colour = triangles[i].colour[1];
				GL11.glVertex3d(triangles[i].points[1].x / d, triangles[i].points[1].y / d, triangles[i].points[1].z / d);
				colour = triangles[i].colour[2];
				GL11.glVertex3d(triangles[i].points[2].x /d, triangles[i].points[2].y / d, triangles[i].points[2].z / d);
			}
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	private void drawTerrain(double[][] map, Vector3d[][] colours, int steps){
		GL11.glPushMatrix();
		GL11.glColor3d(5.0f, 0.5f, 0f);
		GL11.glTranslated(0, 0, 0);
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			Vector3d colour;
			for (int i = 0; i < steps - 1; i++){
				for (int j = 0; j < steps - 1; j++){
					colour = colours[i][j];
					GL11.glColor3d(colour.x, colour.y, colour.z);
					GL11.glVertex3d((i + 1) / 1, map[i + 1][j], j / 1);
					GL11.glVertex3d(i /1, map[i][j], j / 1);
					GL11.glVertex3d(i / 1, map[i][j + 1], (j + 1) / 1);
					
					colour = colours[i][j + 1];
					GL11.glColor3d(colour.x, colour.y, colour.z);
					GL11.glVertex3d((i + 1) / 1, map[i + 1][j], j / 1);
					GL11.glVertex3d((i + 1) / 1, map[i + 1][j + 1], (j + 1) / 1);
					GL11.glVertex3d(i / 1, map[i][j + 1], (j + 1) / 1);
					
				}
			}			
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
}