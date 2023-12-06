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
    public void moveHopper(boolean getXButton){
        if(getXButton == true){ //pues si le picas jala si no no
            HmotorD.set(0.5);
        }else{
            HmotorD.set(0);
        }
    }
    public void upperHopper(boolean getYButton){
        if(getYButton == true){
            HmotorU.set(0.5);
        }else{
            HmotorU.set(0);
        }
    }
    public void spitHopper(boolean getBButton){
        if(getBButton == true){
            HmotorD.set(-0.5);
            HmotorU.set(-0.5);
        }else{
            HmotorD.set(0);
            HmotorU.set(0);
        }
    }
    

    public void stop(){
        velocidad=0;
    }
}
    
