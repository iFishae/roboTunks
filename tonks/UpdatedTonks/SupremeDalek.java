package Dernn;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

public class SupremeDalek extends Robot {

	boolean initializing;
	boolean imaboolean;
	boolean peek;
	double moveAmount;
	int bullethit = 0;
	double half = 0;


	public void run() {
		setBodyColor(new Color(129,30,42));
		setGunColor(new Color(215,215,217));
		setRadarColor(new Color(217,213,210));
		setBulletColor(new Color(173,212,254));
		setScanColor(new Color(217,213,210));
		
		initializing = true;
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		half = moveAmount/2;
		peek = false;

		//turnLeft(getHeading() % 90);
		//ahead(moveAmount);
		//peek = true;
		//turnRight(90);
		//turnGunRight(90);
		//back(moveAmount);
		//initializing = false;
		moveToWall();
		turnGunRight(90);
		turnRight(90);

		
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
		 if (e.getDistance() > 300.0) {
            fire(2.0);
        }
        else {
            fire(3.0);
        }
        if (peek) {
           scan();
        }
    }
	
	public void onBulletHit(BulletHitEvent e) {
		bullethit++;
	}
		

	public void onHitByBullet(HitByBulletEvent e) {
		if (initializing = true) {
			back(200);
		}
		scan();
		if (bullethit >= 3) {
			turnLeft(getHeading() % 90);
			bullethit = 0;
		}
		
	public void moveToWall() {
        peek = false;
        turnLeft(getHeading() % 90.0);
        ahead(moveAmount);
        peek = true;
        turnLeft(90.0);
    }
			
	}

} // "AAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" - Deryn (2022: Trying to defeat Walls)
// "Nvm i got it" - Deryn (2022: Five minutes later)