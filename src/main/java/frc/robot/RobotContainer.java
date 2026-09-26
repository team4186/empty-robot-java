// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.turretcommands.AutoTurretPassToAlliance;
import frc.robot.commands.turretcommands.AutoTurretTargeting;
import frc.robot.commands.turretcommands.AutoTurretTargetingPose;
import frc.robot.subsystems.ArcadeDriveSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.motors.Components;
import java.io.File;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.vision.LimelightRunner;
import swervelib.SwerveInputStream;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic
 * methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and
 * trigger mappings) should be declared here.
 */
public class RobotContainer {

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController driverXbox = new CommandXboxController(3);
    private final CommandPS5Controller driverPS5 = new CommandPS5Controller(4);
    private final CommandStadiaController driverStadia = new CommandStadiaController(5);


    private final CommandJoystick joystickDriver = new CommandJoystick(0); //set port 0 for stadia/joystick, whichever is being used
    // private final CommandJoystick joystickOperator = new CommandJoystick(1);

    private final Components motorComponents = Components.getInstance();

    // The robot's subsystems and commands are defined here...

    // Swerve drivebase
//    private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
//            "swerve/maxSwerve"));

    private final ArcadeDriveSubsystem arcadeDrivebase = new ArcadeDriveSubsystem(
            motorComponents.getLeftArcadeDriveMotor(),
            motorComponents.getRightArcadeDriveMotor()
    );

    // Arcade Drive Drivebase
//    private final ArcadeSubsystem drivebase;

    // Establish a Sendable Chooser that will be able to be sent to the
    // SmartDashboard, allowing selection of desired auto
//    private final SendableChooser<Command> autoChooser;


//    private final TurretSubsystem turretSubsystem = new TurretSubsystem(
//            motorComponents.getTurretShooterMotor(),
//            motorComponents.getTurretRotateMotor(),
//            motorComponents.getTurretHoodMotor(),
//            new DigitalInput(Constants.TurretConstants.HOOD_LIMIT_SWITCH),
//            new DigitalInput(Constants.TurretConstants.TURRET_LEFT_LIMIT_SWITCH),
//            new DigitalInput(Constants.TurretConstants.TURRET_RIGHT_LIMIT_SWITCH)
//    );


    // Command Creation
//    AutoTurretTargeting simpleTurretTracking = new AutoTurretTargeting(turretSubsystem);
//    AutoTurretPassToAlliance simplePassing = new AutoTurretPassToAlliance(turretSubsystem);
//    AutoTurretTargetingPose simplePoseTracking = new AutoTurretTargetingPose(turretSubsystem);


    // NOTE: Coordinates are odd for Joysticks, read this if unsure: https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html

    /**
     * Converts driver input into a field-relative ChassisSpeeds that is controlled
     * by angular velocity for Swerve Subsystem
     */
//    SwerveInputStream driveAngularVelocityBlueJoystick = SwerveInputStream.of(
//                    drivebase.getSwerveDrive(),
//            () -> attenuated( joystickDriver.getY(), 2, 1.0 ) * -1,
//            () -> attenuated( joystickDriver.getX(), 2, 1.0 ) * -1)
//            .withControllerRotationAxis(
//                    () -> attenuated( joystickDriver.getTwist(), 2, 0.75 ) * -1)
//            .deadband(OperatorConstants.DEADBAND)
//            .allianceRelativeControl(true);
//
//    SwerveInputStream driveAngularVelocitySlowBlueJoystick = SwerveInputStream.of(
//            drivebase.getSwerveDrive(),
//            () -> attenuated( joystickDriver.getY(), 2, 0.25 ) * -1,
//            () -> attenuated( joystickDriver.getX(), 2, 0.25 ) * -1)
//        .withControllerRotationAxis(
//            () -> attenuated( joystickDriver.getTwist(), 2, 0.75 ) * -1)//scale originally 0.5
//        .deadband(OperatorConstants.DEADBAND)
//        .allianceRelativeControl(true);
//
//
//    SwerveInputStream driveStadia = SwerveInputStream.of(
//                    drivebase.getSwerveDrive(),
//                    () -> attenuated( driverStadia.getLeftY(), 2, 1.0 ) * -1,
//                    () -> attenuated( driverStadia.getLeftX(), 2, 1.0 ) * -1)
//            .withControllerRotationAxis(
//                     () -> driverStadia.getRawAxis(3))
//                    //driverStadia::getRightX)
//            // () -> attenuated( joystickDriver.getTwist(), 3, 0.75 ) * 1)
//            .deadband(OperatorConstants.DEADBAND)
//            .scaleTranslation(0.4)
//            .allianceRelativeControl(true);
//
//    SwerveInputStream driveStadiaHeadingAxis = driveStadia.copy().withControllerHeadingAxis(
//                    driverStadia::getRightX,
//                    driverStadia::getRightY)
//            .headingWhile(true);
//
//
//    SwerveInputStream driveFieldPS5 = SwerveInputStream.of(
//            drivebase.getSwerveDrive(),
//            () -> attenuated( driverPS5.getLeftY(), 2, 1.0 ) * -1,
//            () -> attenuated( driverPS5.getLeftX(), 2, 1.0 ) * -1)
//        .withControllerRotationAxis(
//            driverPS5::getRightX)
//        .deadband(OperatorConstants.DEADBAND)
//        .allianceRelativeControl(true);
//
//    SwerveInputStream driveHeadingAxisPS5 = driveFieldPS5.copy().withControllerHeadingAxis(
//            driverPS5::getRightX,
//            driverPS5::getRightY)
//        .headingWhile(true);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        DriverStation.silenceJoystickConnectionWarning(true);

        // Register NamedCommands that will be used in PathPlanner if using custom created commands
//        NamedCommands.registerCommand("test", Commands.print("I EXIST"));
//
//        //Have the autoChooser pull in all PathPlanner autos as options
//        autoChooser = AutoBuilder.buildAutoChooser();
//
//        // Set the default auto (do nothing)
////        autoChooser.setDefaultOption("Do Nothing", Commands.runOnce(drivebase::zeroGyroWithAlliance)
////                .andThen(Commands.none()));
//        autoChooser.setDefaultOption("Do Nothing", Commands.none());
//
//
//        // Put the autoChooser on the SmartDashboard
//        SmartDashboard.putData("Auto Chooser", autoChooser);
//
//        if (autoChooser.getSelected() == null) {
//            // RobotModeTriggers.autonomous().onTrue(Commands.runOnce(drivebase::zeroGyroWithAlliance));
//        }

        // After Auto but before Alliance specific setup
        configureBindings();

        // Update Alliance Relevant info
        updateDriverAllianceInfo();
    }


    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary predicate, or via the
     * named factories in
     * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
     * for
     * {@link CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
     * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
     * Flight joysticks}.
     */
    private void configureBindings() {
//        Command driveFieldOrientedBlueAlliance = drivebase.driveFieldOriented(driveAngularVelocityBlueJoystick);
//        Command driveFieldOrientedBlueAllianceSlow = drivebase.driveFieldOriented(driveAngularVelocitySlowBlueJoystick);

        if (RobotBase.isSimulation()) {
            // drivebase.setDefaultCommand(driveFieldOrientedPS5);
            // drivebase.setDefaultCommand(driveFieldHeadingPS5);

//            drivebase.setDefaultCommand(driveFieldOrientedBlueAlliance);
//            joystickDriver.button(11).whileTrue(driveFieldOrientedBlueAllianceSlow);
//            joystickDriver.button(5).whileTrue(drivebase.centerModulesCommand());
//            joystickDriver.button(6).whileTrue(Commands.runOnce(drivebase::lock));

        } else {
//            drivebase.setDefaultCommand(driveFieldOrientedBlueAlliance);
//            joystickDriver.button(11).whileTrue(driveFieldOrientedBlueAllianceSlow);
        }

        if (Robot.isSimulation()) {
            // Create a target pose with destination, hold button to drive to pose
            Pose2d targetPose = new Pose2d(new Translation2d(15, 4),
                    Rotation2d.fromDegrees(180));
//            driverPS5.cross().whileTrue(drivebase.driveToPose(targetPose));
        }

        if (DriverStation.isTest()) {

        } else {
            // Teleop Command Keybinds
            // joystickOperator.button(11).whileTrue(drivebase.driveToPose(targetPose));

            // joystickOperator.trigger().whileTrue(Commands.runOnce(spindexerSubsystem::feed, spindexerSubsystem).repeatedly());

        }
    }


    //TODO: Finish at field
    public void updateDriverAllianceInfo(){
        var alliance = DriverStation.getAlliance();
        LimelightRunner limelightRunner = LimelightRunner.getInstance();
        String turret = Constants.LimelightConstants.LIMELIGHT_TURRET;

        boolean isRedAlliance = (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red);
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to rotateMotors in autonomous
     */
    public Command getAutonomousCommand() {
        // Pass in the selected auto from the SmartDashboard as our desired autnomous
        // commmand
        // return autoChooser.getSelected();
        return Commands.none();
    }


    public void setMotorBrake(boolean brake) {
        // drivebase.setMotorBrake(brake);
    }


    private double attenuated(double value, int exponent, double scale) {
        double res = scale * Math.pow( Math.abs(value), exponent );
        if ( value < 0 ) { res *= -1; }
        return res;
    }


    // Reset Closed Loop Controls to neutral position or velocity respectively
    public void resetSubsystems(){
//        turretSubsystem.updateShooterSpeed(0.0);
//        turretSubsystem.updateTurretRotation(0.0);
    }


    public void arcadeDrive() {
        arcadeDrivebase.arcadeDrive(
                attenuated(joystickDriver.getY(), 1,1 ),
                attenuated(joystickDriver.getTwist(), 1, 1)
        );
    }

}
