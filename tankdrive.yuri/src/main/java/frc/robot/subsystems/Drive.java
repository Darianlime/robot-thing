// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.reflect.Array;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveForwardEncoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

//Controls drivetrain with functions
public class Drive extends SubsystemBase {
  private static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private static final double LEFT_ENCODER_TO_PER_FOOT = 0;
  private static final double RIGHT_ENCODER_TO_PER_FOOT = 0;
  WPI_TalonSRX leftTalonOutside = new WPI_TalonSRX(Constants.LEFT_TALON_OUTSIDE);
  WPI_TalonSRX leftTalonInside = new WPI_TalonSRX(Constants.LEFT_TALON_INSIDE);
  MotorControllerGroup leftTalon = new MotorControllerGroup(leftTalonOutside, leftTalonInside);
  WPI_TalonSRX rightTalonOutside = new WPI_TalonSRX(Constants.RIGHT_TALON_OUTSIDE);
  WPI_TalonSRX rightTalonInside = new WPI_TalonSRX(Constants.RIGHT_TALON_INSIDE);
  MotorControllerGroup rightTalon = new MotorControllerGroup(rightTalonOutside, rightTalonInside);
  DifferentialDrive drive = new DifferentialDrive(leftTalon, rightTalon);

  /** Creates a new Drive. */

  // Coasts robot and inverts left talons
  public Drive() {
    // leftTalonInside.setNeutralMode(NeutralMode.Coast);
    leftTalonOutside.setNeutralMode(NeutralMode.Brake);
    rightTalonInside.setNeutralMode(NeutralMode.Brake);

    rightTalonInside.setSensorPhase(true);
    leftTalonOutside.setSensorPhase(true);
    // rightTalonOutside.setNeutralMode(NeutralMode.Coast);
    rightTalonInside.setInverted(true);
    rightTalonOutside.setInverted(true);
    // Factory Default all hardware to prevent unexpected behavior
    leftTalonOutside.configFactoryDefault();
    rightTalonInside.configFactoryDefault();
    // disable all motor controls
    leftTalonOutside.set(0);
    rightTalonInside.set(0);
    leftTalonOutside.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    rightTalonInside.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    leftTalonInside.follow(leftTalonOutside);
    rightTalonOutside.follow(rightTalonInside);
  }
  // public void driveWithJoystick(Joystick joystickLeft, Joystick joystickRight)
  // {

  // double leftSpeed = joystickLeft.getRawAxis(1);
  // double rightSpeed = joystickRight.getRawAxis(1);

  // drive.tankDrive(-0.60 * leftSpeed, 0.60 * rightSpeed);}

  // Uses joystick to move
  public void driveWithJoystick(Joystick joystickleft, Joystick joystickright) {
    double leftspeed = joystickleft.getRawAxis(1);
    double rightspeed = joystickright.getRawAxis(1);
    leftTalonOutside.set(leftspeed);
    leftTalonInside.set(leftspeed);
    rightTalonInside.set(rightspeed);
    rightTalonOutside.set(rightspeed);

  }

  public void gyroReset() {
    gyro.reset();
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  // public double getLeftEncoder() {
  // return leftEncoder.getAbsolutePosition();
  // }
  public double getEncoderValueLeft() {
    return leftTalonOutside.getSelectedSensorPosition();
  }

  public double getEncoderValueRight() {
    return rightTalonInside.getSelectedSensorPosition();
  }

  public void resetEncodersValues() {
    leftTalonOutside.getSensorCollection().setQuadraturePosition(0, 30);
    rightTalonInside.getSensorCollection().setQuadraturePosition(0, 30);
  }

  public double convertToInches(double encoderValue) {
    return encoderValue * Constants.INCHES_PER_TICK;
  }

  public double convertToEncoderValue(double Inches) {
    return Inches * Constants.INCHES_PER_TICK;
  }

  public double encoderDistanceValue() {
    return convertToInches(getEncoderValueLeft() + getEncoderValueRight()) / 2 ;
  }

  // public void driveForwardEncoder(double distance, double speed) {

  //   if (convertToInches(getEncoderValueLeft()) + convertToEncoderValue(getEncoderValueRight()) / 2 >= distance) {
  //    autoDriveForward(speed);
  //   } else {
  //    autoDriveForward(0);
  //   }
  // }

  public void driveRightEncoder(double distanceRight, double speed) {

    if (convertToInches(getEncoderValueLeft()) <= distanceRight
        && convertToInches(getEncoderValueRight()) <= distanceRight) {
      driveRight(speed);
    } else {
     // driveForward(0);
    }
  }

  
  

  // Drives foward


  // Pivots Right on center
  public void driveRight(double speed) {
    leftTalonOutside.set(speed);
    leftTalonInside.set(speed);
    rightTalonInside.set(-speed);
    rightTalonOutside.set(-speed);
  }

  // Drives forward with set speed
  public void autoDriveForward(double speed) {
    leftTalonOutside.set(speed);
    leftTalonInside.set(speed);
    rightTalonInside.set(speed);
    rightTalonOutside.set(speed);

  }

  // Drives back with set speed
  public void autoDriveBack() {
    leftTalonOutside.set(0.4);
    leftTalonInside.set(0.4);
    rightTalonInside.set(0.4);
    rightTalonOutside.set(0.4);
  }

  // Pivots left on center
  public void autoDriveLeft() {
    leftTalonOutside.set(0.4);
    leftTalonInside.set(0.4);
    rightTalonInside.set(-0.4);
    rightTalonOutside.set(-0.4);
  }

  // Pivots right on center
  public void autoDriveRight() {
    leftTalonOutside.set(-0.4);
    leftTalonInside.set(-0.4);
    rightTalonInside.set(0.4);
    rightTalonOutside.set(0.4);
  }

  public void squareGyro() {

  }

  // Stops driving
  public void autoDriveStop() {
    leftTalonOutside.set(0);
    leftTalonInside.set(0);
    rightTalonInside.set(0);
    rightTalonOutside.set(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Get Gyro Angle", gyro.getAngle());
    SmartDashboard.putNumber("Encoder Left Raw", leftTalonOutside.getSelectedSensorPosition());
    SmartDashboard.putNumber("Encoder Right Raw", rightTalonInside.getSelectedSensorPosition());
    SmartDashboard.putNumber("Encoder Left Values (Feet): ",
        convertToInches(leftTalonOutside.getSelectedSensorPosition()));
    SmartDashboard.putNumber("Encoder Right Values(Feet): ",
        convertToInches(rightTalonInside.getSelectedSensorPosition()));

    // This method will be called once per scheduler run
  }
}
