
package proj;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Akram Abu Khousa
 */
public class SmokeSensor extends Sensor{
    
    private boolean detect;
    private int current_count;
    public boolean interrupt;
    private MagnaticMedium file;
    private static Lamb lamb;
    private SmokeSensor sensor;
    
    public SmokeSensor(){
        file = MagnaticMedium.getFile();
        file.log("Creating Smoke Sensor object");
        this.detect = false;
        this.current_count = 0;
        this.interrupt = false;
        this.lamb = new Lamb("Smoke Sensor Lamb",0);
        this.sensor = this;
        file.log("Smoke Sensor object created Successfuly");
        
    }
    boolean flag = false;
    public void detect(boolean d){
        
        this.detect = d;
        if(detect){
            MagnaticMedium.getFile().log("Detect New Smoke ");
            flag = Controller.smokeInterrupt(this);
            //setting Timer to handle (Deadline time constraint for 2 sec) if the sensor did not generate an inturrupt
            new Timer().schedule(new TimerTask(){
                public void run() {
                    if(!flag){
                        MagnaticMedium.getFile().log("Interrupting Fails ");
                        flag = false;
                    }
                }
            }, 2000);
        }
        else{
            MagnaticMedium.getFile().log("Detection gone now ");
            flag = Controller.smokeDeInterrupt(this);
            //setting Timer to handle (Deadline time constraint for 4 sec) if the sensor did not generate an inturrupt for desablie the first interrupt
            new Timer().schedule(new TimerTask(){
                public void run() {
                    if(!flag){
                        MagnaticMedium.getFile().log("De-Interrupting Fails ");
                        flag = false;
                    }
                }
            }, 4000);
        }
        
    }
    /*
    *  check if the sensor detect smoke segnal
    */
    public boolean detect(){
        return this.detect;
    }

    public Lamb lamb(){
        file.log("Getting Smoke Sensor's LAMB value from lamb(), which is " + this.lamb.colorName(this.lamb.color()));
        return this.lamb;
    }
    public void lamb(Lamb l){
        file.log("Changing Smoke Sensor's LAMB value from " + "\"" + this.lamb.getText() + "\"" + " to "+ "\""  + l.getText()+ "\"" );
        this.lamb = l;
    }

    public boolean safe(){
        return !this.detect;
    }
    

}
