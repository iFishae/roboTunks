package Dernn;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

public class SupremeDalek extends Robot {

	boolean imaboolean;
	boolean peek;
	double moveAmount;
	int tsbh = 0; // Turns since bullet hit
	
	double half = 0;
	


	public void run() {
		setBodyColor(new Color(129,30,42));
		setGunColor(new Color(215,215,217));
		setRadarColor(new Color(217,213,210));
		setBulletColor(new Color(173,212,254));
		setScanColor(new Color(217,213,210));
		
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		half = moveAmount/2;
		peek = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnRight(90);
		turnGunRight(90);
		back(moveAmount);

		
		while(true) {
		ahead(half);
		turnGunRight(90);
		scan();
		turnGunLeft(180);
		scan();
		turnGunRight(90);
		scan();
		ahead(half);
		turnGunRight(90);
		scan();
		turnGunLeft(90);
		// Second Half/Moving back to the other side of the map.
		back(half);
		turnGunLeft(90);
		scan();
		turnGunRight(180);
		scan();
		turnGunLeft(90);
		scan();
		back(half);
		turnGunLeft(90);
		scan();
		turnGunRight(90);
		scan();
		
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		 if (e.getDistance() < 100.0) {
            fire(3.0);
        }
        else {
            fire(3.0);
        }
        if (peek) {
           scan();
        }
    }
	
		

	public void onHitByBullet(HitByBulletEvent e) {
		turnGunRight(90);
		scan();
		turnGun(180);
		scan();
		//if (e.getBearing > ) {
			
		//}
	}

} // "AAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" - Deryn (2022: Trying to defeat Walls)
// "Nvm i got it" - Deryn (2022: Five minutes later)
