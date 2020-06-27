package frc.robot.util;

public class PID {
    /**Variable Declarations*/
	//Proportional, Integral and Derivative Coefficients
    public double pFactor, iFactor, dFactor;
    //Integral Sum
    private double integral;
    //The previous error for derivative calculation
    private double lastError;

	/**
	 * This is the constructor for a PID controller. A PID controller allows for far more precise control 
	 * of the rotation of a motor, whether it be controlling the angular velocity or the position of the motor.
	 * 
	 * @param pFactor The coefficient that the error value is mutiplied by before being summed.
	 * @param iFactor The coefficient that the integral value is mutiplied by before being summed.
	 * @param dFactor The coefficient that the derivative value is mutiplied by before being summed.
	 */
	public PID(double pFactor, double iFactor, double dFactor) {
		this.pFactor = pFactor;
		this.iFactor = iFactor;
		this.dFactor = dFactor;
	}
	
	//Runs through one step of the PID
	//@param double setpoint_(goal value), double actual (current value), double timeFrame (time since last run)
	/**
	 * This function updates the PID.
	 * 
	 * @param setpoint The goal position / velocity of the motor being controlled.
	 * @param actual The current real world position / velocity of the motor being controlled.
	 * @param timeFrame The amount of time since the last run of the PID update function.
	 * 
	 * @return This function returns a double, which is the power that the motor should be set to.
	 */
	public double update(double setpoint, double actual, double timeFrame) {
		//Actual PID math
		double present = setpoint - actual;
		integral += present * timeFrame;
		double deriv = (present - lastError) / timeFrame;
		lastError = present;
		return present * pFactor + integral * iFactor + deriv * dFactor;
    }
}
