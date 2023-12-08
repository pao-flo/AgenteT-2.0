package frc.robot.Auto.Actions;

import frc.robot.subsystems.TankDrive;

public class TurnLeftAction{
  TankDrive mAutoDrive = new TankDrive();
  
  public void finalTurnLeftACtion(){
    mAutoDrive.outMotoresAuto(0.6, 0.6, 0.6, 0.6);
  }
}