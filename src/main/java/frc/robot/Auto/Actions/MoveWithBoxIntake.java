package frc.robot.Auto.Actions;

import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.IntakeBox;

public class MoveWithBoxIntake{
  TankDrive mAutoDrive = new TankDrive();
  IntakeBox mAuIntakeBox = new IntakeBox();
  
  public void finalMoveWithBoxIntake(){
    mAutoDrive.outMotoresAuto(-0.3, -0.3, 0.3, 0.3);
    mAuIntakeBox.EatBoxAuto(0.4);
  }
}