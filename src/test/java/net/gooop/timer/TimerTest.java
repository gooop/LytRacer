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
        Timer timer = new Timer();
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
        Timer timer = new Timer();
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
        Timer timer = new Timer();
        timer.startTimer();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        timer.stopTimer();
        // 10000 is 10 seconds in milliseconds
        assertTrue(Math.abs(timer.getTime() - 10000) < 20);
    }
    
    @Test
    public void getTimeSecondsUnder1Second() {
        Timer timer = new Timer();
        timer.startTimer();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        timer.stopTimer();

        assertTrue(Math.abs(timer.getTimeSeconds() - 0.5f) < 0.02f);
    }

    @Test
    public void getTimeSecondsOver1Second() {
        Timer timer = new Timer();
        timer.startTimer();

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (Exception e) {
            System.out.println("Exception occurred when trying to sleep");
        }

        timer.stopTimer();

        assertTrue(Math.abs(timer.getTimeSeconds() - 5.0f) < 0.02);
    }

}
