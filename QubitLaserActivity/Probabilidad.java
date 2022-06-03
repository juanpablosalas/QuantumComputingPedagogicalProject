import processing.core.PApplet;
import processing.core.PFont;
import processing.serial.Serial;



public class Probabilidad extends PApplet {

	Serial myPort;  // Create object from Serial class
	public String val;     // Data received from the serial port

	private float valH;
	private float valV;
	PFont tFont;
	PFont mFont;
	
	private boolean hayPort;
	
	 // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main("Probabilidad");
    }

    // method used only for setting the size of the window
    public void settings(){
    	size(800,600);
    }

    // identical use to setup in Processing IDE except for size()
    public void setup(){
    	
    	try { 
    		
    		String portName = Serial.list()[0]; //change the 0 to a 1 or 2 etc. to match your port
  	  myPort = new Serial(this, portName, 9600);
  	  hayPort = true;
  	  } catch (Exception e) {
  		hayPort = false;
  	  }
    	
    	
    	
    	  background(255,255,255);
    	  fill(241,202,76);
    	  rect((float)0.0,(float)0.0,(float) (width/1.0),(float)0.05*height);
    	  fill(106,100,152);
    	  rect((float)0.98*width,(float)0.05*height,(float)0.02*width,(float)0.2*height);
    	  fill(71,44,98);
    	  rect((float)0.98*width,(float)0.25*height,(float)0.02*width,(float)0.75*height);
    	  
    	  frameRate(5); 
    	  
    	   tFont = createFont("sourcereg.ttf", 32);
    	   mFont = createFont("jsMath-cmmi10.ttf", 32);
    	
    }

    // identical use to draw in Prcessing IDE
    public void draw(){

    	
    	
    	   if ( hayPort) 
    	  {  // If data is available,
    		   
if (myPort.available() > 0){
	val = myPort.readStringUntil('\n');         // read it and store it in val
	  println(val);
	  String [] valores = val.split(",");
	  valH = Float.parseFloat(valores[0]);
	  valV = Float.parseFloat(valores[1]);
}
    	  
    	  } else{
    	    valH = random(0,1);
    	  println(valH);
    	  valV = 1-valH;
    	  }
    	  
    	   background(255,255,255);
     	  fill(241,202,76);
     	  rect((float)0.0,(float)0.0,(float) (width/1.0),(float)0.05*height);
     	  fill(106,100,152);
     	  rect((float)0.98*width,(float)0.05*height,(float)0.02*width,(float)0.2*height);
     	  fill(71,44,98);
     	  rect((float)0.98*width,(float)0.25*height,(float)0.02*width,(float)0.75*height);
    	  
    	  
    	  
    	  fill(241,202,76);
    	  arc((float) 0.5*width,(float) 0.5*height,(float)0.4*width,(float)0.4*width,(float)0.0,(float)2.0*PI*valH);
    	  fill(71,44,98);
    	  arc((float) 0.5*width,(float) 0.5*height,(float)0.4*width,(float)0.4*width,(float)2.0*PI*valH,(float)2.0*PI);

    	  textAlign(LEFT);
    	  textFont(tFont);
    	  text("|   = "+(int)(valH*1000)/1000.+"|0> + "+(int)(valV*1000)/1000.+"|1>",(float)0.175*width,(float)0.9*height);
    	  textFont(mFont);
    	  text("q>",(float)0.2*width,(float)0.9*height);
    	  
    }
	
}
