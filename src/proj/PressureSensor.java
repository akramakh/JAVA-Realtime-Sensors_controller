
package proj;

/**
 *
 * @author Akram Abu Khousa
 */
public class PressureSensor extends Sensor{
    
    private int last_value;
    private int current_value;
    private int min_value;
    private int max_value;
    private int readings;
    private MagnaticMedium file;
    private Dial dial;
    private Lamb lamb;
    private int timeouts = 0;
    
    public PressureSensor(int min, int max){
        super(min, max);
        file = MagnaticMedium.getFile();
        file.log("Creating Pressure Sensor object");
        this.min_value = min;
        this.max_value = max;
        this.current_value = 10;
        this.readings = 0;
        this.lamb = new Lamb("Pressure Lamb",0);
        this.dial = new Dial();
        file.log("Pressure Sensor object created Successfuly");
    }
    
    public int min(){
        file.log("Getting Pressure Sensor's MIN value, which is " + this.min_value);
        return this.min_value;
    }
    public void min(int value){
        file.log("Changing Pressure Sensor's MIN value from " + this.min_value + " to " + value);
        this.min_value = value;
    }
    
    public int max(){
        file.log("Getting Pressure Sensor's MAX value, which is " + this.max_value);
        return this.max_value;
    }
    public void max(int value){
        file.log("Changing Pressure Sensor's MAX value from " + this.max_value + " to " + value);
        this.max_value = value;
    }
    
    public int currentValue(){
        file.log("Getting Pressure Sensor's CURRENT value, which is " + this.current_value);
        if(this.current_value < this.min() || this.current_value > this.max()){
            this.timeout();
            return 0;
        }
        this.timeouts = 0;
        return this.current_value;
    }
    public void currentValue(int value){
        this.lastValue(this.current_value);
        file.log("Changing Pressure Sensor's CURRENT value from " + this.current_value + " to " + value);
        this.current_value = value;
    }
    
    public int lastValue(){
        file.log("Getting Pressure Sensor's LAST value, which is " + this.last_value);
        return this.last_value;
    }
    public void lastValue(int value){
        file.log("Changing Pressure Sensor's LAST value from " + this.last_value + " to " + value);
        this.last_value = value;
    }
    
    public Lamb lamb(){
        file.log("Getting Pressure Sensor's LAMB value, which is " + this.lamb.colorName(this.lamb.color()));
        return this.lamb;
    }
    public void lamb(Lamb l){
        file.log("Changing Pressure Sensor's LAMB value from " + "\"" + this.lamb.getText() + "\"" + " to "+ "\""  + l.getText()+ "\"");
        this.lamb = l;
    }
    
    public boolean safe(){
        return this.current_value < 60;
    }
    
    public Dial dial(){
        file.log("Getting Pressure Sensor's DIAL value, which is " + this.dial.getValue());
        return this.dial;
    }
    public void dial(Dial d){
        file.log("Changing Pressure Sensor's DIAL value from " + this.dial.getValue() + " to " + d);
        this.dial = d;
    }
    
    public void timeout(){
        this.timeouts++;
        if(this.timeouts > 2){
            this.lamb.color(1);
            MagnaticMedium.getFile().log("Three Time Outs were Generated");
        }
    }
}
