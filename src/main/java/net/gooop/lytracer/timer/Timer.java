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
    long splits[];
    private int currentCheckpoint = 0;

    /**
     * Constructor for Timer that includes the number of checkpoints.
     * @param numCheckpoints The number of checkpoints the course has. Note: The splits will contain all checkpoints and the end time.
     */
    public Timer(int numCheckpoints) {
        // Set the size of the splits array to the number of checkpoints + 1 (for the end)
        splits = new long[numCheckpoints + 1];
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    /** 
     * Stops the timer and saves the time. Should be used directly for failures.
     */
    public void stopTimer() {
        endTime = System.currentTimeMillis() - startTime;
        timerRunning = false;
    }

    /*
     * Saves the split time for a given checkpoint, calls stopTimer() if the last checkpoint has been reached
     */
    public void saveSplit() {
        if (currentCheckpoint < splits.length) {
            splits[currentCheckpoint] = getTime();
            currentCheckpoint += 1;
        } else {
            stopTimer();
            splits[currentCheckpoint] = getTime();
        }
    }

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
     * Returns the splits array (in milliseconds)
     */
    public long[] getSplits() {
        return splits;
    }

    /**
     * Returns the splits array (in seconds)
     */
    public float[] getSplitsSeconds() {
        float[] splitsSeconds = new float[splits.length];
        for (int i = 0; i < splits.length; i++) {
            splitsSeconds[i] = splits[i] / 1000.0f;
        }
        return splitsSeconds;
    }

    /**
     * 
     * @return Returns the last split in seconds.
     */
    public float getLastSplitSeconds() {
        if (currentCheckpoint == 0) {
            return 0.0f;
        }
        else {
            return splits[currentCheckpoint] / 1000.0f;
        }
    }

    /**
     * Returns timerRunning
     * @return Boolean, whether the timer is running or not.
     */
    public Boolean getTimerRunning() {
        return timerRunning;
    }
}
