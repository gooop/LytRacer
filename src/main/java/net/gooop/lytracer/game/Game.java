/*
 * Name: Game
 * Description: Class to handle a race. All game logic is done here.
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.game;

// Misc imports
import java.util.UUID;

// LytRacer Specific Imports
import net.gooop.lytracer.timer.*;

import net.gooop.lytracer.course.*;

public class Game {
    private Timer timer;
    private Course course;

    /**
     * Constructor for Game class that includes id and course
     * @param id The game ID assigned to this game instance.
     * @param course The course associated with this game instance.
     */
    public Game(UUID id, Course course) {
        this.course = course;
    }

    /**
     * Starts the game
     */
    public void start() {
        timer = new Timer(course.getNumCheckpoints());
    }
    
    /**
     * Stops the game
     */
    public void stop() {
        timer.stopTimer();
    }

}
