package frc.robot.motors;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.*;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constants;
import frc.robot.Constants.TurretConstants;


// MotorConfigs Singleton for Subsystem Motors (Swerve Subsystem not included)
public final class MotorConfigs {
    private static final MotorConfigs instance = new MotorConfigs();

    // Default Configs
    private final SparkBaseConfig DefaultSparkMaxConfig = new SparkMaxConfig()
        .smartCurrentLimit(50)
        .idleMode(SparkBaseConfig.IdleMode.kBrake);

    private final SparkBaseConfig DefaultSparkFlexConfig = new SparkFlexConfig()
        .smartCurrentLimit(50)
        .idleMode(SparkBaseConfig.IdleMode.kBrake);

    private final TalonSRXConfiguration TalonBaseConfig = new TalonSRXConfiguration();
    private final VictorSPXConfiguration VictorBaseConfig = new VictorSPXConfiguration();

    // private constructor to prevent public class creation
    private MotorConfigs() {}


    public static MotorConfigs getInstance() { return instance; }


    public SparkBaseConfig applyDefaultSparkMaxConfig() { return DefaultSparkMaxConfig; }


    public SparkBaseConfig applyDefaultSparkFlexConfig() { return DefaultSparkFlexConfig; }


    /**
     * Use this apply config a single motor in {@link Components} class.
     *
     * @param motor SparkMax motor object needing configuration
     * @param inverse the motor direction
     *
     * @return SparkMax motor with applied config
     */
    public SparkMax applyTurretRotateSparkConfig(
        SparkMax motor,
        boolean inverse
    ) {
        SparkBaseConfig config = DefaultSparkMaxConfig;

        config
            .inverted(inverse)
            .smartCurrentLimit(Constants.NeoMotorConstants.SMART_CURRENT_LIMIT_550)
            .idleMode(TurretConstants.ROTATE_IDLE_MODE);

        config.encoder
            .positionConversionFactor(TurretConstants.ROTATE_POSITION_CONVERSION_FACTOR)
            .velocityConversionFactor(TurretConstants.ROTATE_VELOCITY_CONVERSION_FACTOR);

        config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            // Set PID values for position control. We don't need to pass a closed loop
            // slot, as it will default to slot 0.
            .pid(
                TurretConstants.ROTATE_P,
                TurretConstants.ROTATE_I,
                TurretConstants.ROTATE_D,
                ClosedLoopSlot.kSlot0)
            .outputRange(
                TurretConstants.ROTATE_MIN_OUTPUT,
                TurretConstants.ROTATE_MAX_OUTPUT,
                ClosedLoopSlot.kSlot0)
            .allowedClosedLoopError(TurretConstants.ROTATE_ERROR_THRESHOLD, ClosedLoopSlot.kSlot0)
            .feedForward
            .kS(
                TurretConstants.ROTATE_KS,
                ClosedLoopSlot.kSlot0)
            .kV(
                TurretConstants.ROTATE_KV,
                ClosedLoopSlot.kSlot0);


        motor.configure(
            config,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );

        return motor;
    }


    /**
     * Use this apply config a leader/follower motor pair in {@link Components} class.
     *
     * @param motorLeader SparkMax motor object needing configuration
     * @param inverse the motor direction
     *
     * @return SparkMax motor with applied config
     */
    public SparkFlex applyShooterSparkConfig(
        SparkFlex motorLeader,
        SparkFlex motorFollower,
        boolean inverse
    ){
        SparkBaseConfig baseConfig = DefaultSparkFlexConfig;

        baseConfig.inverted(inverse)
            .smartCurrentLimit(Constants.NeoMotorConstants.SMART_CURRENT_LIMIT_VORTEX)
            .idleMode(TurretConstants.SHOOTER_IDLE_MODE);

        // Using Velocity
        baseConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .pid(
                    TurretConstants.SHOOTER_P,
                    TurretConstants.SHOOTER_I,
                    TurretConstants.SHOOTER_D,
                    ClosedLoopSlot.kSlot1)
                .outputRange(
                    TurretConstants.SHOOTER_MIN_OUTPUT,
                    TurretConstants.SHOOTER_MAX_OUTPUT,
                    ClosedLoopSlot.kSlot1) // Range of total voltage
                .allowedClosedLoopError(
                        TurretConstants.SHOOTER_ERROR_THRESHOLD,
                        ClosedLoopSlot.kSlot0)
                .feedForward
                .kS(TurretConstants.SHOOTER_KS,
                ClosedLoopSlot.kSlot1)
                .kV(
                TurretConstants.SHOOTER_KV,
                ClosedLoopSlot.kSlot1);

        baseConfig.encoder
            .positionConversionFactor(TurretConstants.SHOOTER_POSITION_CONVERSION_FACTOR)
            .velocityConversionFactor(TurretConstants.SHOOTER_VELOCITY_CONVERSION_FACTOR)
            .quadratureAverageDepth(Constants.VELOCITY_AVERAGE_DEPTH)
            .quadratureMeasurementPeriod(Constants.VELOCITY_MEASUREMENT_PERIOD);

        motorLeader.configure(
            baseConfig,
            ResetMode.kNoResetSafeParameters,
            PersistMode.kPersistParameters);


        SparkBaseConfig followerConfig = new SparkFlexConfig();

        followerConfig
            .apply(baseConfig)
            .follow(motorLeader, true);

        motorFollower.configure(
            followerConfig,
            ResetMode.kNoResetSafeParameters,
            PersistMode.kPersistParameters);

        return motorLeader;
    }

    // Example of No Closed Loop Configuration
//    public SparkMax applyIntakeExtensionSparkConfig(
//        SparkMax motor,
//        boolean inverse
//    ) {
//            SparkBaseConfig config = DefaultSparkMaxConfig;
//
//            config
//                    .inverted(inverse)
//                    .smartCurrentLimit(Constants.NeoMotorConstants.SMART_CURRENT_LIMIT_550)
//                    .idleMode(IntakeConstants.EXTENSION_IDLE_MODE);
//
//            config.encoder
//                    .positionConversionFactor(IntakeConstants.EXTENSION_POSITION_CONVERSION_FACTOR)
//                    .velocityConversionFactor(IntakeConstants.EXTENSION_VELOCITY_CONVERSION_FACTOR);
//
//            motor.configure(
//                    config,
//                    ResetMode.kResetSafeParameters,
//                    PersistMode.kPersistParameters
//            );
//
//            return motor;
//    }

//    rightArcadeDriveMotor = customConfigs.applyDefaultArcadeDriveConfig(
//            new TalonSRX(7), // Leader
//                    new VictorSPX(3), // Follower 1
//                    new VictorSPX(4), // Follower 2
//                    false
//                            );
    public TalonSRX applyDefaultArcadeDriveConfig(
            TalonSRX leader,
            VictorSPX follower0,
            VictorSPX follower1,
            Boolean inverse
    ) {
        leader.configAllSettings(TalonBaseConfig);
        follower0.configAllSettings(VictorBaseConfig);
        follower1.configAllSettings(VictorBaseConfig);

        follower0.follow(leader);
        follower1.follow(leader);

        leader.setInverted( inverse );
        follower0.setInverted(InvertType.FollowMaster);
        follower1.setInverted(InvertType.FollowMaster);

        return leader;
    }
}
