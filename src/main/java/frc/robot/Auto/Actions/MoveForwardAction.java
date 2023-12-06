package frc.robot.Auto.Actions;

import frc.robot.subsystems.TankDrive;

public class MoveForwardAction{
  TankDrive mAutoDrive = new TankDrive();
  
  public void finalMoveForwardACtion(){
    mAutoDrive.outMotoresAuto(0.3, 0.3, -0.3, -0.3);;
  }
}