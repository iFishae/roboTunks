package Dernn3;

import robocode.*;
import java.awt.Color;

public class Murder extends Robot {
    private static final double WALL_MARGIN = 18.0;

    @Override
    public void run() {
        setColors();
        while (true) {
            turnRadarRight(360); // Continuously scan the entire arena
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        double firePower = calculateFirePower(event.getDistance());
        double bulletSpeed = calculateBulletSpeed(firePower);
        double[] futurePosition = predictFuturePosition(event, bulletSpeed);

        futurePosition = constrainPositionToBounds(futurePosition);

        double absoluteBearing = calculateAbsoluteBearing(futurePosition);
        double bearingFromGun = normalizeToPlusMinus180(absoluteBearing - getGunHeading());

        lockRadar(event);
        aimAndFire(bearingFromGun, firePower);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        // Add any hit-by-bullet behavior here if needed
    }

    private void setColors() {
        setBodyColor(new Color(100, 87, 80));
        setGunColor(new Color(213, 207, 204));
        setRadarColor(new Color(181, 184, 228));
        setBulletColor(new Color(139, 184, 249));
        setScanColor(new Color(139, 184, 249));
    }

    private double calculateFirePower(double distance) {
        return Math.max(2, Math.min(400 / distance, 3));
    }

    private double calculateBulletSpeed(double firePower) {
        return 20 - 3 * firePower;
    }

    private double[] predictFuturePosition(ScannedRobotEvent event, double bulletSpeed) {
        double distance = event.getDistance();
        double futureX = getX() + distance * Math.sin(Math.toRadians(getHeading() + event.getBearing()));
        double futureY = getY() + distance * Math.cos(Math.toRadians(getHeading() + event.getBearing()));
        double predictedTimeToHit = distance / bulletSpeed;

        for (int i = 0; i < 5; i++) {
            double deltaTime = predictedTimeToHit;
            futureX = getX() + event.getDistance() * Math.sin(Math.toRadians(getHeading() + event.getBearing())) + event.getVelocity() * deltaTime * Math.sin(Math.toRadians(event.getHeading()));
            futureY = getY() + event.getDistance() * Math.cos(Math.toRadians(getHeading() + event.getBearing())) + event.getVelocity() * deltaTime * Math.cos(Math.toRadians(event.getHeading()));
            predictedTimeToHit = Math.sqrt(Math.pow(futureX - getX(), 2) + Math.pow(futureY - getY(), 2)) / bulletSpeed;
        }

        return new double[]{futureX, futureY};
    }

    private double[] constrainPositionToBounds(double[] position) {
        double futureX = Math.max(WALL_MARGIN, Math.min(getBattleFieldWidth() - WALL_MARGIN, position[0]));
        double futureY = Math.max(WALL_MARGIN, Math.min(getBattleFieldHeight() - WALL_MARGIN, position[1]));
        return new double[]{futureX, futureY};
    }

    private double calculateAbsoluteBearing(double[] position) {
        return Math.toDegrees(Math.atan2(position[0] - getX(), position[1] - getY()));
    }

    private void lockRadar(ScannedRobotEvent event) {
        double radarTurn = normalizeToPlusMinus180(getHeading() + event.getBearing() - getRadarHeading());
        turnRadarRight(radarTurn);
    }

    private void aimAndFire(double bearingFromGun, double firePower) {
        turnGunRight(bearingFromGun);
        if (Math.abs(bearingFromGun) <= 2) {
            fire(firePower);
        }
    }

    private double normalizeToPlusMinus180(double angle) {
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
