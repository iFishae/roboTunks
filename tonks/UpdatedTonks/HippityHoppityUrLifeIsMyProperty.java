package Dernn;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html
public class HippityHoppityUrLifeIsMyProperty extends Robot
{
	boolean peek;
	double moveAmount;
	boolean initializing;

	public void run() {
	
		setBodyColor(new Color(227, 87, 230));
		setGunColor(new Color(173, 5, 72));
		setRadarColor(new Color(120, 4, 122));
		setBulletColor(new Color(129, 227, 217));
		setScanColor(new Color(217,213,210));
		
		//Put Initilization Under Here
		initializing = true;
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		moveToWall();
		initializing = false;
		
		while(true) {
		ahead(moveAmount);
		scan();
		back(moveAmount);
		}
	}
	
	public void moveToWall() {
        peek = false;
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnRight(90);
		turnGunRight(90);
		back(moveAmount);
    }
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}
	
	public void onHitWall(HitWallEvent e) {
		if (initializing = true ) {
		back(30);
	}
		
}
}