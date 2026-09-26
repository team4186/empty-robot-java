package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleConsumer;

public class ArcadeDriveSubsystem extends SubsystemBase {

    private final TalonSRX leftMotor;
    private final TalonSRX rightMotor;

    private final DifferentialDrive diffDrive;

    public ArcadeDriveSubsystem(
            TalonSRX leftMotorSet,
            TalonSRX rightMotorSet
    ){
        this.leftMotor = leftMotorSet;
        this.rightMotor = rightMotorSet;

        this.diffDrive = new DifferentialDrive(
                value -> accept(value, leftMotor),
                value -> accept(value, rightMotor)
        );
    }

    @Override
    public void periodic(){
        // Print velocity and other useful metrics to SmartDashboard Here
    }


//    fun manualDrive(
//            forward: Double,
//            turn: Double,
//            direction: Direction = Direction.Forward,
//            drive: (forward: Double, turn: Double) -> Unit,
//            ) {
//        drive(
//                attenuated(direction * 0.9 * forward),
//                attenuated(direction * 0.75 * turn),
//                )
//    }
    public void arcadeDrive(Double forward, Double turn){
//        manualDrive(
    }

    public void stopMotors(){
        diffDrive.stopMotor();
    }


    private void stopMotor(TalonSRX motor){
        // Send Stop motor signal
        motor.set(ControlMode.PercentOutput, 0.0);
    }

    private void accept(Double value, TalonSRX motor){
        motor.set(ControlMode.PercentOutput, value);
    }
}
