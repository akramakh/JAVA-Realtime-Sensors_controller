/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;

import java.awt.Color;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Akram Abu Khousa
 */

public class Controller extends JFrame{

    // Variables declaration 
    public static boolean closeFile = false;
    private static Dial ProgressBar1;
    private static Dial ProgressBar2;
    private static Dial ProgressBar3;
    
    private static JLabel developerName;
    private static JLabel temperatureTitle;
    private static JLabel pressureTitle;
    private static JLabel fuelTitle;
    
    private JSlider pressure_slider;
    private JSlider temperature_slider;
    private JSlider fuel_slider;
    private JCheckBox smoke_slider1;
    private JCheckBox smoke_slider2;
    private JCheckBox smoke_slider3;
    private JCheckBox smoke_slider4;
    
    private JButton close_btn, randomize_btn;
    private JTextField pressure_tf;
    private JTextField temperature_tf;
    private JTextField fuel_tf;
    private JTextField smoke_tf1;
    private JTextField smoke_tf2;
    private JTextField smoke_tf3;
    private JTextField smoke_tf4;
    
    private JLabel pressure_l;
    private JLabel temperature_l;
    private JLabel fuel_l;
    private JLabel smoke_l1;
    private JLabel smoke_l2;
    private JLabel smoke_l3;
    private JLabel smoke_l4;
    
    private static JLabel pressureLamb_l;
    private static JLabel temperatureLamb_l;
    private static JLabel fuelLamb_l;
    private static Lamb smokeLamb_l1;
    private static Lamb smokeLamb_l2;
    private static Lamb smokeLamb_l3;
    private static Lamb smokeLamb_l4;
    
    private JLabel pressureConst_l;
    private JLabel temperatureConst_l;
    private JLabel fuelConst_l;
    private JLabel smokeConst_l1;
    private JLabel smokeConst_l2;
    private JLabel smokeConst_l3;
    private JLabel smokeConst_l4;
    
    private static TemperatureSensor temperature_sensor;
    private static PressureSensor pressure_sensor;
    private static FuelSensor fuel_sensor;
    public static SmokeSensor smoke_sensor1, smoke_sensor2, smoke_sensor3, smoke_sensor4;
    
    private MagnaticMedium file;
    
    public static Controller create() {
        return new Controller();
    }

    /**
     * Creates new form Controller
     */
    
    private Controller() {
        
        
        // since the 'MagnaticMedium' is implemented in Senglton Patern
        file = MagnaticMedium.getFile();
        
        temperature_sensor = new TemperatureSensor(0,100);
        pressure_sensor = new PressureSensor(0,100);
        fuel_sensor = new FuelSensor(0,100);
        smoke_sensor1 = new SmokeSensor();
        smoke_sensor2 = new SmokeSensor();
        smoke_sensor3 = new SmokeSensor();
        smoke_sensor4 = new SmokeSensor();
        
        ProgressBar1 = temperature_sensor.dial();
        ProgressBar2 = pressure_sensor.dial();
        ProgressBar3 = fuel_sensor.dial();
        
        developerName = new JLabel("Akram Abu Khousa   |   20151238");
        developerName.setForeground(Color.gray);
        developerName.setBounds(250,500,300,30);
        
        temperatureTitle = new JLabel("Temerature Dial");
        pressureTitle = new JLabel("Pressure Dial");
        fuelTitle = new JLabel("Fuel Dial");
        
        temperatureTitle.setBounds(80,60,150,30);
        pressureTitle.setBounds(320,60,150,30);
        fuelTitle.setBounds(550,60,150,30);
        
        pressure_slider = new JSlider();
        pressure_slider.setMinimum(pressure_sensor.min());
        pressure_slider.setMaximum(pressure_sensor.max());
        pressure_slider.setValue(50);
        pressure_slider.setBounds(120,203,150,30);
        temperature_slider = new JSlider();
        temperature_slider.setMinimum(temperature_sensor.min());
        temperature_slider.setMaximum(temperature_sensor.max());
        temperature_slider.setValue(50);
        temperature_slider.setBounds(120,243,150,30);
        fuel_slider = new JSlider();
        fuel_slider.setMinimum(fuel_sensor.min());
        fuel_slider.setMaximum(fuel_sensor.max());
        fuel_slider.setValue(50);
        fuel_slider.setBounds(120,280,153,30);
        
        smoke_slider1 = new JCheckBox("No Detection yet");
        smoke_slider1.setSelected(false);
        smoke_slider1.setBounds(120,323,150,30);
        
        smoke_slider2 = new JCheckBox("No Detection yet");
        smoke_slider2.setSelected(false);
        smoke_slider2.setBounds(120,363,150,30);
        
        smoke_slider3 = new JCheckBox("No Detection yet");
        smoke_slider3.setSelected(false);
        smoke_slider3.setBounds(120,403,150,30);
        
        smoke_slider4 = new JCheckBox("No Detection yet");
        smoke_slider4.setSelected(false);
        smoke_slider4.setBounds(120,443,150,30);
        
        close_btn = new JButton("Close Magnatic");
        close_btn.setBounds(535,430,150,35);
        randomize_btn = new JButton("Randomize");
        randomize_btn.setBounds(535,200,150,35);
        
        pressure_l = new JLabel("Pressure");
        pressure_l.setBounds(20,200,100,35);
        temperature_l = new JLabel("Temperature");
        temperature_l.setBounds(20,240,100,35);
        fuel_l = new JLabel("Fuel");
        fuel_l.setBounds(20,280,100,35);
        smoke_l1 = new JLabel("First Smoke");
        smoke_l1.setBounds(20,320,100,35);
        smoke_l2 = new JLabel("Second Smoke");
        smoke_l2.setBounds(20,360,100,35);
        smoke_l3 = new JLabel("Third Smoke");
        smoke_l3.setBounds(20,400,100,35);
        smoke_l4 = new JLabel("Fourth Smoke");
        smoke_l4.setBounds(20,440,100,35);
        
        pressureLamb_l = new JLabel("Pressure Lamb");
        pressureLamb_l.setBounds(350,200,200,35);
        temperatureLamb_l = new JLabel("Temperature Lamb");
        temperatureLamb_l.setBounds(350,240,200,35);
        fuelLamb_l = new JLabel("Fuel Lamb");
        fuelLamb_l.setBounds(350,280,200,35);
        smokeLamb_l1 = new Lamb("First Smoke Lamb",0);
        smokeLamb_l1.setBounds(350,320,200,35);
        smokeLamb_l2 = new Lamb("Second Smoke Lamb",0);
        smokeLamb_l2.setBounds(350,360,200,35);
        smokeLamb_l3 = new Lamb("Third Smoke Lamb",0);
        smokeLamb_l3.setBounds(350,400,200,35);
        smokeLamb_l4 = new Lamb("Fourth Smoke Lamb",0);
        smokeLamb_l4.setBounds(350,440,200,35);
        
        smoke_sensor1.lamb(smokeLamb_l1);
        smoke_sensor2.lamb(smokeLamb_l2);
        smoke_sensor3.lamb(smokeLamb_l3);
        smoke_sensor4.lamb(smokeLamb_l4);
        
        pressureConst_l = new JLabel("(" + getSensorCurrentValue(pressure_sensor) + ")");
        pressureConst_l.setBounds(280,200,100,35);
        temperatureConst_l = new JLabel("(" + getSensorCurrentValue(temperature_sensor) + ")");
        temperatureConst_l.setBounds(280,240,100,35);
        fuelConst_l = new JLabel("(" + getSensorCurrentValue(fuel_sensor) + ")");
        fuelConst_l.setBounds(280,280,100,35);
        smokeConst_l1 = new JLabel(smoke_sensor1.detect()+"");
        smokeConst_l1.setBounds(280,320,100,35);
        smokeConst_l2 = new JLabel(smoke_sensor2.detect()+"");
        smokeConst_l2.setBounds(280,360,100,35);
        smokeConst_l3 = new JLabel(smoke_sensor3.detect()+"");
        smokeConst_l3.setBounds(280,400,100,35);
        smokeConst_l4 = new JLabel(smoke_sensor4.detect()+"");
        smokeConst_l4.setBounds(280,440,100,35);
        
        add(close_btn);
        add(randomize_btn);
        add(pressure_l);
        add(temperature_l);
        add(temperatureTitle);
        add(pressureTitle);
        add(fuelTitle);
        add(fuel_l);
        add(smoke_l1);
        add(smoke_l2);
        add(smoke_l3);
        add(smoke_l4);
        add(pressureLamb_l);
        add(temperatureLamb_l);
        add(fuelLamb_l);
        add(smokeLamb_l1);
        add(smokeLamb_l2);
        add(smokeLamb_l3);
        add(smokeLamb_l4);
        add(pressureConst_l);
        add(temperatureConst_l);
        add(fuelConst_l);
        add(smokeConst_l1);
        add(smokeConst_l2);
        add(smokeConst_l3);
        add(smokeConst_l4);

        add(pressure_slider);
        add(temperature_slider);
        add(fuel_slider);
        add(smoke_slider1);
        add(smoke_slider2);
        add(smoke_slider3);
        add(smoke_slider4);
        add(developerName);
        
        
        /*
        * Slider Components to set sensors values for testing
        */
        pressure_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pressure_sensor.currentValue(pressure_slider.getValue());
                pressureConst_l.setText(String.valueOf(getSensorCurrentValue(pressure_sensor))); 
                ProgressBar2.setValue(getSensorCurrentValue(pressure_sensor));
                pressureLamb_l.setForeground(getSensorLambColor(pressure_sensor));
            }
        });
        temperature_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temperature_sensor.currentValue(temperature_slider.getValue());  
                temperatureConst_l.setText(String.valueOf(getSensorCurrentValue(temperature_sensor)));
                ProgressBar1.setValue(getSensorCurrentValue(temperature_sensor)); 
                temperatureLamb_l.setForeground(getSensorLambColor(temperature_sensor));              
            }
        });
        fuel_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fuel_sensor.currentValue(fuel_slider.getValue()); 
                fuelConst_l.setText(String.valueOf(getSensorCurrentValue(fuel_sensor)));
                ProgressBar3.setValue(getSensorCurrentValue(fuel_sensor));
                fuelLamb_l.setForeground(getSensorLambColor(fuel_sensor));                  
            }
        });
        smoke_slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(smoke_slider1.isSelected()){
                    smoke_slider1.setText("is Detecting Now");
                    smoke_sensor1.detect(true);
                    smokeConst_l1.setText("Danger");
                }else{
                    smoke_slider1.setText("No Detection yet");
                    smoke_sensor1.detect(false);
                    smokeConst_l1.setText("Safe");
                    
                }  
                smokeLamb_l1.setForeground(smoke_sensor1.lamb().color());
            }
        });
        smoke_slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(smoke_slider2.isSelected()){
                    smoke_slider2.setText("is Detecting Now");
                    smoke_sensor2.detect(true);
                    smokeConst_l2.setText("Danger");
                }else{
                    smoke_slider2.setText("No Detection yet");
                    smoke_sensor2.detect(false);
                    smokeConst_l2.setText("Safe");
                    
                }
                smokeLamb_l2.setForeground(smoke_sensor2.lamb().color());
            }
        });
        smoke_slider3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(smoke_slider3.isSelected()){
                    smoke_slider3.setText("is Detecting Now");
                    smoke_sensor3.detect(true);
                    smokeConst_l3.setText("Danger");
                }else{
                    smoke_slider3.setText("No Detection yet");
                    smoke_sensor3.detect(false);
                    smokeConst_l3.setText("Safe");
                    
                }
                smokeLamb_l3.setForeground(smoke_sensor3.lamb().color());
            }
        });
        smoke_slider4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(smoke_slider4.isSelected()){
                    smoke_slider4.setText("is Detecting Now");
                    smoke_sensor4.detect(true);
                    smokeConst_l4.setText("Danger");
                }else{
                    smoke_slider4.setText("No Detection yet");
                    smoke_sensor4.detect(false);
                    smokeConst_l4.setText("Safe");
                    
                }
                smokeLamb_l4.setForeground(smoke_sensor4.lamb().color());
            }
        });
        
        
        /*
        *  to close Logging file and save data
        */
        close_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                closeFile = true;
                file.closeFile();
                close_btn.setBounds(510,430,175,35);
                close_btn.setBackground(Color.green);
                close_btn.setText(close_btn.getText() + " ( Saved )");
            }
        });
        
        /*
        *  set Random values of Sensor for testing
        */
        randomize_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                // Set timer to change and read sensors value every one Second
                Timer tt = new Timer();
                TimerTask tt_task;
                tt_task = new TimerTask() {
                    @Override
                    public void run() {
                        pressure_sensor.currentValue((int) Math.round(Math.random()*100));
                        temperature_sensor.currentValue((int) Math.round(Math.random()*100));
                        fuel_sensor.currentValue((int) Math.round(Math.random()*100));
                        
                        ProgressBar1.setValue(getSensorCurrentValue(temperature_sensor));
                        ProgressBar2.setValue(getSensorCurrentValue(pressure_sensor));
                        ProgressBar3.setValue(getSensorCurrentValue(fuel_sensor));


                        pressureLamb_l.setForeground(getSensorLambColor(pressure_sensor));
                        temperatureLamb_l.setForeground(getSensorLambColor(temperature_sensor));
                        
                        // Set Timer to delay lighting Fule lamb for 30 mili-secomds
                        new Timer().schedule(new TimerTask(){
                            public void run() {
                               fuelLamb_l.setForeground(getSensorLambColor(fuel_sensor));
                            }
                        }, 30);  
                    }
                };
                
                if(randomize_btn.getBackground().equals(Color.green)){
                    randomize_btn.setBackground(Color.orange);
                    close_btn.setEnabled(true);
                    pressure_slider.setEnabled(true);
                    temperature_slider.setEnabled(true);
                    fuel_slider.setEnabled(true);
                    smoke_slider1.setEnabled(true);
                    smoke_slider2.setEnabled(true);
                    smoke_slider3.setEnabled(true);
                    smoke_slider4.setEnabled(true);
                    
                    tt_task.cancel();
                    
                    
                }else{
                    randomize_btn.setBackground(Color.green);
                    pressure_slider.setEnabled(false);
                    temperature_slider.setEnabled(false);
                    fuel_slider.setEnabled(false);
                    smoke_slider1.setEnabled(false);
                    smoke_slider2.setEnabled(false);
                    smoke_slider3.setEnabled(false);
                    smoke_slider4.setEnabled(false);
                    
                    tt.schedule(tt_task, 1000, 2000);
                    
                }

            }
        });
        

        setDefaultCloseOperation(exit());

        ProgressBar1.setToolTipText("");
        ProgressBar1.setValue(getSensorCurrentValue(temperature_sensor));
        ProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProgressBar1.setStringPainted(true);

        ProgressBar2.setValue(getSensorCurrentValue(pressure_sensor));
        ProgressBar2.setStringPainted(true);

        ProgressBar3.setValue(getSensorCurrentValue(fuel_sensor));
        ProgressBar3.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ProgressBar1, 100 , 202, 300)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(ProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 50)
                .addComponent(ProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(414, Short.MAX_VALUE))
        );

        pack();
  

   
    }

    /*
    *  to Close Logging file befor exiting
    */
    public int exit(){   
        file.closeFile();
        return javax.swing.WindowConstants.EXIT_ON_CLOSE;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Controller.create().setVisible(true);
        
        /* 
        *  to apply multi threading in the main method moreover in the Timers
        */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                testLambs();
            }
        });
    }

    
    public static void testLambs(){
        new Timer().schedule(new TimerTask(){
            public void run() {
                smokeLamb_l1.setForeground(smoke_sensor1.lamb().color());
                smokeLamb_l2.setForeground(smoke_sensor2.lamb().color());
                smokeLamb_l3.setForeground(smoke_sensor3.lamb().color());
                smokeLamb_l4.setForeground(smoke_sensor4.lamb().color());
            }
        }, 50);
    }
    

//    public static void pollSensors(){
//        
//        Timer tt = new Timer();
//        tt.schedule(new TimerTask(){
//            @Override
//            public void run() {
//       
//                ProgressBar1.setValue(getSensorCurrentValue(temperature_sensor));
//                ProgressBar2.setValue(getSensorCurrentValue(pressure_sensor));
//                ProgressBar3.setValue(getSensorCurrentValue(fuel_sensor));
//
//
//                pressureLamb_l.setForeground(getSensorLambColor(pressure_sensor));
//                temperatureLamb_l.setForeground(getSensorLambColor(temperature_sensor));
//                
//                // Set Timer to delay lighting Fule lamb for 30 mili-secomds
//                new Timer().schedule(new TimerTask(){
//                    public void run() {
//                       fuelLamb_l.setForeground(getSensorLambColor(fuel_sensor));
//                    }
//                }, 30);
//                
//            }
//        }, 0, 1000);
//        
//        
//    }


    /*
    *  To Read Sensors values from here
    */
    public static int getSensorCurrentValue(Sensor sensor){
        if(sensor.safe()){
            sensor.readings = 0;
        }else{
            sensor.readings++;
        }
        return sensor.currentValue();
    }
    
    /*
    *  to get Lambs Color in order to display to the Pilot
    */
    public static Color getSensorLambColor(Sensor sensor){
        if(sensor.readings > 3){
            sensor.lamb().color(1);
        }else{
            sensor.lamb().color(0);
        }
        return sensor.lamb().color();
    }
    
    /*
    *  when Smoke Sensors detect a smoke sign, depending on its Timer (Deadline - 2 sec) it will generate an Event by Calling this method
    */
    public static boolean smokeInterrupt(SmokeSensor s){
        MagnaticMedium.getFile().log("!$! Interrupting "); 
        s.lamb().color(1);
        return true;
    }
    
    /*
    *  when Smoke Sensors no longer detect a smoke sign, depending on its Timer (Deadline - 4 sec) it will generate an Event by Calling this method
    */
    public static boolean smokeDeInterrupt(SmokeSensor s){
        MagnaticMedium.getFile().log("!$! De-Interrupting "); 
        s.lamb().color(0);
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
