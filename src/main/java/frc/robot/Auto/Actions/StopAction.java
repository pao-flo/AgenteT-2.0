package frc.robot.Auto.Actions;

import frc.robot.subsystems.TankDrive;

public class StopAction {
    TankDrive mDriveAuto = new TankDrive();

    public void finalStopAction(){
        mDriveAuto.outMotoresAuto(0, 0, 0, 0);
    }
}