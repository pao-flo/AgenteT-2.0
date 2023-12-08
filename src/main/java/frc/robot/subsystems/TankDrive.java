package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
    public AHRS navx;
    public double angle;

    //-----------------------características---------------------//
    //Hardware
    TalonSRX Right1;
    TalonSRX Right2;
    TalonSRX Left1;
    TalonSRX Left2;
    
    //Variables de logica
    double velocidad;
    double rightSpeed;
    double leftSpeed;
    double realRightSpeed;
    double realLeftSpeed;

    //INPUTS

    //OUTPUTS

    //-----------------------características---------------------//

    // constructor que se ejecuta una vez
    public TankDrive(){
        Right1 = new TalonSRX(Constants.kDriveRight1);
        Right2 = new TalonSRX(Constants.kDriveRight2);
        Left1 = new TalonSRX(Constants.kDriveLeft1);
        Left2 = new TalonSRX(Constants.kDriveLeft2);
        //cosas que iniciamos
        velocidad=0;
        realLeftSpeed=0;
        realRightSpeed=0;

        try{
            navx = new AHRS(SPI.Port.kMXP);
            Timer.delay(0.5);
            navx.reset();
            Timer.delay(0.5);
        }
        catch(Exception e){
            System.out.println("navx not working");
        }
    }

    //------------------------funciones del subsystema---------------//
    public void avanzar(double yInput, double xInput){
        if(yInput>0){
        rightSpeed = yInput + xInput;
        leftSpeed = yInput - xInput;
        }else{
        rightSpeed = yInput + xInput;
        leftSpeed = yInput - xInput;
        }

        if(Math.abs(realRightSpeed)>Math.abs(rightSpeed)){
            realRightSpeed = realRightSpeed - 0.05;
        }else if(Math.abs(realRightSpeed)<Math.abs(rightSpeed)){
            realRightSpeed = realRightSpeed + 0.05;
        }else{
            realRightSpeed = rightSpeed;
        }

        //cosas de la funcion
        Right1.set(ControlMode.PercentOutput, rightSpeed);
        Right2.set(ControlMode.PercentOutput, rightSpeed);
        Left1.set(ControlMode.PercentOutput, -leftSpeed);
        Left2.set(ControlMode.PercentOutput, -leftSpeed);
    }

    public void outMotoresAuto( double frontRightDemand, double backRightDemand, 
    double frontLeftDemand, double backleftDemand ){
      Right1.set(ControlMode.PercentOutput, frontRightDemand);
      Right2.set(ControlMode.PercentOutput, backRightDemand);
      Left1.set(ControlMode.PercentOutput, frontLeftDemand);
      Left2.set(ControlMode.PercentOutput, backleftDemand);
    }

    public void stop(){
        velocidad=0;
    }

    public void outputTelemetry(){
        SmartDashboard.putNumber("navx angle", navx.getAngle());
    }
    

    //------------------------funciones del subsystema---------------//
}
    
