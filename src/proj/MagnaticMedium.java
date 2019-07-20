
package proj;

import java.io.*;
import java.util.Date;

/**
 *
 * @author Akram Abu Khousa
 */

public class MagnaticMedium{
    
    FileWriter fw;
    PrintWriter pw;
    private int counter; //to numerate the Lines in the Magnatic Medium file
    private static int lastMins;
    private static MagnaticMedium file;
    
    /*
    *  Constructor
    */
    private MagnaticMedium(){
        this.counter = 000;
        lastMins = new Date().getMinutes()-1;
        try {
            this.fw = new FileWriter("MagnaticMedium.txt");
            this.pw = new PrintWriter(this.fw);
        } catch (IOException ex) {
            System.out.println("ERROR Logging");
        }
    }
    
    /*
    *  get a unique Magnatic Medium instance from any where
    */
    public static MagnaticMedium getFile(){
        if(file == null){
            file = new MagnaticMedium();
            file.log("The Magnatic Medium was created");
        }
        return file;
    }
    
    /*
    * Lagging action in the file
    */
    public void log(String s){
        if(lastMins == new Date().getMinutes()){
            this.counter++;
            this.pw.println(this.counter + "  Time  =>  " + "(" + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds() + ") " + System.currentTimeMillis() + "  |  Operation =>  " + s);
            System.out.println("Time  =>  " + System.currentTimeMillis() + "  |  Operation =>  " + s);
        }else{
            this.lastMins = new Date().getMinutes();
            this.pw.println("+------------------------------------------------------------------------------+\r\n|                         "+new Date()+"                         |\r\n+------------------------------------------------------------------------------+");
        }
       
    }
    
    /*
    *  Close and save Logging File
    */
    public void closeFile(){
        if(Controller.closeFile){
            log("Closing the Program");
            this.pw.close();
        }   
    }
}
