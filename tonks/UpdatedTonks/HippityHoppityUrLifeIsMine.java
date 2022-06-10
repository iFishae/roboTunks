package Dernn;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;
import robocode.Robot;

public class HippityHoppityUrLifeIsMyProperty extends Robot
{
    boolean peek;
    boolean peekOn;
    int alive;
    double moveAmount;
    boolean turned;
    
    public HippityHoppityUrLifeIsMyProperty() {
        turned = false;
    }
    
    public void run() {
        setBodyColor(new Color(227, 87, 230));
		setGunColor(new Color(173, 5, 72));
		setRadarColor(new Color(120, 4, 122));
		setBulletColor(new Color(173,212,254));
		setScanColor(new Color(217,213,210));
        moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
        moveToWall();
        while (true) {
            peek = true;
            ahead(moveAmount);
            peek = false;
            turnLeft(90.0);
            alive = getOthers();
            if (alive == 2) {
                continue;
            }
        }
    }
    
    public void onScannedRobot(final ScannedRobotEvent scannedRobotEvent) {
        if (scannedRobotEvent.getDistance() < 10.0) {
            fire(3.0);
        }
        else {
            fire(3.0);
        }
        if (peek) {
            scan();
        }
    }
    
    public void onHitByBullet(final HitByBulletEvent hitByBulletEvent) {
    }
    
    public void onHitWall(final HitWallEvent hitWallEvent) {
        peekOn = true;
    }
    
    public void onHitRobot(final HitRobotEvent hitRobotEvent) {
    }
    
    public void moveToWall() {
        peek = false;
        turnLeft(getHeading() % 90.0);
        ahead(moveAmount);
        peek = true;
        turnLeft(90.0);
    }
}
