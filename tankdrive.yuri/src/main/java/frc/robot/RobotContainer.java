// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.time.Duration;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDriveBack;
import frc.robot.commands.AutoDriveForward;
import frc.robot.commands.AutoDriveLeft;
import frc.robot.commands.AutoDriveRight;
import frc.robot.commands.AutonomousSquare;
import frc.robot.commands.DriveTime;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ResetGyro;
import frc.robot.commands.driveWithJoystick;
import frc.robot.subsystems.Drive;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Drive drive = new Drive();
  public Joystick joystickLeft = new Joystick(0);
  public Joystick joystickRight = new Joystick(1);
  private final driveWithJoystick driveWithJoystick = new driveWithJoystick(drive, joystickLeft, joystickRight);

  // JoystickButton autodriveforward = new JoystickButton(joystickLeft, 3);
  // JoystickButton autodriveback = new JoystickButton(joystickLeft, 2);
  // JoystickButton autodriveleft = new JoystickButton(joystickRight, 4);
  // JoystickButton autodriveRight = new JoystickButton(joystickRight, 5);
  JoystickButton resetgyro = new JoystickButton(joystickLeft, 6);

  private final SendableChooser<Command> m_chooser = new SendableChooser<>();


  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    drive.setDefaultCommand(driveWithJoystick);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // autodriveforward.whileHeld(new AutoDriveForward(drive));
    // autodriveback.whileHeld(new AutoDriveBack(drive));
    // autodriveleft.whileHeld(new AutoDriveLeft(drive));
    // autodriveRight.whileHeld(new AutoDriveRight(drive)); 
    resetgyro.whenPressed(new ResetGyro(drive));

    m_chooser.setDefaultOption("Square Drive", new AutonomousSquare(drive, 3, -0.3));
    SmartDashboard.putData(m_chooser);

  }
    
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
