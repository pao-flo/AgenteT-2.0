package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeBox extends SubsystemBase {

    //-----------------------características---------------------//
    //Hardware
    TalonSRX RCintake;
    TalonSRX LCintake;
    
    //Variables de logica
    double velocidad;

    //INPUTS

    //OUTPUTS

    //-----------------------características---------------------//

    // constructor que se ejecuta una vez
    public IntakeBox(){
        RCintake = new TalonSRX(Constants.kCIntakeR);
        LCintake = new TalonSRX(Constants.kCIntakeL);
        velocidad = 0;
    }
//ver dirección
    /*public void getBox(boolean getXButton, boolean getYButton){
        if (getXButton == true){
            velocidad=0.4;
            getYButton=false;
            RCintake.set(ControlMode.PercentOutput, velocidad);
            LCintake.set(ControlMode.PercentOutput, velocidad);
        }else if(getYButton == true){
            velocidad=-0.4;
            getXButton=false;
            RCintake.set(ControlMode.PercentOutput, velocidad);
            LCintake.set(ControlMode.PercentOutput, velocidad);
        }else{
            RCintake.set(ControlMode.PercentOutput, 0);
            LCintake.set(ControlMode.PercentOutput, 0);
        }

    }*/
    public void eatBox(double lstick){
        if(lstick>Constants.kStickTolerance){
            RCintake.set(ControlMode.PercentOutput, lstick);
            LCintake.set(ControlMode.PercentOutput, lstick);
        }else {
            RCintake.set(ControlMode.PercentOutput, 0);
            LCintake.set(ControlMode.PercentOutput, 0);
        }

    }
    

    public void stop(){
        velocidad=0;
    }

    //------------------------funciones del subsystema---------------//
}
    