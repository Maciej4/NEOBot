package frc.robot.controllers;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.Path;
import com.acmerobotics.roadrunner.path.PathBuilder;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryGenerator;
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints;

public class AutoDrive
{
    public MotionProfile profile;
    public Pose2d poseEstimate;
    public DriveSignal signal;
    public Path path;
    public DriveConstraints constraints;
    public Trajectory trajectory;
    public PIDCoefficients translationalPid;
    public PIDCoefficients headingPid;
    public HolonomicPIDVAFollower follower;

    public AutoDrive()
    {
        profile = MotionProfileGenerator.generateSimpleMotionProfile(
            new MotionState(0.0, 0.0, 0.0),
            new MotionState(60.0, 0.0, 0.0),
            25.0,
            40.0,
            100.0
        );

        System.out.println(profile.get(0.5));

        path = new PathBuilder(new Pose2d(3.0, 3.0, 0.0))
            .splineTo(new Pose2d(-3.0, -3.0, 0.0))
            .lineTo(new Vector2d(1.0, 1.0))
            .build();

        constraints = new DriveConstraints(20.0, 40.0, 80.0, 1.0, 2.0, 4.0);
        trajectory = TrajectoryGenerator.INSTANCE.generateTrajectory(path, constraints);

        translationalPid = new PIDCoefficients(5.0, 0.0, 0.0);
        headingPid = new PIDCoefficients(2.0, 0.0, 0.0);
        follower = new HolonomicPIDVAFollower(translationalPid, translationalPid, headingPid);

        follower.followTrajectory(trajectory);

        init();
    }

    public void loop(double t)
    {
        ////for(t in 0..10) {}
        // poseEstimate = trajectory.get(t);
        // signal = follower.update(poseEstimate);
        // System.out.println(signal);
    }

    public void init()
    {
        for(double t = 0; t < 4; t += 0.1) {
            // poseEstimate = trajectory.get(t);
            // System.out.println("Pose Estimate: " + poseEstimate);
            // signal = follower.update(poseEstimate);
            // System.out.println("Signal: " + signal);
            System.out.println(profile.get(t));
        }
    }
}