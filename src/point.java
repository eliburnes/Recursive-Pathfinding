import java.awt.Rectangle;

public class point {
public int x;
public int y;
public int width1;
public int width2;
public int time;
public Rectangle rec;
public boolean passed;
public boolean blocked;
public boolean queen;
public int value;
public int type;
	public point(int z, int o, int w1, int w2, int h) {
		x=z;
		y=o;
		width1=w1;
		width2=w2;
		passed=false;
		blocked=false;
		value=h;
		
		if(0<=value&&value<=10){
			type=1;
		}
		if(10<=value&&value<=20){
			type=2;
		}
		if(30<=value&&value<=40){
			type=3;
		}
		if(40<=value&&value<=50){
			type=4;
		}
		if(50<=value&&value<=60){
			type=5;
		}
		if(60<=value&&value<=70){
			type=6;
		}
		if(70<=value&&value<=80){
			type=7;
		}
		if(80<=value&&value<=90){
			type=8;
		}
		if(90<=value&&value<=100){
			type=9;
		}
		
	}

	public void reRec(){
		rec=new Rectangle(x,y,width1,width2);
	}
	

}
