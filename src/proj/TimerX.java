
package proj;

//import java.sql.*;

import java.sql.Time;
import java.util.Timer;


/**
 *
 * @author Akram Abu Khousa
 */
public class TimerX extends Timer{
    private Time start;
    private Time end;
    private MagnaticMedium file;
    
//    MagnaticMedium file;
    
    public static TimerX create(){
        return new TimerX();
    }
    
    public TimerX(){
        this.start = new Time(System.currentTimeMillis());
        this.end = new Time(System.currentTimeMillis()+1000l);
        file = MagnaticMedium.getFile();
        file.log("timer start from: " + this.start +"  to: "+ this.end);
    }
    
    public void timeOut(int duration){
        this.start = new Time(System.currentTimeMillis());
        this.end = new Time(System.currentTimeMillis() + duration);
        file.log("timer has been set from: " + this.start +"  to: "+ this.end);
        
    }
    
    public void delay(int duration){
        
        file.log("timer has been set for DELAY at: " + this.start +"  for: "+ duration);
    }
    
    public void duration(int duration){
        
        file.log("timer has been set for DURATION at: " + this.start +"  for: "+ duration);
    }
    
    public void deadLine(int duration){
        
        file.log("timer has been set for DEADLINE from: " + this.start +"  till: "+ this.start + duration);
    }
//    private static void log(String s){
//        System.out.println("Operation =>  " + s + " at: " + System.currentTimeMillis());
//    }
}
