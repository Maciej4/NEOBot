package frc.robot

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.path.PathBuilder
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator
import com.acmerobotics.roadrunner.profile.MotionState
import com.acmerobotics.roadrunner.trajectory.TrajectoryGenerator
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints

class RoadRunnerSandbox {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val profile = MotionProfileGenerator.generateSimpleMotionProfile(
                MotionState(0.0, 0.0, 0.0),
                MotionState(60.0, 0.0, 0.0),
                25.0,
                40.0,
                100.0
            )

            println(profile[0.5])

            val path = PathBuilder(Pose2d(3.0, 3.0, 0.0))
                .splineTo(Pose2d(-3.0, -3.0, 0.0))
                .lineTo(Vector2d(1.0, 1.0))
                .build()
            val constraints = DriveConstraints(20.0, 40.0, 80.0, 1.0, 2.0, 4.0)
            val trajectory = TrajectoryGenerator.generateTrajectory(path, constraints)

            val translationalPid = PIDCoefficients(5.0, 0.0, 0.0)
            val headingPid = PIDCoefficients(2.0, 0.0, 0.0)
            val follower = HolonomicPIDVAFollower(translationalPid, translationalPid, headingPid)

            follower.followTrajectory(trajectory)

            for(t in 0..10) {
                val poseEstimate = trajectory[t.toDouble()]
                val signal = follower.update(poseEstimate)
                println(signal)
            }
        }
    }
}