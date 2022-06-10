
package Dernn;

import robocode.HitWallEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;
import robocode.Robot;

public class Hourglass extends Robot
{
    public void run() {
        this.turnLeft(this.getHeading() % 90.0);
        this.ahead(800.0);
        this.turnRight(90.0);
        this.turnGunRight(90.0);
        this.setColors(Color.red, Color.red, Color.red);
        while (true) {
            this.ahead(800.0);
            this.turnLeft(45.0);
            this.back(1000.0);
            this.turnGunRight(360.0);
            this.turnGunRight(360.0);
            this.turnRight(45.0);
            this.turnGunRight(180.0);
            this.ahead(800.0);
            this.turnRight(45.0);
            this.back(1000.0);
            this.turnGunRight(360.0);
            this.turnGunRight(360.0);
            this.turnLeft(45.0);
            this.turnGunRight(180.0);
        }
    }
    
    public void onScannedRobot(final ScannedRobotEvent scannedRobotEvent) {
        this.fire(3.0);
        this.setColors(Color.red, Color.red, Color.red);
    }
    
    public void onHitByBullet(final HitByBulletEvent hitByBulletEvent) {
        if (hitByBulletEvent.getBearing() > 0.0) {
            this.turnGunRight(hitByBulletEvent.getBearing());
        }
        else {
            this.turnGunLeft(hitByBulletEvent.getBearing());
        }
        this.setColors(Color.white, Color.white, Color.white);
    }
    
    public void onHitWall(final HitWallEvent hitWallEvent) {
        this.setColors(Color.blue, Color.blue, Color.blue);
    }
}
