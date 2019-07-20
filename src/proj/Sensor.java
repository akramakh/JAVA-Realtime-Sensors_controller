
package proj;

/**
 *
 * @author Akram Abu Khousa
 */

public class Sensor {
    
    private int last_value;
    private int current_value;
    private int min_value;
    private int max_value;
    public int readings;
    private TimerX timer;
    private MagnaticMedium file;
    private Lamb lamb;
    private int timeouts = 0;
    
    public Sensor(){
        
    }
    public Sensor(int min, int max){
        this.min_value = min;
        this.max_value = max;
        this.readings = 0;
        this.current_value = -1;
        this.file = MagnaticMedium.getFile();
    }
    
    /*
    *  get minimum value
    */
    public int min(){
        file.log("Getting Sensor's MIN value, which is " + this.min_value);
        return this.min_value;
    }
    
    /*
    *  set minimum value
    */
    public void min(int value){
        file.log("Changing Sensor's MIN value from " + this.min_value + " to " + value);
        this.min_value = value;
    }
    
    /*
    *  get maximum value
    */
    public int max(){
        file.log("Getting Sensor's MAX value, which is " + this.max_value);
        return this.max_value;
    }
    
    /*
    *  set maximum value
    */
    public void max(int value){
        file.log("Changing Sensor's MAX value from " + this.max_value + " to " + value);
        this.max_value = value;
    }
    
    /*
    *  get current value
    */
    public int currentValue(){
        file.log("Getting Sensor's CURRENT value, which is " + this.current_value);
        if(this.current_value < this.min() || this.current_value > this.max()){
            this.timeout();
            return 0;
        }
        this.timeouts = 0;
        return this.current_value;
    }
    
    /*
    *  set current value
    */
    public void currentValue(int value){
        this.lastValue(this.current_value);
        file.log("Changing Sensor's CURRENT value from " + this.current_value + " to " + value);
        this.current_value = value;
    }
    
    /*
    *  get previous value
    */
    public int lastValue(){
        file.log("Getting Sensor's LAST value, which is " + this.last_value);
        return this.last_value;
    }
    
    /*
    *  set previous value
    */
    public void lastValue(int value){
        file.log("Changing Sensor's LAST value from " + this.last_value + " to " + value);
        this.last_value = value;
    }
    
    /*
    *  get corresponding Lamb
    */
    public Lamb lamb(){
        file.log("Getting Sensor's LAMB value, which is " + this.lamb.colorName(this.lamb.color()));
        return this.lamb;
    }
    
    /*
    *  set corresponding Lamb
    */
    public void lamb(Lamb l){
        file.log("Changing Sensor's LAMB value from " + "\"" + this.lamb.getText() + "\"" + " to "+ "\""  + l.getText()+ "\"");
        this.lamb = l;
    }
    
    /*
    *  Check if the sensor reading is in the safe range or not
    */
    public boolean safe(){
        return this.current_value < 50;
    }
    
    /*
    *  when the sensor fails to send readings three times
    */
    public void timeout(){
        this.timeouts++;
        if(this.timeouts > 2){
            this.lamb.color(1);
            MagnaticMedium.getFile().log("Three Time Outs were Generated");
        }
    }
//    private static void log(String s){
//        System.out.println("Operation =>  " + s + " at: " + System.currentTimeMillis());
//    }
}
