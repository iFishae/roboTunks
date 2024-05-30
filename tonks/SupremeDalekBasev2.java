package Dernn2;

import robocode.ScannedRobotEvent;
import robocode.HitByBulletEvent;
import java.awt.Color;
import robocode.Robot;

public class SupremeDalek extends Robot {
    final double WALL_MARGIN = 18;

    public void run() {
        setBodyColor(new Color(129, 30, 42));
        setGunColor(new Color(215, 215, 217));
        setRadarColor(new Color(217, 213, 210));
        setBulletColor(new Color(173, 212, 254));
        setScanColor(new Color(217, 213, 210));

        while (true) {
            turnRadarRight(360); // Continuously scan the entire arena
        }
    }

    public void onScannedRobot(ScannedRobotEvent event) {
        double firePower = Math.max(2, Math.min(400 / event.getDistance(), 3)); // Adjust firepower to be at least level 2
        double bulletSpeed = 20 - 3 * firePower;
        double distance = event.getDistance();

        // Initial predicted future position
        double futureX = getX() + distance * Math.sin(Math.toRadians(getHeading() + event.getBearing()));
        double futureY = getY() + distance * Math.cos(Math.toRadians(getHeading() + event.getBearing()));
        double predictedTimeToHit = distance / bulletSpeed;

        // Iterate to refine prediction
        for (int i = 0; i < 5; i++) {
            double deltaTime = predictedTimeToHit;
            futureX = getX() + event.getDistance() * Math.sin(Math.toRadians(getHeading() + event.getBearing())) + event.getVelocity() * deltaTime * Math.sin(Math.toRadians(event.getHeading()));
            futureY = getY() + event.getDistance() * Math.cos(Math.toRadians(getHeading() + event.getBearing())) + event.getVelocity() * deltaTime * Math.cos(Math.toRadians(event.getHeading()));
            predictedTimeToHit = Math.sqrt(Math.pow(futureX - getX(), 2) + Math.pow(futureY - getY(), 2)) / bulletSpeed;
        }

        // Ensure the future position is within battlefield bounds
        double battlefieldWidth = getBattleFieldWidth();
        double battlefieldHeight = getBattleFieldHeight();

        futureX = Math.max(WALL_MARGIN, Math.min(battlefieldWidth - WALL_MARGIN, futureX));
        futureY = Math.max(WALL_MARGIN, Math.min(battlefieldHeight - WALL_MARGIN, futureY));

        // Calculate absolute bearing to the predicted future position
        double absoluteBearing = Math.toDegrees(Math.atan2(futureX - getX(), futureY - getY()));
        double bearingFromGun = normalizeToPlusMinus180(absoluteBearing - getGunHeading());

        // Lock radar on the enemy
        double radarTurn = normalizeToPlusMinus180(getHeading() + event.getBearing() - getRadarHeading());
        turnRadarRight(radarTurn);

        // Turn gun towards the predicted position
        turnGunRight(bearingFromGun);

        // Fire only if the gun is almost aligned with the target
        if (Math.abs(bearingFromGun) <= 2) {
            fire(firePower);
        }
    }

    public void onHitByBullet(HitByBulletEvent event) {
        // Add any hit-by-bullet behavior here if needed
    }

    public double normalizeToPlusMinus180(double angle) {
        angle %= 360.0;
        if (angle > 180.0) {
            angle -= 360.0;
        }
        if (angle < -180.0) {
            angle += 360.0;
        }
        return angle;
    }
}
