
package proj;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Akram Abu Khousa
 */

public class Lamb extends JLabel {
    private String c; //to store color name ["green" , "red"]
    private Color color; //to store object Color
    private String name; //to assign a name for each lamb
    private MagnaticMedium file; //to get the file object to write in
    
    
    /*
    *  to avoid access throw Constructor
    */
    public Lamb create(String name) {
        return new Lamb(name, 0);
    }
    
    public Lamb(){
        this.color = Color.green;
        this.name = "NAN";
        this.file = MagnaticMedium.getFile();
    }
    public Lamb(String name, int color){
        super(name);
        if(color == 0){
           this.color = Color.green;
        }else{
           this.color = Color.red; 
        }
        this.name = name;
        this.file = MagnaticMedium.getFile();
    }
    
    /*
    *  getting color object
    */
    public Color color(){    
        file.log("Observing " + this.name + "'s Color (" + this.colorName(this.color) + ")");
        return this.color;
    }
    
    /*
    *  setting color object
    */
    public void color(int new_color){
        if(new_color == 0){
           this.color = Color.green;
        }else{
           this.color = Color.red; 
        }
        
        file.log(this.name + "'s Color changed to (" + this.colorName(this.color) + ")");
    }
    
    /*
    *  get color name
    */
    public String colorName(Color color){
        if(color.getRed()== 255) c = "RED";
        else c = "GRREN";
        return c;
    }
//    private static void log(String s){
//        System.out.println("Operation =>  " + s + " at: " + System.currentTimeMillis());
//    }
}
