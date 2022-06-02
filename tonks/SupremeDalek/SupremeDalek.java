package DernnsBots;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

public class SupremeDalek extends Robot {

	boolean imaboolean;
	boolean peek;
	double moveAmount;
	int safe = 0; // How many times bot has been hit by a bullet
	int tsbh = 0; // Turns since bullet hit
	


	public void run() {
		setBodyColor(new Color(129,30,42));
		setGunColor(new Color(215,215,217));
		setRadarColor(new Color(217,213,210));
		setBulletColor(new Color(173,212,254));
		setScanColor(new Color(217,213,210));
		
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		peek = false;
		imaboolean = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnRight(90);
		turnGunRight(90);

		
		while(true) {
		ahead(moveAmount);
		imaboolean = true;
		turnGunRight(90);
		scan();
		safe++;
		turnGunLeft(90);
		imaboolean = false;
		back(moveAmount);
		imaboolean = true;
		turnGunLeft(90);
		scan();
		imaboolean = false;
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
		scan();
	}
	

		
	public void onHitRobot(HitRobotEvent e) {
			if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		}
		else {
			ahead(100);
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		if (safe > 4) {
			ahead(100);
		}
	}

} // "AAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" - Deryn (2022: Trying to defeat Walls)
// "Nvm i got it" - Deryn (2022: Five minutes later)
