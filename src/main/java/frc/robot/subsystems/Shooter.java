package frc.robot.subsystems;

import frc.robot.subsystems.Leds;
import frc.robot.subsystems.Leds.State;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    //-----------------------características---------------------//
    //Hardware
    CANSparkMax shooter;
    TalonSRX HmotorD;
    TalonSRX HmotorU;
    Leds mLeds;
    
    //Variables de logica
    double velocidad;

    //INPUTS

    //OUTPUTS

    //-----------------------características---------------------//

    // constructor que se ejecuta una vez
    public Shooter(){
        shooter = new CANSparkMax(Constants.kShooter, MotorType.kBrushless);
        HmotorD = new TalonSRX(Constants.kHopperD);
        HmotorU = new TalonSRX(Constants.kHopperU);
        //mLeds = new Leds();
        velocidad = 0;
    }
//ver dirección
    public void shoot(Boolean getAButton, boolean rbumper){
        if(getAButton == true){
            shooter.set(-1);
            //mLeds.SetState(State.Aiming);
            
        }else if(rbumper){
            shooter.set(-1);
            HmotorD.set(ControlMode.PercentOutput, 0.3);
            HmotorU.set(ControlMode.PercentOutput, -0.5);
            //mLeds.SetState(State.Aiming);
        }
        else{
            shooter.set(0.0);
            HmotorD.set(ControlMode.PercentOutput, 0);
            HmotorU.set(ControlMode.PercentOutput, 0);
            //mLeds.SetState(State.Teleop);
        }
    }

    public void AutoShoot(double velocity){
        shooter.set(velocity);
        HmotorD.set(ControlMode.PercentOutput, 0.3);
        HmotorU.set(ControlMode.PercentOutput, -0.5);
    }

    /*public void shootWithHopper(boolean rbumper){
        if(rbumper){
            shooter.set(-1);
            HmotorD.set(ControlMode.PercentOutput, 0.5);
            HmotorU.set(ControlMode.PercentOutput, -0.5);
        }else {
            shooter.set(0);
            HmotorD.set(ControlMode.PercentOutput, 0);
            HmotorU.set(ControlMode.PercentOutput, 0);
        }

    }*/
    

    public void stop(){
        velocidad=0;
    }
}