
package proj;

import javax.swing.JProgressBar;

/**
 *
 * @author Akram Abu Khousa
 */
public class Dial extends JProgressBar{
    
    private MagnaticMedium file = MagnaticMedium.getFile();
    
    public Dial(){
        super(0,100);
        file.log("Creating Dial Object");
    }
    
//    private static void log(String s){
//        System.out.println("Operation =>  " + s + " at: " + System.currentTimeMillis());
//    }
    
}
