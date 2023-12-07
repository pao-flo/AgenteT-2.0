package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.ControlBoard.control;
import frc.robot.subsystems.Hopper;
//import frc.robot.subsystems.Intake;
//import frc.robot.subsystems.IntakeBox;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.Leds.State;
import frc.robot.subsystems.Leds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Auto
import frc.robot.Auto.Actions.GetTimeAction;
import frc.robot.Auto.Actions.MoveForwardAction;
import frc.robot.Auto.Actions.StopAction;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private TankDrive mTankDrive;
  private control mControlBoard;
  private Hopper mHopper;
  private Shooter mShooter;
  private Leds mLeds;
  //private Intake mIntake;
  //private IntakeBox mIntakeBox;
  
    //Inicialización acciones autónomo
  GetTimeAction mAutoTimer = new GetTimeAction();
  MoveForwardAction mMoveForwardAction = new MoveForwardAction();
  StopAction mStopAction = new StopAction();
  
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
    //mIntake = new Intake();
    //mIntakeBox = new IntakeBox();

  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    //mLeds.setColorDisabled();
    mLeds.SetState(State.IsClimbing);
  }

  @Override
  public void disabledPeriodic() {
    mTankDrive.outputTelemetry();
    mControlBoard.outputTelemetry();
  }

  @Override
  public void autonomousInit() {
    mAutoTimer.autoRelativeTimeControl();
  }

  @Override
  public void autonomousPeriodic() {
    mLeds.SetState(State.Aiming);
    mAutoTimer.autoAbsoluteTimeControl(); //inicializa el timeStap absoluto
    double difTime = mAutoTimer.getAbsoluteTimer()-mAutoTimer.getRelativeTimer();
    if(difTime<3){
      mMoveForwardAction.finalMoveForwardACtion();
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
    mLeds.SetState(State.Disable);
    mTankDrive.avanzar(mControlBoard.left_y_stick_driver(), mControlBoard.right_x_stick_driver());
    double angle = mTankDrive.navx.getYaw();
    SmartDashboard.putNumber("angle", angle);

    //mTankDrive.outputTelemetry();
    mHopper.moveDownHopper(mControlBoard.mecanisms_x_button(),mControlBoard.mecanisms_b_button());
    mHopper.moveUpperHopper(mControlBoard.mecanisms_y_button(),mControlBoard.mecanisms_b_button());
    //mHopper.spitHopper(mControlBoard.mecanisms_b_button());
    mShooter.shoot(mControlBoard.mecanisms_a_button());
    mControlBoard.outputTelemetry();
    //mLeds.setColor();
    //mIntake.eat(mControlBoard.right_trigger_mecanisms(), mControlBoard.left_trigger_mecanisms());
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
