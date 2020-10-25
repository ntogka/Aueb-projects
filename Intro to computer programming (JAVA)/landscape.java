import acm.program.*;
import acm.graphics.*;
import java.awt.*;
public class landscape extends GraphicsProgram {
	public void run() {
		
		GRect h1=new GRect(40,getHeight()-200,160,200);
		add (h1);
		GRect h2=new GRect(0,getHeight()-300,40,300);
		add(h2);
		GRect h3=new GRect(200,getHeight()-300,40,300);
		add (h3);
		GRect h4=new GRect(280,getHeight()-100,20,100);
		add (h4);
		GRect h5=new GRect(320,getHeight()-100,20,100);
		add (h5);
		GRect h6=new GRect(360,getHeight()-100,20,100);
		add (h6);
		GLine k1=new GLine(0,getHeight()-300,20,getHeight()-340);
		add(k1);
		GLine k2=new GLine(20,getHeight()-340,40,getHeight()-300);
		add(k2);
		GLine k3=new GLine(200,getHeight()-300,220,getHeight()-340);
		add(k3);
		GLine k4=new GLine(220,getHeight()-340,240,getHeight()-300);
		add (k4);
		GLine k5=new GLine(40,getHeight()-200,120,getHeight()-280);
		add(k5);
		GLine k6=new GLine(120,getHeight()-280,200,getHeight()-200);
		add(k6);
	    GLine k7=new GLine(280,getHeight()-100,290,getHeight()-150);
		add(k7);
		GLine k8=new GLine(290,getHeight()-150,300,getHeight()-100);
		add(k8);
		GLine k9=new GLine(320,getHeight()-100,330,getHeight()-150);
		add(k9);
		GLine k10=new GLine(330,getHeight()-150,340,getHeight()-100);
		add(k10);
		GLine k11=new GLine(360,getHeight()-100,370,getHeight()-150);
		add(k11);
		GLine k12=new GLine(370,getHeight()-150,380,getHeight()-100);
		add(k12);
		GRect h7=new GRect(110,getHeight()-50,20,50);
		add(h7);
		GLine k20=new GLine(110,getHeight()-50,120,getHeight()-75);
		add(k20);
		GLine k21=new GLine(120,getHeight()-75,130,getHeight()-50);
		add(k21);
		GOval b1=new GOval(80,getHeight()-150,20,20);
		add(b1);
		GOval b2=new GOval(130,getHeight()-150,20,20);
		add(b2);
	}
}