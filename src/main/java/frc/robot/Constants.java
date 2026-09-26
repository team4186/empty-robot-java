// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.config.PIDConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Unit;
import edu.wpi.first.wpilibj.DriverStation;
import swervelib.math.Matter;
import com.revrobotics.spark.config.SparkBaseConfig;

import java.util.HashMap;
import java.util.Map;

import static edu.wpi.first.units.Units.Meter;
import static java.util.Map.entry;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be
 * declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // kg per pound
    public static final double ROBOT_MASS = (114.5 + 13.4 + 15.3) * 0.453592; // robot + battery + bumper (143.2 lbs) kg per pound
    public static final Matter CHASSIS = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
    public static final double LOOP_TIME = 0.13; // s, 20ms + 110ms sprk max velocity lag // TODO: Modify?
    public static final double MAX_SPEED = Units.feetToMeters(13.76); //orig value 14.5
    public static final double NOMINAL_VOLTAGE = 12.0;

    // Improving Velocity Based Control for Closed Loop Control Motor Control Configuration
    public static final int VELOCITY_AVERAGE_DEPTH = 5; // 5 Sample Count
    public static final int VELOCITY_MEASUREMENT_PERIOD = 1; // 1ms Moving Avg Window

    // Maximum speed of the robot in meters per second, used to limit acceleration.
    public static final class AutonConstants {
        // default path planner 5, 0, 0
        // default yagsl 0.7, 0, 0
        public static final PIDConstants TRANSLATION_PID = new PIDConstants(
                0.7,
                0,
                0);
        // default path planner 5, 0, 0
        // default yagsl 0.4, 0, 0.01
        public static final PIDConstants ANGLE_PID = new PIDConstants(
                0.4,
                0,
                0.01);
    }


    // Relavent Field Coordinates
    public static final class StructureConstants {

        // NOTE: All translations are based on Blue Origin
        public static final Translation2d BLUE_SCORING_LOCATION =
            new Translation2d(
                Meter.of( Units.inchesToMeters(182.11) ),
                Meter.of( Units.inchesToMeters(158.84) ));


        public static final Translation2d RED_SCORING_LOCATION =
            new Translation2d(
                Meter.of( Units.inchesToMeters(651.22 - 182.11) ),
                Meter.of( Units.inchesToMeters(158.84) ));


        // ~34in difference between poles, Perspective based blue origin
        public static final Translation2d RED_CLIMB_NORTH_POLE =
            new Translation2d(
                Meter.of( Units.inchesToMeters(651.22-40.0) ),
                Meter.of( Units.inchesToMeters(187.22)));

        public static final Translation2d RED_CLIMB_SOUTH_POLE =
            new Translation2d(
                Meter.of( Units.inchesToMeters(651.22-40.0) ),
                Meter.of( Units.inchesToMeters(153.22) ));


        public static final Translation2d BLUE_CLIMB_NORTH_POLE =
            new Translation2d(
                Meter.of( Units.inchesToMeters(40.0) ),
                Meter.of( Units.inchesToMeters(187.22) ));

        public static final Translation2d BLUE_CLIMB_SOUTH_POLE =
            new Translation2d(
                Meter.of( Units.inchesToMeters(40.0) ),
                Meter.of( Units.inchesToMeters(153.22) ));

        public static final double ROBOT_X_CLIMBING_OFFSET = 0.0;
    }


    public static final class DrivebaseConstants {
        // Hold time on motor brakes when disabled
        public static final double WHEEL_LOCK_TIME = 10; // seconds
    }


    public static class OperatorConstants {
        // Joystick Deadband
        public static final double DEADBAND = 0.1;
        public static final double LEFT_Y_DEADBAND = 0.1;
        public static final double RIGHT_X_DEADBAND = 0.1;
        public static final double TURN_CONSTANT = 6;
    }


    // Motor Configuration Constants for NEO Motors
    public static final class NeoMotorConstants {
        // NEO (REGULAR)
        // SMART CURRENT LIMIT (50A - 60A)
        public static final int SMART_CURRENT_LIMIT_REGULAR = 50;
        public static final double NEO_REG_FREE_SPEED = 5676;
        public static final double NEO_REG_RATING_KV = 473.0; // kv 473

        // NEO 550
        // SMART CURRENT LIMIT (20A - 40A)
        public static final int SMART_CURRENT_LIMIT_550 = 30;
        public static final double NEO_550_FREE_SPEED = 11000;
        public static final double NEO_550_RATING_KV = 915.0; // Neo Vortex KV rating under no load

        // NEO VORTEX
        // SMART CURRENT LIMIT 80A
        public static final int SMART_CURRENT_LIMIT_VORTEX = 80;
        public static final double NEO_VORTEX_FREE_SPEED = 6784;
        public static final double NEO_VORTEX_RATING_KV = 560.0; // Neo Vortex KV rating under no load
    }


    public static class LimelightConstants {
        // Camera pipeline names assigned on Limelight Camera
        public static final String LIMELIGHT_TURRET = "limelight-turret";
        public static final String LIMELIGHT_ROBOT = "limelight-climb";

        // Standard Deviations for Field Pose Estimation Camera Trust Levels
        public static final double LIMELIGHT_X_STD_DEVS = 0.5;
        public static final double LIMELIGHT_Y_STD_DEVS = 0.5;
        public static final double LIMELIGHT_HEADING_STD_DEVS = 9999999; // StdDevs (x, y, heading)

        // Description of Offsets relative to center of robot/gyroscope
        public static final double[] LIMELIGHT_ROBOT_CAMERA_POSITION = {
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            };
    }


    // Example Constants for a Turret Subsystem
    public static final class TurretConstants {
        // NEO 550
        public static final SparkBaseConfig.IdleMode ROTATE_IDLE_MODE = SparkBaseConfig.IdleMode.kCoast;
        public static final SparkBaseConfig.IdleMode SHOOTER_IDLE_MODE = SparkBaseConfig.IdleMode.kCoast;
        public static final SparkBaseConfig.IdleMode HOOD_IDLE_MODE = SparkBaseConfig.IdleMode.kBrake;

        // MotorController Unique Ids
        public static final int ROTATE_MOTOR_ID = 38;
        public static final int SHOOTER_LEAD_MOTOR_ID = 31;
        public static final int SHOOTER_FOLLOWER_MOTOR_ID = 32;
        public static final int HOOD_MOTOR_ID = 22;

        // LimitSwitch DIO Ports
        public static final int HOOD_LIMIT_SWITCH = 0;
        public static final int TURRET_LEFT_LIMIT_SWITCH = 2;
        public static final int TURRET_RIGHT_LIMIT_SWITCH = 3;

        // Max rotation
        public static final double TURRET_MAX_ROTATION = 100.0; // Degrees
        public static final double TURRET_MIN_ROTATION = -100.0;
        public static final double TURRET_ROTATION_DEAD_ZONE = 360 - (TURRET_MAX_ROTATION - TURRET_MIN_ROTATION);


        public static final double HOOD_MAX_ROTATION = 35.0; // Degrees
        public static final double HOOD_MIN_ROTATION = 0.0;


        // Gear Ratios for later use with Encoder Translations to Useful Units
        // https://docs.revrobotics.com/revlib/spark/closed-loop/units
        public static final double ROTATE_GEAR_RATIO = ((216.0/57.0) * 20.0); // 216:57 * 20:1
        public static final double SHOOTER_GEAR_RATIO = (1/1.25); // 1:1.25 (increase)
        public static final double HOOD_GEAR_RATIO = 20;


        // FeedForward for MotorControllers using Closed Loop Control
        public static final double ROTATE_KS = 0.51;
        public static final double SHOOTER_KS = 0.185;
        public static final double HOOD_KS = 0.7; // TODO: Update

        public static final double ROTATE_KV =  NOMINAL_VOLTAGE / NeoMotorConstants.NEO_550_FREE_SPEED;
        public static final double SHOOTER_KV = 0.001425;
        public static final double HOOD_KV = 0.075;


        // PIDs to assist FeedForward for Closed Loop Control
        public static final double ROTATE_P = 0.0175; // rotate is prone to oscillation at some points
        public static final double ROTATE_I = 0.0;
        public static final double ROTATE_D = 0.0085;

        public static final double SHOOTER_P = 0.00075;
        public static final double SHOOTER_I = 0.0;
        public static final double SHOOTER_D = 0.0;

        public static final double HOOD_P = 0.01;
        public static final double HOOD_I = 0.0;
        public static final double HOOD_D = 0.0;


        public static final int HOOD_SMART_CURRENT_LIMIT = 40;


        // Conversion factors and expected measured limits
        public static final double ROTATE_POSITION_CONVERSION_FACTOR = (1 / ROTATE_GEAR_RATIO) * 360; // Convert to degrees
        public static final double SHOOTER_POSITION_CONVERSION_FACTOR = ( 1/ SHOOTER_GEAR_RATIO );
        public static final double HOOD_POSITION_CONVERSION_FACTOR = (1 / HOOD_GEAR_RATIO) * 360;

        public static final double ROTATE_VELOCITY_CONVERSION_FACTOR = 1.0;
        public static final double SHOOTER_VELOCITY_CONVERSION_FACTOR = 1.0;
        public static final double HOOD_VELOCITY_CONVERSION_FACTOR = 1.0;


        // Assigning Upper and Lower Bounds for power output -1.0 to +1.0 (Full Power Reverse / Forward)
        public static final double ROTATE_MIN_OUTPUT = -0.9;
        public static final double ROTATE_MAX_OUTPUT = 0.9;

        public static final double SHOOTER_MIN_OUTPUT = -0.90;
        public static final double SHOOTER_MAX_OUTPUT = 0.90;

        public static final double HOOD_MIN_OUTPUT = -1;
        public static final double HOOD_MAX_OUTPUT = 1;

        public static final double HOOD_UP_SPEED = 0.4;
        public static final double HOOD_DOWN_SPEED = -0.35;

        public static final double HOOD_MAX_POSITION = 35.0;

        public static final double ROTATE_ERROR_THRESHOLD = 0.113;
        public static final double SHOOTER_ERROR_THRESHOLD = 0.0;
        public static final double HOOD_ERROR_THRESHOLD = 0.428;


        // Predefined Pre-sets For Hood Positions
        public static final double HOOD_L1_POSITION = 15; //original L1 (pos/speed): 15/0.16
        public static final double HOOD_L2_POSITION = 25;
        public static final double HOOD_L3_POSITION = 35;


        //  Key: Distance in Feet, Value: {ShooterSpeed, HoodAngle}
        /**
         *  Lookup Table KEY: Distance in Feed  Value: { ShooterSpeed, HoodAngle}
         *  Feet -> Double[ (Shooter Wheel Speed), (Hood Angle) ]
         */
        public static final Map<Integer, Double[]> TURRET_LOOKUP_WRT_CAMERA = new HashMap<Integer, Double[]>(
            Map.ofEntries(
                    entry(2, new Double[]{ 2600.0, 0.0}),
                    entry(3, new Double[]{ 2600.0, 7.5}), // expected lower bound
                    entry(4, new Double[]{ 2600.0, 10.0}),
                    entry(5, new Double[]{ 2600.0, 13.5}),
                    entry(6, new Double[]{ 2800.0, 13.5}),
                    entry(7, new Double[]{ 3000.0, 15.0})
                    //,entry(8, new Double[]{ 0.0, 0.0})
        ));


        public static final Map<Integer, Double[]> TURRET_LOOKUP_WRT_POSE = new HashMap<Integer, Double[]>(
                Map.ofEntries(
                        entry(3, new Double[]{ 2600.0, 0.0}),
                        entry(4,new Double[]{2600.0,3.8}),  //TODO: not tested (hallucinated valued)
                        entry(5, new Double[]{ 2600.0, 7.5}),
                        entry(6, new Double[]{ 2600.0, 10.0}),
                        entry(7, new Double[]{ 2600.0, 13.5}),
                        entry(8, new Double[]{ 2800.0, 13.5}),
                        entry(9, new Double[]{ 3000.0, 15.0}),
                        entry(10,new Double[]{3200.0,17.0}),
                        entry(11,new Double[]{3400.0,19.0})// TODO: also a hallucinated value
                        //,entry(8, new Double[]{ 0.0, 0.0})
                ));

        // Predifined Values for Testing Shooter Speeds without look-up table
        public static final double SHOOTER_TARGET_FAR_SPEED = 3000.0;
        public static final double SHOOTER_TARGET_CLOSE_SPEED = 5000.0;
    }
}
