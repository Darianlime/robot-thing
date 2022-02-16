
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveTime extends CommandBase {
  private final Drive drive;
  private final double speed;
  private final double duration;
  private long startTime;
  /** Creates a new DriveTime. */
  public DriveTime( Drive drive ,double duration, double speed) {
    this.drive = drive;
    this.duration = duration * 1000;
    this.speed = speed;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      startTime = System.currentTimeMillis();
     // drive.driveForward(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  //  drive.driveForward(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // drive.driveForward(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return System.currentTimeMillis() - startTime >= duration;
  }
}
