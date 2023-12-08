package frc.robot.Auto.Actions;

import frc.robot.subsystems.IntakeBox;

public class LeaveBoxAction{
  IntakeBox mIntakeBox = new IntakeBox();
  
  public void finalLeaveBoxAction(){
    mIntakeBox.LeaveBoxAuto(-0.4);
  }
}