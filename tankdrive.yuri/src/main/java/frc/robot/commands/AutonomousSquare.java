// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;

/** Add your docs here. */
public class AutonomousSquare extends SequentialCommandGroup {
    public AutonomousSquare(Drive drive,double distance ,double speed) {
        addCommands(
            new DriveForwardEncoder(drive, distance, speed),
            new TurnGyro(drive, speed),
            new DriveForwardEncoder(drive, distance, speed),
            new TurnGyro(drive, speed),
            new DriveForwardEncoder(drive, distance, speed),
            new TurnGyro(drive, speed),
            new DriveForwardEncoder(drive, distance, speed),
            new TurnGyro(drive, speed)

        );
    }
}
