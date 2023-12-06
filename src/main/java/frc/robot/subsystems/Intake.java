package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    //-----------------------características---------------------//
    //Hardware
    TalonSRX intake;
    private final Solenoid piston1 = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.kSolenoid1);
    private final Solenoid piston2 = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.kSolenoid2);
    //Variables de logica
    double velocidad;

    //INPUTS
    boolean statePiston1 = false;
    boolean statePiston2 = false;

    //OUTPUTS

    //-----------------------características---------------------//

    // constructor que se ejecuta una vez
    public Intake(){
        intake = new TalonSRX(Constants.kIntake);
        velocidad = 0;
    }
//ver dirección
    public void eat(double Rtrigger, double Ltrigger){
        double direct=Rtrigger-Ltrigger; //varibale que suma velocidad
        if (Rtrigger>Constants.kStickTolerance){
            intake.set(ControlMode.PercentOutput, Rtrigger);
            piston1.set(true);
            piston2.set(true);
        }else if(Ltrigger>Constants.kStickTolerance){
            intake.set(ControlMode.PercentOutput, -Ltrigger);
            piston1.set(true);
            piston2.set(true);
        }else{
            intake.set(ControlMode.PercentOutput, 0);
            piston1.set(false);
            piston2.set(false);
        }

    }
    
    public void stop(){
        velocidad=0;
    }

    //------------------------funciones del subsystema---------------//
}
    
