// 
// Decompiled by Procyon v0.5.36
// 

package Robot;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;
import robocode.Robot;

public class InterceptorV2 extends Robot
{
    double lastHeadDif;
    int timer;
    final int maxTimer = 60;
    double gunTargDeg;
    double dis;
    double prefDis;
    
    public void run() {
        this.lastHeadDif = 0.0;
        this.gunTargDeg = 0.0;
        this.timer = 0;
        this.prefDis = 100.0;
        this.setColors(Color.orange, Color.blue, Color.green);
        while (true) {
            if (this.timer == 60) {
                --this.timer;
                this.ahead(40.0);
            }
            else if (this.timer > 0) {
                --this.timer;
                if (this.timer * 4 < 180) {
                    this.trackTarget();
                }
                else {
                    this.turnGunRight((double)(int)(60.0 * Math.random()));
                }
                if (this.timer * 2 < 60 && this.timer % 5 == 0) {
                    if (this.timer % 2 == 0) {
                        this.turnRight((double)(int)(50.0 * Math.random()));
                    }
                    else {
                        this.turnLeft((double)(int)(50.0 * Math.random()));
                    }
                    this.ahead(20.0);
                    System.out.println("turning");
                    this.goToRadius();
                }
                this.ahead(1.0);
            }
            else {
                this.turnGunLeft(30.0);
                this.turnRight(5.0);
            }
        }
    }
    
    public void trackTarget() {
        final double n = -1.0 * (this.normalizeToPlusMinus180(this.getHeading()) - this.normalizeToPlusMinus180(this.getGunHeading()));
        this.gunTargDeg = this.lastHeadDif - n;
        final double normalizeToPlusMinus180 = this.normalizeToPlusMinus180(n);
        this.gunTargDeg = this.normalizeToPlusMinus180(this.gunTargDeg);
        System.out.println("Deg: " + (int)this.gunTargDeg + " lastHeadDif:" + (int)this.lastHeadDif + " /\\ Gun Body Heading: " + (int)normalizeToPlusMinus180);
        if (Math.abs(this.gunTargDeg) > 1.0) {
            if (this.gunTargDeg > 0.0) {
                this.turnGunRight(Math.abs(this.gunTargDeg));
            }
            else {
                this.turnGunLeft(Math.abs(-1.0 * this.gunTargDeg));
            }
        }
    }
    
    public void onScannedRobot(final ScannedRobotEvent scannedRobotEvent) {
        this.fire(1.0 + 2.0 * (1.0 / this.dis));
        this.lastHeadDif = this.normalizeToPlusMinus180(scannedRobotEvent.getBearing());
        this.dis = scannedRobotEvent.getDistance();
        this.timer = 60;
        this.trackTarget();
    }
    
    public void goToRadius() {
        final int n = 25;
        final double n2 = 2 * n * Math.random() - n;
        final int n3 = 20 + (int)(60.0 * Math.random());
        final double n4 = n2 + this.lastHeadDif;
        this.turnRight(n4);
        this.turnGunLeft(n4);
        this.lastHeadDif -= n2;
        if (this.dis < this.prefDis) {
            this.back((double)n3);
        }
        if (this.dis > this.prefDis) {
            this.ahead((double)n3);
        }
    }
    
    public void onHitByBullet(final HitByBulletEvent hitByBulletEvent) {
    }
    
    public void onHitWall(final HitWallEvent hitWallEvent) {
        this.turnLeft(90.0);
    }
    
    public void onHitRobot(final HitRobotEvent hitRobotEvent) {
        this.ahead(80.0);
        this.turnLeft(90.0);
    }
    
    public double normalizeToPlusMinus180(double n) {
        n %= 360.0;
        if (n > 180.0) {
            n -= 360.0;
        }
        if (n < -180.0) {
            n += 360.0;
        }
        return n;
    }
}
