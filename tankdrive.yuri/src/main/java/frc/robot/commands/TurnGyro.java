// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.naming.PartialResultException;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class TurnGyro extends CommandBase {
  private final Drive drive;
  private final double speed;
  private final double durationTurn;
  private long startTime;
  //private ADXRS450_Gyro gyro;
  /** Creates a new DriveWithGyro. */
  public TurnGyro(Drive drive, double durationTurn,double speed) {
    this.speed = speed;
    this.drive = drive;
    this.durationTurn = durationTurn * 1000;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
   // gyro.reset();
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
    return System.currentTimeMillis() - startTime >= durationTurn;
  }
}
