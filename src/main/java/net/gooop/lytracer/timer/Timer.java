/*
 * Name: Timer
 * Description: Timer implementation for LytRacer
 * Author(s): Gooop
 * License: MIT
 */

// Package name
package net.gooop.lytracer.timer;

public class Timer {
    private long startTime = 0;
    private long endTime = 0;
    private Boolean timerRunning = false;

    public long getTime() {
        if (timerRunning) {
            return System.currentTimeMillis() - startTime;
        }
        else {
            return endTime;
        }
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    public void stopTimer() {
        endTime = System.currentTimeMillis() - startTime;
        timerRunning = false;
    }

}
