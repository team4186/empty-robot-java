package frc.robot.motors;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.Constants.TurretConstants;

/**
 * The [Components] singleton can be used to configure and hold reference to hardware parts
 * used by the [Robot].
 *
 * The only gain here is organizational, as it avoids cluttering in the [Robot] class scope.
 */

// Flexible motor creation for fast testing between systems
public class Components {
    private static final Components instance = new Components();
    private final MotorConfigs customConfigs = MotorConfigs.getInstance();

    private SparkMax turretRotateMotor;
    private SparkMax turretHoodMotor;
    private SparkFlex turretShooterMotor;

    private TalonSRX leftArcadeDriveMotor;
    private TalonSRX rightArcadeDriveMotor;

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

    public TalonSRX getLeftArcadeDriveMotor() {
        if ( leftArcadeDriveMotor == null ) {
            leftArcadeDriveMotor = customConfigs.applyDefaultArcadeDriveConfig(
                    new TalonSRX(11), // Leader
                    new VictorSPX(10), // Follower 1
                    new VictorSPX(12), // Follower 2
                    false
            );
        }

        return leftArcadeDriveMotor;
    }

    public TalonSRX getRightArcadeDriveMotor() {
        if ( rightArcadeDriveMotor == null ){
            rightArcadeDriveMotor = customConfigs.applyDefaultArcadeDriveConfig(
                    new TalonSRX(7), // Leader
                    new VictorSPX(3), // Follower 1
                    new VictorSPX(4), // Follower 2
                    true
            );
        }

        return rightArcadeDriveMotor;
    }

}
