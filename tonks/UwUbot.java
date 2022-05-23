package DernnsBots;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * uwubot - Deryn / iFishae @ https://github.com/iFishae
 */
public class UwUbot extends Robot
{

	public void run() {
		// Initialization of the robot should be put here

		setGunColor(Color.magenta);
		setRadarColor(Color.magenta);
		setBulletColor(Color.magenta);
		setScanColor(Color.orange);

		// Main Loop
		while(true) {
			//RGB Colors lol
			setBodyColor(Color.red);
			setBodyColor(Color.orange);
			setBodyColor(Color.yellow);
			setBodyColor(Color.green);
			setBodyColor(Color.cyan);
			setBodyColor(Color.blue);
			setBodyColor(Color.magenta);


			turnRight(360);
			

		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(3);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
