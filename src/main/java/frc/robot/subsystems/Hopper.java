package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    public void moveDownHopper(boolean button, boolean buttonBakc){
        if(button){
            HmotorD.set(ControlMode.PercentOutput, 0.5);
        }else if(buttonBakc){
            HmotorD.set(ControlMode.PercentOutput, -0.5);
        }else{
            HmotorD.set(ControlMode.PercentOutput, 0);
        }
    }
    public void moveUpperHopper(boolean button, boolean buttonBack){
        if(button){
            HmotorU.set(ControlMode.PercentOutput, -0.5);
        }else if(buttonBack){
            HmotorU.set(ControlMode.PercentOutput, 0.5);
        }
        else{
            HmotorU.set(ControlMode.PercentOutput, 0);
        }
    }
    public void spitHopper(boolean getBButton){
        if(getBButton == true){
            HmotorD.set(ControlMode.PercentOutput, -0.5);
            HmotorU.set(ControlMode.PercentOutput, 0.5);
        }else{
            HmotorD.set(ControlMode.PercentOutput, 0);
            HmotorU.set(ControlMode.PercentOutput, 0);
        }
    }

    
    

    public void stop(){
        velocidad=0;
    }
}
    
