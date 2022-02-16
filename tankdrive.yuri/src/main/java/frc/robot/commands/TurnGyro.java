// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

//Creates and intializes variables 
public class TurnGyro extends CommandBase {
  private final Drive drive;
  private final double speed;
  /** Creates a new DriveWithGyro. */
  public TurnGyro(Drive drive, double speed) {
    this.speed = speed;
    this.drive = drive;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.gyroReset();
    // gyro.calibrate();
    drive.driveRight(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.driveRight(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.autoDriveStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return drive.getGyroAngle() >= 90;
  }
}
