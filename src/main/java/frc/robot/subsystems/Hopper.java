package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {

    //-----------------------características---------------------//
    //Hardware
    TalonSRX HmotorD;
    TalonSRX HmotorU;
    
    //Variables de logica
    double velocidad;

    //INPUTS

    //OUTPUTS

    //-----------------------características---------------------//

    // constructor que se ejecuta una vez
    public Hopper(){
        HmotorD = new TalonSRX(Constants.kHopperD);
        HmotorU = new TalonSRX(Constants.kHopperU);
        velocidad = 0;
    }
//ver dirección
    public void moveHopper(double yInput){
        if(Math.abs(yInput)>Constants.kStickTolerance){ //valor absoluto para ir atrás tmbn
            HmotorD.set(ControlMode.PercentOutput, -yInput);
        }else{
            HmotorD.set(ControlMode.PercentOutput, 0);
        }
    }
    public void upperHopper(double yValue){
        if(yValue>Constants.kStickTolerance){
            HmotorU.set(ControlMode.PercentOutput, yValue);
        }else{
            HmotorU.set(ControlMode.PercentOutput, 0);
        }
    }
    

    public void stop(){
        velocidad=0;
    }
}
    