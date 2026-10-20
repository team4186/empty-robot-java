package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArcadeDriveSubsystem extends SubsystemBase {

    private final TalonSRX leftMotor;
    private final TalonSRX rightMotor;

    /**
     * NOTE: For SparkMax setup we can get an encoder and set it to reference later,
     *       For TalonSRX we print the values directly in periodic
     *     private final RelativeEncoder leftEncoder;
     *     private final RelativeEncoder rightEncoder;
     *     this.a_Encoder = leftmotor.getRelativeEncoder();
      */

    private final DifferentialDrive diffDrive;


    public ArcadeDriveSubsystem(
            TalonSRX leftMotorSet,
            TalonSRX rightMotorSet
    ){
        this.leftMotor = leftMotorSet;
        this.rightMotor = rightMotorSet;

        // this.leftEncoder =

        this.diffDrive = new DifferentialDrive(
                value -> accept(value, leftMotor),
                value -> accept(value, rightMotor)
        );
    }


    /**
     * Print useful sensor information here using SmartDashboard, publishing useful information that can be seen anywhere
     * using the network tables.
     */
    @Override
    public void periodic(){
        // Note: Default Velocity is in RPM and
        SmartDashboard.putNumber("DiffDrive_RightMotor_Velocity", rightMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("DiffDrive_LeftMotor_Velocity", leftMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("DiffDrive_Linear_Velocity", (leftMotor.getSelectedSensorVelocity() + rightMotor.getSelectedSensorVelocity()) / 2);
    }


    /**
     * Take Controller inputs and pass them along to DifferentialDrive Object
     *
     * @param forward input from a controller axis measurement
     * @param turn input from a controller axis measurement
     */
    public void arcadeDrive(Double forward, Double turn){
        diffDrive.arcadeDrive(forward, turn);
    }


    /**
     * Send stop signal to motors, REMINDER: default brake or default coast "0% Power" will stop the motor or roll to a stop
     */
    public void stopMotors(){
        diffDrive.stopMotor();
    }


    /**
     * Lambda Expression: function to pass information with no return value, defining how arcade drive will accept input
     *
     * @param value input from arcade drive
     * @param motor motor to pass value to
     */
    private void accept(Double value, TalonSRX motor){
        motor.set(ControlMode.PercentOutput, value);
    }
}
