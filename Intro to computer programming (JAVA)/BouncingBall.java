import acm.program.*;
import acm.graphics.*;
import java.awt.*;
public class BouncingBall extends GraphicsProgram{
public void run() {
   GOval ball = new GOval(getWidth()/2, getHeight()/2, 100, 100);
   ball.setFilled(true);
   ball.setFillColor(Color.RED);
   add(ball);
   double dx = 1;
   double dy = 1;
   double x=getWidth()/2;
   double y=getHeight()/2;
while(true){
 while(x<getWidth()-100 && y<getHeight()-100){     
	  ball.move(dx, dy);
      pause(5);
	  x=x+1;
	  y=y+1;
	 }
   while(x>=0&&y>0){
    ball.move(-dx,-dy);
	x=x-1;
	y=y-1;
    pause(5);
	}
	while(x>=0&&y<getHeight()-100){
		ball.move(-dx, dy);
      pause(5);
	  x=x-1;
	  y=y+1;
	}
	while(x<getWidth()-100 && y>=0){
		ball.move(dx, -dy);
      pause(5);
	  x=x+1;
	  y=y-1;
	}
 
}
  }
   }  

