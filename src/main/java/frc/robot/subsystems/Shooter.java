package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    //-----------------------características---------------------//
    //Hardware
    CANSparkMax shooter;
    
    //Variables de logica
    double velocidad;

    //INPUTS

    //OUTPUTS

    //-----------------------características---------------------//

    // constructor que se ejecuta una vez
    public Shooter(){
        shooter = new CANSparkMax(Constants.kShooter, MotorType.kBrushless);
        velocidad = 0;
    }
//ver dirección
    public void shoot(Boolean getAButton){
        if(getAButton == true){
            shooter.set(0.8);
        }else{
            shooter.set(0.8);
        }
    }
    

    public void stop(){
        velocidad=0;
    }
}