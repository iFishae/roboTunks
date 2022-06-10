// 
// Decompiled by Procyon v0.5.36
// 

package Dernn;

import robocode.HitWallEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;
import robocode.Robot;

public class DannyBotV3 extends Robot
{
    public void run() {
        this.setColors(Color.orange, Color.blue, Color.orange);
        while (true) {
            this.turnGunRight(360.0);
        }
    }
    
    public void onScannedRobot(final ScannedRobotEvent scannedRobotEvent) {
        if (scannedRobotEvent.getDistance() < 150.0) {
            this.fire(3.0);
        }
        else if (scannedRobotEvent.getDistance() >= 150.0 && scannedRobotEvent.getDistance() <= 300.0) {
            this.fire(2.0);
        }
        else {
            this.fire(1.0);
        }
        this.turnRight(scannedRobotEvent.getBearing());
        this.turnGunRight(scannedRobotEvent.getBearing());
        this.ahead(scannedRobotEvent.getDistance() / 2.0);
    }
    
    public void onHitByBullet(final HitByBulletEvent hitByBulletEvent) {
        this.turnLeft(90.0);
        this.ahead(40.0);
    }
    
    public void onHitWall(final HitWallEvent hitWallEvent) {
        this.back(100.0);
    }
}
