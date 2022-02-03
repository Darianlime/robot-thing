// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drive extends SubsystemBase {
  WPI_TalonSRX leftTalonOutside = new WPI_TalonSRX(Constants.LEFT_TALON_OUTSIDE);
  WPI_TalonSRX leftTalonInside = new WPI_TalonSRX(Constants.LEFT_TALON_INSIDE);
  MotorControllerGroup leftTalon = new MotorControllerGroup(leftTalonOutside, leftTalonInside);
  WPI_TalonSRX rightTalonOutside = new WPI_TalonSRX(Constants.RIGHT_TALON_OUTSIDE);
  WPI_TalonSRX rightTalonInside = new WPI_TalonSRX(Constants.RIGHT_TALON_INSIDE);
  MotorControllerGroup rightTalon = new MotorControllerGroup(rightTalonOutside, rightTalonInside);
  DifferentialDrive drive = new DifferentialDrive(leftTalon, rightTalon);
  /** Creates a new Drive. */
  public Drive() {
    leftTalonInside.setNeutralMode(NeutralMode.Coast);
    leftTalonOutside.setNeutralMode(NeutralMode.Coast);
    rightTalonInside.setNeutralMode(NeutralMode.Coast);
    rightTalonOutside.setNeutralMode(NeutralMode.Coast);
    leftTalonInside.setInverted(true);
    leftTalonOutside.setInverted(true);
  }
  // public void driveWithJoystick(Joystick joystickLeft, Joystick joystickRight) {
    
  //   double leftSpeed = joystickLeft.getRawAxis(1);
  //   double rightSpeed  = joystickRight.getRawAxis(1);
    

  //   drive.tankDrive(-0.60 * leftSpeed, 0.60 * rightSpeed);}
    // Driving to the joystick being home
    public void driveWithJoystick(Joystick
     joystickleft, Joystick joystickright) {
      double leftspeed = joystickleft.getRawAxis(1);
      double rightspeed = joystickright.getRawAxis(1);
      leftTalonOutside.set(leftspeed);
       leftTalonInside.set(leftspeed);
       rightTalonInside.set(rightspeed);
      rightTalonOutside.set(rightspeed);

      
     }

     public void resetGyro() {
       //gyro.reset();
     }
    
    public void driveForward(double speed) {
      leftTalonOutside.set(speed);
      leftTalonInside.set(speed);
      rightTalonInside.set(speed);
      rightTalonOutside.set(speed);
    }

    public void driveRight(double speed) {
      leftTalonOutside.set(0.3);
      leftTalonInside.set(0.3);
      rightTalonInside.set(speed);
      rightTalonOutside.set(speed);
    }

    public void autoDriveForward() {
      leftTalonOutside.set(-0.4);
      leftTalonInside.set(-0.4);
      rightTalonInside.set(-0.4);
      rightTalonOutside.set(-0.4);

    }

    public void autoDriveBack() {
      leftTalonOutside.set(0.4);
      leftTalonInside.set(0.4);
      rightTalonInside.set(0.4);
      rightTalonOutside.set(0.4);
    }
//this pivot on center 
    public void autoDriveLeft() {
      leftTalonOutside.set(0.4);
      leftTalonInside.set(0.4);
      rightTalonInside.set(-0.4);
      rightTalonOutside.set(-0.4);
    }

    public void autoDriveRight() {
      leftTalonOutside.set(-0.4);
      leftTalonInside.set(-0.4);
      rightTalonInside.set(0.4);
      rightTalonOutside.set(0.4);
    }

    public void squareGyro() {
      
      }
          
        
    

    public void autoDriveStop(){
      leftTalonOutside.set(0);
      leftTalonInside.set(0);
      rightTalonInside.set(0);
      rightTalonOutside.set(0);
    }


  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
}
