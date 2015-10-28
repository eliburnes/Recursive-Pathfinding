
import java.applet.Applet;
	//import java.util.IllegalFormatCodePointException;
	import java.awt.Graphics;
import java.awt.Image;
import java.lang.Object;
import java.util.*;
//import org.apache.commons.math3.util.CombinatoricsUtils;

public class bezier extends Applet {

		public int worldX=500;
		public int worldY=500;
		public int numPoints=4;
		public int length;
		public int highest;
		public int lowest;
		public int width=15;
		public int smallWidth;
		public int resolution=500;
		public point[]points=new point[numPoints]; 
		public point[]dots; 
		Graphics bufferGraphics;
		Image offscreen;

		public void init(){
			
			 setSize(worldX,worldY);
			
			for (int x=0;x<points.length;x++){
				int t = ((int) (Math.random()*(worldX-200)));
				int r = ((int) (Math.random()*(worldX-200)));
				
				points[x]=new point(t, r, 0, 0, 0);
			}
			
			
			for (int x=0;x<points.length;x++){
				if(highest<points[x].x){
					highest=points[x].x;
				}
				if(lowest>points[x].x){
					lowest=points[x].x;
				}
			}
			length=highest-lowest;
			
			dots=new point[resolution];
			for (int x=0;x<dots.length;x++){
				dots[x]=new point(0, 0, 0, 0, 0);
			}
			curve(points);
			
		offscreen=createImage(worldX,worldY);
		bufferGraphics=offscreen.getGraphics();
		
		repaint();
	
		}
		
		public void curve(point[] input){
			
			for (int t=0;t<1.2;t+=.2){
				dots[t*5].x=input[0].x*(1-t)^3+3*input[1].x*t*(1-t)^2+3*input[2].x*t^2*(1-t)+input[3].x*t^3;
				dots[t*5].y=input[0].y*(1-t)^3+3*input[1].y*t*(1-t)^2+3*input[2].y*t^2*(1-t)+input[3].y*t^3;
				System.out.println(dots[t*5].x);
			}
			
		}
		
		
		public void paint(Graphics g)
		   {
			  bufferGraphics.clearRect(0,0,worldX,worldY);
			  
			
			  for (int x=0;x<points.length;x++){
			  bufferGraphics.fillRect(points[x].x,points[x].y, width, width);
			  }
			  for (int x=0;x<dots.length;x++){
				  bufferGraphics.fillRect(dots[x].x,dots[x].y, 5, 5);
				  System.out.println(dots[x].x);
				  }
			  g.drawImage(offscreen,0,0,this);
			   
		   }
			
			public void update(Graphics g){
				paint (g);
			}
		
		
	}



