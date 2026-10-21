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


/**
 * The [MotorConfigs] singleton can be used to configure MotorControllers used by the [Robot].
 *
 * The only gain here is organizational, as it avoids cluttering in the [Robot] class scope.
 */
public final class MotorConfigs {
    private static final MotorConfigs instance = new MotorConfigs();


    // Default Configs for neo motors can be found here: https://docs.revrobotics.com/brushless/home/faq
    private final SparkBaseConfig DefaultSparkMaxConfig = new SparkMaxConfig()
        .smartCurrentLimit(50)
        .idleMode(SparkBaseConfig.IdleMode.kBrake);

    private final SparkBaseConfig DefaultSparkFlexConfig = new SparkFlexConfig()
        .smartCurrentLimit(50)
        .idleMode(SparkBaseConfig.IdleMode.kBrake);

    private final TalonSRXConfiguration TalonBaseConfig = new TalonSRXConfiguration();
    private final VictorSPXConfiguration VictorBaseConfig = new VictorSPXConfiguration();


    // Private constructor to prevent public class creation
    private MotorConfigs() {}


    // If instance is needed
    public static MotorConfigs getInstance() { return instance; }


    public SparkBaseConfig applyDefaultSparkMaxConfig() { return DefaultSparkMaxConfig; }


    public SparkBaseConfig applyDefaultSparkFlexConfig() { return DefaultSparkFlexConfig; }


    /*
      Consider the following when creating a configuration:

      1. Single motor or Group of motors with 1 leader and 1+ followers
            Do the multiple motors conflict or cooperate?
      2. Closed-Loop values taken from testing using RevRoboticsV2 dashboard
      3. Conversion factor for rpm and/or position, find this using gear ratios to turn rotations into useful units
      4. If closed-loop for velocity update the depth and period to remove delay between decisions
            .quadratureAverageDepth(Constants.VELOCITY_AVERAGE_DEPTH)
            .quadratureMeasurementPeriod(Constants.VELOCITY_MEASUREMENT_PERIOD);
      5. Are you using limit switches tied to the motor controllers? If yes, then add to configuration
     */


    /**
     * Use this apply config a single motor in {@link Components} class. Apply closed loop constants from Constants File
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


    /**
     *
     *
     * @param leader
     * @param follower0
     * @param follower1
     * @param inverse swap positive and negative direction for motor
     * @return TalonSRX leader motor with applied configuration
     */
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
