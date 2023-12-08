package frc.robot.Auto.Actions;

import frc.robot.subsystems.TankDrive;

public class TurnRightAction{
  TankDrive mAutoDrive = new TankDrive();
  
  public void finalTurnRightACtion(){
    mAutoDrive.outMotoresAuto(-0.6, -0.6, -0.6, -0.6);
  }
}