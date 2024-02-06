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

    /**
    * Returns the the current time (in milliseconds) of a running timer or the total
    * time of a previously run timer.
    *
    * @return time (in milliseconds)
    */
    public long getTime() {
        if (timerRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime;
        }
    }
    
    /**
    * Returns the the current time (in seconds) of a running timer or the total
    * time of a previously run timer.
    *
    * @return time (in seconds)
    */
    public float getTimeSeconds() {
        return getTime() / 1000.0f;
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    /** 
     * Stops the timer and saves the time.
     */
    public void stopTimer() {
        endTime = System.currentTimeMillis() - startTime;
        timerRunning = false;
    }

}
