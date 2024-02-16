/*
 * Name: TimerTest
 * Description: Tests the Timer class for LytRacer
 * Author(s): Gooop
 * License: MIT
 */
// Package name
package net.gooop.timer;

// Junit imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Misc imports
import java.util.concurrent.TimeUnit;

// UUT imports
import net.gooop.lytracer.timer.Timer;

public class TimerTest {
    @Test
    public void startTimerStartsTimer() {
        // Arrange
        Timer timer = new Timer(0);
        assertEquals(timer.getTime(), 0);
        timer.startTimer();

        // Act
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        // Assert
        assertTrue(timer.getTime() > 0);
    }

    @Test
    public void stopTimerStopsTimer() {
        // Arrange
        Timer timer = new Timer(0);
        timer.startTimer();

        // Act
        long firstTime = 0;
        long secondTime = 0;
        long thirdTime = 0;
        try {
            TimeUnit.SECONDS.sleep(1);
            firstTime = timer.getTime();
            TimeUnit.SECONDS.sleep(1);
            timer.stopTimer();
            secondTime = timer.getTime();
            TimeUnit.SECONDS.sleep(1);
            thirdTime = timer.getTime();
        }
        catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        // Assert
        assertNotEquals(firstTime, secondTime);
        assertEquals(secondTime, thirdTime);
    }

    @Test
    public void timerIsRelativelyAccurate() {
        // Arrange
        Timer timer = new Timer(0);
        timer.startTimer();

        // Act
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }
        timer.stopTimer();

        // Assert
        // 10000 is 10 seconds in milliseconds
        assertTrue(Math.abs(timer.getTime() - 10000) < 20);
    }
    
    @Test
    public void getTimeSecondsUnder1Second() {
        // Arrange
        Timer timer = new Timer(0);
        timer.startTimer();

        // Act
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }
        timer.stopTimer();

        // Assert
        assertTrue(Math.abs(timer.getTimeSeconds() - 0.5f) < 0.02f);
    }

    @Test
    public void getTimeSecondsOver1Second() {
        // Arrange
        Timer timer = new Timer(0);
        timer.startTimer();

        // Act
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }
        timer.stopTimer();

        // Assert
        assertTrue(Math.abs(timer.getTimeSeconds() - 5.0f) < 0.02);
    }

    @Test
    public void saveSplit0Checkpoints() {
        // Arrange
        int waitTime = 5000;
        Timer timer = new Timer(0);
        timer.startTimer();

        // Act
        try {
            TimeUnit.MILLISECONDS.sleep(waitTime);
            timer.saveSplit();
            TimeUnit.MILLISECONDS.sleep(waitTime);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        // Assert
        float[] splits = new float[1];
        splits = timer.getSplitsSeconds();
        assertTrue(Math.abs(splits[0] - 5.0f) < 0.02); // TODO: This shouldn't pass but does
    }

    @Test
    public void saveSplit1Checkpoint() {
        // Arrange
        int waitTime = 5000;
        Timer timer = new Timer(1);
        timer.startTimer();

        // Act
        try {
            TimeUnit.MILLISECONDS.sleep(waitTime);
            timer.saveSplit();
            TimeUnit.MILLISECONDS.sleep(waitTime);
            timer.saveSplit();
            TimeUnit.MILLISECONDS.sleep(waitTime);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        // Assert
        float[] splits = new float[2];
        splits = timer.getSplitsSeconds();
        assertTrue(Math.abs(splits[0] - 5.0f) < 0.02);
        assertTrue(Math.abs(splits[1] - 10.0f) < 0.04);
    }

    @Test
    public void saveSplit15Checkpoints() {
        // Arrange
        int waitTime = 1000;
        Timer timer = new Timer(15);
        timer.startTimer();

        // Act
        try {
            for (int i = 0; i < 16; i++) {
                timer.saveSplit();
                TimeUnit.MILLISECONDS.sleep(waitTime);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        // Assert
        float[] splits = new float[2];
        splits = timer.getSplitsSeconds();
        for (int i = 0; i < splits.length; i++) {
            float waitTimeSeconds = (float) ((waitTime) / 1000) * i;
            float difference = Math.abs(splits[i] - waitTimeSeconds);
            assertTrue(difference < 0.02 * (i + 1));
        }
    }

}
