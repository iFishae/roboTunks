package DernnsBots;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * K9 - a robot by (your name here)
 */
public class SupremeDalek extends Robot {
	public void run() {
		setBodyColor(new Color(129,30,42));
		setGunColor(new Color(215,215,217)); //215,215,217
		setRadarColor(new Color(217,213,210));
		setBulletColor(new Color(173,212,254));
		setScanColor(new Color(217,213,210));
		
		// Scanning at the beginning of the round, to check for enemy positions	
		scan();
		
		while(true) {
			ahead(100);			
			back(100);
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
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
