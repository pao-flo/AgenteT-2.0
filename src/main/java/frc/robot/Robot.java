package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.ControlBoard.control;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeBox;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.Leds.State;
import frc.robot.subsystems.Leds;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

//Auto
import frc.robot.Auto.Actions.GetTimeAction;
import frc.robot.Auto.Actions.MoveBackAction;
import frc.robot.Auto.Actions.MoveForwardAction;
import frc.robot.Auto.Actions.StopAction;
import frc.robot.Auto.Actions.TurnLeftAction;
import frc.robot.Auto.Actions.TurnRightAction;
import frc.robot.Auto.Actions.LeaveBoxAction;
import frc.robot.Auto.Actions.MoveWithBoxIntake;
//import frc.robot.Auto.Actions.ShootAction;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  public AHRS navx;
  double angle;

  private TankDrive mTankDrive;
  private control mControlBoard;
  private Hopper mHopper;
  private Shooter mShooter;
  private Leds mLeds;
  private Intake mIntake;
  private IntakeBox mIntakeBox;
  
  //Inicialización acciones autónomo
  GetTimeAction mAutoTimer = new GetTimeAction();
  MoveForwardAction mMoveForwardAction = new MoveForwardAction();
  StopAction mStopAction = new StopAction();
  TurnLeftAction mTurnLeftAction = new TurnLeftAction();
  TurnRightAction mTurnRightAction = new TurnRightAction();
  MoveBackAction mMoveBackAction = new MoveBackAction();
  LeaveBoxAction mLeaveBoxAction = new LeaveBoxAction();
  MoveWithBoxIntake mMoveWithBoxIntake = new MoveWithBoxIntake();
  //ShootAction mShootAction = new ShootAction();
  
  private static final int PDH_CAN_ID = 1;
  private static final int NUM_PDH_CHANNELS = 24;

  PowerDistribution m_pdh = new PowerDistribution(PDH_CAN_ID,ModuleType.kRev);
  

  @Override
  public void robotInit() {
    m_pdh.setSwitchableChannel(false);
    m_pdh.clearStickyFaults();

    mTankDrive = new TankDrive();
    mControlBoard = new control();
    mHopper = new Hopper();
    mShooter = new Shooter();
    mLeds = new Leds();
    mIntake = new Intake();
    mIntakeBox = new IntakeBox();

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

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    //mLeds.setColorDisabled();
    mLeds.SetState(State.Disable);
  }

  @Override
  public void disabledPeriodic() {
    mTankDrive.outputTelemetry();
    mControlBoard.outputTelemetry();
  }

  @Override
  public void autonomousInit() {
    mAutoTimer.autoRelativeTimeControl();
    angle = navx.getAngle();
  }

  @Override
  public void autonomousPeriodic() {
    mLeds.SetState(State.Auto);
    mAutoTimer.autoAbsoluteTimeControl(); //inicializa el timeStap absoluto
    //Autónomo que solo avanza
    //Angulo para disparar -27.6
    /*double difTime = mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer();
    if(difTime<2.5){
      mMoveForwardAction.finalMoveForwardACtion();
    }
    else mStopAction.finalStopAction();*/

    double difTime = mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer();
    if(difTime<2){
      mMoveForwardAction.finalMoveForwardACtion();
    }else if(difTime>2 && difTime<2.3){
      mTurnRightAction.finalTurnRightACtion();
    }else if(difTime>2.3 && difTime<3.7){
      mMoveForwardAction.finalMoveForwardACtion();
    }else if(difTime>3.7 && difTime<3.9){
      mLeaveBoxAction.finalLeaveBoxAction(); //aqui jala 
    }else if(difTime>3.9 && difTime<4.5){
      mMoveBackAction.finalMoveBackACtion(); //acatoy
    }else if(difTime>4.5 && difTime<5.28){
      mTurnLeftAction.finalTurnLeftACtion();
    }else if(difTime>5.28 && difTime<6.98){
      mMoveWithBoxIntake.finalMoveWithBoxIntake();
    }else if(difTime>6.98 && difTime<8.38){
      mMoveBackAction.finalMoveBackACtion();
    }else if(difTime>8.38 && difTime<8.5){
      mTurnRightAction.finalTurnRightACtion();
    }else if(difTime>8.5  && difTime<8.9){
      mLeaveBoxAction.finalLeaveBoxAction();
    }
    else mStopAction.finalStopAction();
  }
    

  @Override
  public void teleopInit() {
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    mLeds.SetState(State.Teleop);
    mTankDrive.avanzar(mControlBoard.left_y_stick_driver(), mControlBoard.right_x_stick_driver());
    double angle = mTankDrive.navx.getYaw();
    SmartDashboard.putNumber("angle", angle);
    
    mControlBoard.outputTelemetry();
    mIntake.eat(mControlBoard.left_y_stick_mecanisms());
    mIntakeBox.eatBox(mControlBoard.right_y_stick_mecanisms());
    mShooter.shoot(mControlBoard.mecanisms_a_button(), mControlBoard.mecanisms_rigth_bumper());
  
    mHopper.moveDownHopper(mControlBoard.mecanisms_x_button(),mControlBoard.mecanisms_b_button());
    mHopper.moveUpperHopper(mControlBoard.mecanisms_y_button(),mControlBoard.mecanisms_b_button());

    //mShooter.shootWithHopper(mControlBoard.mecanisms_rigth_bumper());
    //mIntakeBox.getBox(mControlBoard.mecanisms_x_button(), mControlBoard.mecanisms_y_button());
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
  
  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
