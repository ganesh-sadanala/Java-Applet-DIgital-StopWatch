/**Java Program to illustrate Digital Stop Watch using Applet in java*/
// importing required packages
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*<>*/

public class DigitalClock extends Applet implements Runnable,ActionListener
{
// Panel to keep all the buttons and labels 

  Panel p;
  
  Label display;

//Button

  Button start,stop,reset;

//Time
  int hr,min,sec,millsec;
  
  //String to be displayed on the label
  String disp;
  
  //State of stop-watch on/off
  boolean on;

//Initialization
  public void init()
{

//initially off
on=false;

p=new Panel();

//Setting layout of the panel
p.setLayout(new GridLayout(4,1,6,10));

//initial time 00:00:00:000
hr=min=sec=millsec=0;

//Label
display=new Label();

disp="00 : 00 : 00 : 000";
display.setText(disp);
p.add(display);

//Start Button
start=new Button("Start");
start.addActionListener((ActionListener)this);

//Add to the Panel
p.add(start);

//Reset Button
reset=new Button("Reset");
reset.addActionListener((ActionListener)this);
p.add(reset);

//Stop Button
stop=new Button("Stop");
stop.addActionListener((ActionListener)this);
p.add(stop);
add(p);

//Invoke the run method
new Thread(this,"StopWatch").start();
}

//Reset Function
//reset to default value
public void reset()
{
   try{
Thread.sleep(1);
}catch(Exception e){
System.out.println(e);
}
hr=min=sec=millsec=0;
}


//update function
//update the timer
public void update()
{
millsec++;
if(millsec == 1000)
{
  millsec=0;sec++;
  if(sec==60)
{
  sec=0;min++;
   if(min==60)
{
   min=0;hr++;
}
}
}
}

//changing label
public void changeLabel()
{

//Properly formatting the display Label
if(hr<10)
disp="0" + hr + ":";
else
disp=hr + ":";

if (min < 10) 
            disp += "0" + min + " : "; 
        else
            disp += min + " : "; 
  
        if (sec < 10) 
            disp += "0" + sec + " : "; 
        else
            disp += sec + " : "; 
  
        if (millsec < 10) 
            disp += "00" + millsec; 
        else if (millsec < 100) 
            disp += "0" + millsec; 
        else
            disp += millsec; 
  
        display.setText(disp); 
}

public void run()
{

//When Start button is clicked
while(on){
try{

//pause 1 millisecond
Thread.sleep(1);
//update the time
update();
//change the label
changeLabel();
}catch(InterruptedException e){
System.out.println(e);
}
}
}

//Any action is performed like button click
public void actionPerformed(ActionEvent e)
{
//getSource() gets the method id
//start a thread when start button is pressed
if(e.getSource()==start){
on=true;new Thread(this,"StopWatch").start();
}

//stop the clock
if(e.getSource()==stop){
on=false;
}
//reset
if(e.getSource()==reset){

//stop the watch
on=false;reset();changeLabel();
}

}
}