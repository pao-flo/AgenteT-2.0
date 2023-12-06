package frc.robot.ControlBoard;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class control {
    //hardware
    XboxController driver;
    XboxController mecanisms;
    double tolerance;


    public control(){
        driver = new XboxController(Constants.kChassisPort);
        mecanisms = new XboxController(Constants.kMecanism);
        tolerance = Constants.kStickTolerance;
    }
    //--------------funciones-----------//
    public double left_x_stick_driver(){
        double xValue = driver.getRawAxis(0);
        if (Math.abs(xValue)<tolerance){
            xValue = 0;
        }
        return xValue;
    }
    public double left_y_stick_driver(){
        double yValue = driver.getRawAxis(1);
        if (Math.abs(yValue)<tolerance){
            yValue = 0;
        }
        return yValue;
    }
    public double right_x_stick_driver(){
        double xValue = driver.getRawAxis(4);
        if (Math.abs(xValue)<tolerance){
            xValue = 0;
        }
        return xValue;
    }
    public double right_y_stick_driver(){
        double yValue = driver.getRawAxis(5);
        if (Math.abs(yValue)<tolerance){
            yValue = 0;
        }
        return yValue;
    }

    public boolean driver_a_button(){
        return driver.getAButton();
    }
    public boolean driver_b_button(){
        return driver.getBButton(); //getWhenPress, whenReleased
    }
    public boolean driver_x_button(){
        return driver.getXButton();
    }
    public boolean driver_y_button(){
        return driver.getYButton();
    }

    public double left_trigger_driver(){
        double tValue = driver.getLeftTriggerAxis();
        if (Math.abs(tValue)<tolerance){
            tValue = 0;
        }
        return tValue;
    }
    public double right_trigger_driver(){
        double tValue = driver.getRightTriggerAxis();
        if (Math.abs(tValue)<tolerance){
            tValue = 0;
        }
        return tValue;
    }
    //mecanismos
    public double left_x_stick_mecanisms(){
        double xValue = mecanisms.getRawAxis(1);
        if (Math.abs(xValue)<tolerance){
            xValue = 0;
        }
        return xValue;
    }
    public double left_y_stick_mecanisms(){
        double yValue = mecanisms.getRawAxis(0);
        if (Math.abs(yValue)<tolerance){
            yValue = 0;
        }
        return yValue;
    }
    public double right_x_stick_mecanisms(){
        double xValue = mecanisms.getRawAxis(3);
        if (Math.abs(xValue)<tolerance){
            xValue = 0;
        }
        return xValue;
    }
    public double right_y_stick_mecanisms(){
        double yValue = mecanisms.getRawAxis(4);
        if (Math.abs(yValue)<tolerance){
            yValue = 0;
        }
        return yValue;
    }

    public boolean mecanisms_a_button(){
        return mecanisms.getAButton();
    }
    public boolean mecanisms_b_button(){
        return mecanisms.getBButton();
    }
    public boolean mecanisms_x_button(){
        return mecanisms.getXButton();
    }
    public boolean mecanisms_y_button(){
        return mecanisms.getYButton();
    }

    public double left_trigger_mecanisms(){
        double tValue = mecanisms.getLeftTriggerAxis();
        if (Math.abs(tValue)<tolerance){
            tValue = 0;
        }
        return tValue;
    }
    public double right_trigger_mecanisms(){
        double tValue = mecanisms.getRightTriggerAxis();
        if (Math.abs(tValue)<tolerance){
            tValue = 0;
        }
        return tValue;
    }


}

