package frc.robot.motors;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants.TurretConstants;

// Flexible motor creation for fast testing between systems
public class Components {
    private static final Components instance = new Components();
    private final MotorConfigs customConfigs = MotorConfigs.getInstance();

    private SparkMax turretRotateMotor;
    private SparkMax turretHoodMotor;
    private SparkFlex turretShooterMotor;

    private SparkMax intakeExtensionStarboardMotor;
    private SparkMax intakeExtensionPortMotor;
    private SparkMax intakePickupMotor;

    private SparkMax intakeTestMotor;

    private SparkMax spindexerRotateMotor;
    private SparkMax spindexerFeedMotor;
    private SparkMax spindexerAssistMotor;
    private SparkMax climbMotor;


    // private constructor to prevent public class creation
    private Components() { }


    public static Components getInstance() { return instance; }

    // Single Motor Example using SparkMax motors
//    public SparkMax getTurretRotateMotor(){
//        if ( turretRotateMotor == null ) {
//            turretRotateMotor = customConfigs.applyTurretRotateSparkConfig(
//                new SparkMax(TurretConstants.ROTATE_MOTOR_ID, SparkLowLevel.MotorType.kBrushless),
//                false
//            );
//        }
//
//        return turretRotateMotor;
//    }

    // Motor Pair Example using SparkFlex motors (Leader/Follower) -> return one motor object
//    public SparkFlex getTurretShooterMotor(){
//        if (turretShooterMotor == null) {
//            turretShooterMotor = customConfigs.applyShooterSparkConfig(
//                new SparkFlex(TurretConstants.SHOOTER_LEAD_MOTOR_ID, SparkLowLevel.MotorType.kBrushless),
//                new SparkFlex(TurretConstants.SHOOTER_FOLLOWER_MOTOR_ID, SparkLowLevel.MotorType.kBrushless),
//                true
//            );
//        }
//
//        return turretShooterMotor;
//    }
}
