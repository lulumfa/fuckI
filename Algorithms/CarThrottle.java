package Airbnb;

public class CarThrottle {
	private static final double MAX_SPEED_M_PER_S = 200; // unit as m/s
	private static final int RETRY_MS = 100;
	
	public void wrapper(double targetSpeed){
		double left = 0, right = MAX_SPEED_M_PER_S;
		setThrottle(right);
		sleep();
		while (!shouldStop(targetSpeed)) {
			double mid = left + (right - left) /2;
			if (shouldSpeedUp(targetSpeed)) {
				left = getStatus()[0];
				setThrottle(mid);
			} else if (shouldSlowDown(targetSpeed)) {
				right = getStatus()[0];
				setThrottle(mid);
			}
			sleep();
		}
	}
	
	private boolean shouldSlowDown(double targetSpeed) {
		double[] status = getStatus();
		if (status ==  null || status.length < 2) {
			return false;
		}
		return status[1] > targetSpeed;
	}
	
	private boolean shouldSpeedUp(double targetSpeed) {
		double[] oldStatus = getStatus();
		sleep();
		double[] newStatus = getStatus();
		if (oldStatus == null || newStatus == null || oldStatus.length < 2 || newStatus.length < 2) {
			return false;
		}
		return oldStatus[1] == newStatus[1] && oldStatus[1] < targetSpeed;
	}
	
	private boolean shouldStop(double targetSpeed) {
		double[] oldStatus = getStatus();
		if (oldStatus == null || oldStatus.length < 2) return true;
		if (oldStatus[1] != targetSpeed) return false;
		
		sleep();
		
		double[] newStatus = getStatus();
		return (oldStatus == null || oldStatus.length < 2 || oldStatus[1] == newStatus[1]);
	}
	
	private void sleep() {
        try {
			Thread.sleep(RETRY_MS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private double[] getStatus() {
		// mimic socket_server
		// first param as throttle, second as the current speed
		return new double[]{50.1, 30.1};
	}
	
	
	private void setThrottle(double throttle) {
		// mimic socket_server
	}
}
