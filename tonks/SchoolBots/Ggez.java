// 
// Decompiled by Procyon v0.5.36
// 

package MKA;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;
import robocode.Robot;

public class Ggez extends Robot
{
    boolean peek;
    boolean peekOn;
    int alive;
    double moveAmount;
    boolean turned;
    
    public Ggez() {
        this.turned = false;
    }
    
    public void run() {
        this.setBodyColor(Color.black);
        this.setGunColor(Color.black);
        this.setRadarColor(Color.black);
        this.setBulletColor(Color.red);
        this.setScanColor(Color.red);
        this.moveAmount = Math.max(this.getBattleFieldWidth(), this.getBattleFieldHeight());
        this.moveToWall();
        while (true) {
            this.peek = true;
            this.ahead(this.moveAmount);
            this.peek = false;
            this.turnLeft(90.0);
            this.alive = this.getOthers();
            if (this.alive == 2) {
                continue;
            }
        }
    }
    
    public void onScannedRobot(final ScannedRobotEvent scannedRobotEvent) {
        if (scannedRobotEvent.getDistance() < 10.0) {
            this.fire(3.0);
        }
        else {
            this.fire(3.0);
        }
        if (this.peek) {
            this.scan();
        }
    }
    
    public void onHitByBullet(final HitByBulletEvent hitByBulletEvent) {
    }
    
    public void onHitWall(final HitWallEvent hitWallEvent) {
        this.peekOn = true;
    }
    
    public void onHitRobot(final HitRobotEvent hitRobotEvent) {
    }
    
    public void moveToWall() {
        this.peek = false;
        this.turnLeft(this.getHeading() % 90.0);
        this.ahead(this.moveAmount);
        this.peek = true;
        this.turnLeft(90.0);
    }
}
