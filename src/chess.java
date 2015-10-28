import java.applet.Applet;
//import java.util.IllegalFormatCodePointException;
import java.awt.Graphics;
import java.awt.Image;
import java.nio.file.SecureDirectoryStream;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;


public class chess extends Applet 
{
	
	public int size=8;
	public int[][] board=new int[size][size];
	public int[][] end=new int[size][size];
	//1=queen
	//2=blocked 
	//3=available
	public point[] queenArray=new point [size];
	public point[] queenEnd=new point [size];
	public point[][] solutions=new point[size*size*size][size];
	public int numSolutions;
	public int shownSolution=6;
	public int worldX=1000;
	public int worldY=1000;
	public int paths=0;
	public int numQueen;
	public int queens;
	public int numSolution;
	public int x1=0;
	public int y1=0;
	public int width=15;
	int count=0;
	public int startX=50;
	public boolean start=false;
	
	
	Graphics bufferGraphics;
	Image offscreen;

	public void init(){
		numSolutions=0;
		
		for (int x=0;x<size;x++){
			queenArray[x]= new point(x,0,0,0,0);
		}
		for (int x=0;x<size*size*size;x++){
			for (int y=0;y<size;y++){
				solutions[x][y]= new point(x,0,0,0,0);
			}
		}

		queenArray[0].y=4;
		find(queenArray, 1);
		
		
		setSize(worldX,worldY);
		
	offscreen=createImage(worldX,worldY);
	bufferGraphics=offscreen.getGraphics();
	
	

	}
	
	public void find(point queenarray[], int numQueens){
		if(numQueens==size){
			
			for (int x=0;x<numQueens;x++){
			System.out.println(queenarray[x].y);
			}
			System.out.println("******************************");
			for (int x=0;x<size;x++){
				
					solutions[numSolutions][x].y=queenarray[x].y;
					
				}
			numSolutions++;
		}
		else{
			for (int g=0;g<size;g++){
				if (canPlace(queenarray, numQueens, g, numQueens)==true){
					queenarray[numQueens].y=g;
					numQueens++;	
					find(queenarray, numQueens);
				}

			}
		}
	}
	
	public boolean canPlace(point queenarray[], int queenX, int queenY, int numQueens){
		//boolean canPlace=true;
		
		for (int x=0;x<numQueens;x++){
				if(queenarray[x].x+queenarray[x].y==queenX+queenY){
					return(false);	
				}
				if(queenarray[x].x==queenX||queenarray[x].y==queenY){
					return(false);
				}
				if(queenarray[x].x-queenarray[x].y==queenX-queenY){
					return(false);
			}
			}
			return(true);
		
		
	}
	
	public void paint(Graphics g)
	   {
		  bufferGraphics.clearRect(0,0,worldX,worldY);
		   
		  for (int x=0;x<size;x++){
			  for (int o=0;o<size;o++){
				  for (int y=0;y<size;y++){
			  bufferGraphics.fillRect(o*150+startX+x+x*width,startX+solutions[o][x].y+solutions[o][x].y*width, width, width);
			   bufferGraphics.drawRect(o*150+startX+x+x*width,startX+y+y*width, width, width);
		  }}}
		  
		  g.drawImage(offscreen,0,0,this);
		   
	   }
		
		public void update(Graphics g){
			paint (g);
		}
	
	
}
