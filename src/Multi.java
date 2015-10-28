import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Multi extends Applet implements MouseMotionListener, MouseListener, Runnable, ActionListener
{

		public int width=30;
	   public int size=15;
	   public point[][] cookie= new point[size][size];
	   public boolean[][] multiPoints= new boolean[size][size];
	   public boolean[][] yo= new boolean[size][size];
	  
	   public int pathLength;
	   int low=10000;
	   public int xpos=20;
	   public int ypos=20;
	   public int mouseX;
	   public int mouseY;
	   public int randomX;
	   public int worldX=1000;
	   public int worldY=800;
	   public int mode=2;
	   //mode=1 is Color, mode=2 is 
	   public Image terrainPic;
	   public Image greenPic;
	   public Image grassPic;
	   
	   public int range=80;
	   
	   public Button color;
	   public Button landscape;
	   
	   Graphics bufferGraphics;
	   Image offscreen;
	   
	   Thread thread;
	   
	
	   
	   public void init()
	   {
		   setSize(worldX,worldY);
		   this.setFocusable(true);
		   addMouseListener(this);
		   addMouseMotionListener(this);
		   
		   
		   color=new Button ("Color Mode");
		   color.setBounds(size*40+200, 200, size*40+250, 220);
		   landscape=new Button ("Landscape Mode");
		   landscape.setBounds(size*40+200, 200, size*40+250, 220);
		   
		   color.setVisible(false);
		   landscape.setVisible(true);
		   add(color);
		   add(landscape);
		   color.addActionListener(this);
		   landscape.addActionListener(this);
		   
		   terrainPic=getImage(getDocumentBase(),"terrain.png");
		   greenPic=getImage(getDocumentBase(),"test.png");
		   grassPic=getImage(getDocumentBase(),"grass1.png");
		
	      for(int x=0;x<size;x++)
	      {
	         for(int y=0;y<size;y++)
	         {
	        	int t = ((int) (Math.random()*range));
	            cookie[x][y]=new point(xpos+x*width, ypos+y*width, width, width, t);  
	         }
	      }
   
	   	 pathLength=cookie[0][0].value;
	   	
	   	 
	      cookie[0][0].passed=true;
	      yo[0][0]=true;
	      
	      pathFind(0,0,yo,cookie[0][0].value,2);
	      
	      for(int x=0;x<size;x++){
	      for(int y=0;y<size;y++)
	         {
	      cookie[x][y].reRec();
	         }}
	      
	      
	      
	      thread = new Thread(this);  //constructs a new thread
	      thread.start();	
	      
	      offscreen=createImage(worldX,worldY);
	      bufferGraphics=offscreen.getGraphics();
	      
	     
	      
	   }//init()
	   
	   
		public void mouseClicked(MouseEvent e) {
			mouseX=e.getX();
			mouseY=e.getY();
			
			for(int x=0;x<size;x++)
		      {
		    	  
		         for(int y=0;y<size;y++)
		         {
			       if(cookie[x][y].rec.contains(mouseX,mouseY)){
			      if(x!=y||x!=0||x!=0){
			      System.out.println("works");
			      System.out.println("("+x+","+y+")");

				  cookie[x][y].blocked=true;
				
				  low=10000;
				  if(cookie[x][y].passed){
			      pathFind(0,0,yo,cookie[0][0].value,2);
				  }
			      

			        }
				
		         }}}
		      repaint();

			
		}

	   public void paint(Graphics g)
	   {
		   bufferGraphics.clearRect(0,0,worldX,worldY);
		   
		   
		   
		   if(mode==1){
			   bufferGraphics.drawImage(grassPic,xpos,ypos,cookie[size-1][size-1].x+width,cookie[size-1][size-1].y+width, this);
		   for(int x=0;x<size;x++)
		      {
		         for(int y=0;y<size;y++)
		         {
		         if(cookie[x][y].type==3){
			     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 2+cookie[x][y].type*105, 160,(cookie[x][y].type+1)*85, this);
		         }
		         if(cookie[x][y].type==8){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 189, 160,274, this);
			         }
		         if(cookie[x][y].type==7){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 278, 160,353, this);
			         }
		         if(cookie[x][y].type==6){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 354, 160,431, this);
			         }
		         if(cookie[x][y].type==2){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 434, 160,515, this);
			         }
		         if(cookie[x][y].type==1){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 522, 160,607, this);
			         }
		         if(cookie[x][y].type==4){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 610, 160,684, this);
			         }
		         if(cookie[x][y].type==5){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x,cookie[x][y].y,cookie[x][y].x+width,cookie[x][y].y+width,80, 2, 160,100, this);
			         }
		         if(cookie[x][y].type==9){
				     bufferGraphics.drawImage(terrainPic, cookie[x][y].x-5,cookie[x][y].y-5,cookie[x][y].x+width+5,cookie[x][y].y+width+5,190, 180, 363,383, this);
			         }
		         if(cookie[x][y].passed){
			        	bufferGraphics.setColor(Color.lightGray);
			        	bufferGraphics.fillRect(cookie[x][y].x,cookie[x][y].y,cookie[x][y].width1,cookie[x][y].width2);
			        	}
		         if(cookie[x][y].blocked){
			        	bufferGraphics.setColor(Color.black);
			        	bufferGraphics.fillRect(cookie[x][y].x,cookie[x][y].y,cookie[x][y].width1,cookie[x][y].width2);
			        	}
		         }}}
		   
		   bufferGraphics.setColor(Color.BLACK);	
	  	  for(int x=0;x<size;x++)
	      {
	         for(int y=0;y<size;y++)
	         {
	            bufferGraphics.drawRect(xpos+x*width,ypos+y*width,width,width);
	            if(mode==2){
	            	
	            Color color=new Color(255,255-cookie[x][y].value*(255/range),255-cookie[x][y].value*(255/range));
	            bufferGraphics.setColor(color);
	            bufferGraphics.fillRect(cookie[x][y].x,cookie[x][y].y,cookie[x][y].width1,cookie[x][y].width2);

	        	if(cookie[x][y].passed){
	        	bufferGraphics.setColor(Color.lightGray);
	        	bufferGraphics.fillRect(cookie[x][y].x,cookie[x][y].y,cookie[x][y].width1,cookie[x][y].width2);
	        	}
		        
		        bufferGraphics.setColor(Color.BLACK);
		        bufferGraphics.drawString(""+cookie[x][y].value, cookie[x][y].x+width/3,cookie[x][y].y+width/2);
		        if(cookie[x][y].blocked){
		        	bufferGraphics.setColor(Color.black);
		        	bufferGraphics.fillRect(cookie[x][y].x,cookie[x][y].y,cookie[x][y].width1,cookie[x][y].width2);
		        	}
	            }

	         }
	         
	      }
	  	  
	  			   
		   for(int x=0;x<size;x++)
		      {
		         for(int y=0;y<size;y++)
		         {		        
		        	bufferGraphics.setColor(Color.black);
		        	bufferGraphics.drawRect(xpos+x*width,ypos+y*width,width,width);
		         }}
		    
	
		   g.drawImage(offscreen,0,0,this);
		   }// paint()
	   
	   public void pathFind(int x, int y, boolean[][] path, int pathLength, int direction){
	      if(x==size-1&&y==size-1){
	      
	      
	      if (low>pathLength){
	      low=pathLength;	    
	      for(int u=0;u<size;u++){
   	    	  for(int j=0;j<size;j++){
   	    	cookie[u][j].passed=path[u][j];
   	    	  
   	    	  }
          }
	    
	      }}
	      
	      else{
	    	  
 			 if(pathLength>low){
 				 x=size-1;
 				 y=size-1;
 			 }
	    	 
	    		 if(x<size-1&&y<size-1){
	    			 if(pathLength+(size-1-x)<low||pathLength+(size-1-y)<low){
	    				 
	    			 
		        	 if(cookie[x+1][y+1].blocked==false){
	 
		        	 boolean[][]g=new boolean[size][size];
		        	 for(int u=0;u<size;u++){
		   	    	  for(int j=0;j<size;j++){
		   	    		g[u][j]=path[u][j];
		   	    	  }
		   	    	  }
		        	 g[x+1][y+1]=true;
			        //path[x+1][y+1]=true;
		            pathFind(x+1,y+1,g, pathLength+cookie[x+1][y+1].value,1);
		         }}}
	         if(x<size-1){
	        	 if(pathLength+(size-1-x)<low||pathLength+(size-1-y)<low){
	        	 if(direction!=3){
	        	 if(cookie[x+1][y].blocked==false){
	        	
	        	 boolean[][]g=new boolean[size][size];
	        	 for(int u=0;u<size;u++){
	   	    	  for(int j=0;j<size;j++){
	   	    		g[u][j]=path[u][j];   
	   	    	  }
	   	    	  }
	        	 g[x+1][y]=true;
		        //path[x+1][y]=true;
	            pathFind(x+1,y,g,pathLength+cookie[x+1][y].value,2);
	         }}}}
	         if(y<size-1){
	        	 if(pathLength+(size-1-x)<low||pathLength+(size-1-y)<low){
	        	 if(cookie[x][y+1].blocked==false){
	        	 if(direction!=2){
	        	 boolean[][]g=new boolean[size][size];
	        	 for(int u=0;u<size;u++){
	   	    	  for(int j=0;j<size;j++){
	   	    		 g[u][j]=path[u][j];
	   	    	  }
	   	    	  }
	        	 g[x][y+1]=true;
		        //path[x][y+1]=true;
	            pathFind(x,y+1,g,pathLength+cookie[x][y+1].value,3);
	         }}}}
	         
	      } 
	      //System.out.println("done");   
	   }
	   
	   
	   public void run() {
		   
		   	// this thread loop forever and runs the paint method and then sleeps.
		      while(true)
		      {
		    	 
		    	  
		    	  
		    	  repaint();
		 
		         try {
		            Thread.sleep(25);
		         }
		         catch (Exception e){ }
		         
		      }//while
		   
		   }// run()

	

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==color){
				mode=2;
				color.setVisible(false);
				landscape.setVisible(true);
				repaint();
			}
			if(e.getSource()==landscape){
				mode=1;
				landscape.setVisible(false);
				color.setVisible(true);
				repaint();
			}
	}
	
	
	}
