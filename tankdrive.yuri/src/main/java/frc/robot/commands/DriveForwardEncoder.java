// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveForwardEncoder extends CommandBase {
  Drive drive;
  double distance;
  double speed;
  //int encoder = drive.getEncoder();
  /** Creates a new DriveForwardEncoder. */
  public DriveForwardEncoder(Drive drive, double distance, double speed) {
    this.drive = drive;
    this.distance = distance;
    this.speed = speed;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetEncodersValues();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.driveForwardEncoder(distance, speed);
  //  encoder = drive.getEncoder();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
