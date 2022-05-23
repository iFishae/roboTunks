package DernnsBots;
import robocode.*;
import java.awt.*;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * uwubot - Deryn / iFishae @ https://github.com/iFishae
 */
public class UwUbot extends Robot
{

	boolean peek;
	double moveAmount;


	public void run() {
		// Colors :3
		setBodyColor(Color.magenta);
		setGunColor(Color.white);
		setRadarColor(Color.magenta);
		setBulletColor(Color.pink);
		setScanColor(Color.cyan);

		// HERE TO SOMEWHERE IS STOLEN CODE FROM WALLS BOT => To understand it of course *eye roll*
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		peek = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);

		peek = true;
		turnGunRight(90);
		turnRight(90);

		// Main Loop
		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
		}




		// Bottom of Initialization / Run Code
	}
	
	// Stolen from Walls bot => moves away from enemy bot(s) if they kiss </3
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// More Stolen Code Here lmao
		fire(3);
		if (peek) {
			scan();
		}
	}


	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		
	}
	

	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		
	}	

	
}
