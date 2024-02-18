/*
 * Name: Course
 * Description: Class to hold course data.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.course;

public class Course {
    private int numCheckpoints;

    public Course() {
        numCheckpoints = 3;
    }

    /**
     * Getter for numCheckpoints
     */
    public int getNumCheckpoints() {
        return numCheckpoints;
    }
}
