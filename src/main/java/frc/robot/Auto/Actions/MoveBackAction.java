package frc.robot.Auto.Actions;

import frc.robot.subsystems.TankDrive;

public class MoveBackAction{
  TankDrive mAutoDrive = new TankDrive();
  
  public void finalMoveBackACtion(){
    mAutoDrive.outMotoresAuto(0.6, 0.6, -0.6, -0.6);
  }
}