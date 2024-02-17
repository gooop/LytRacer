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

    public Game(UUID id, Course course) {
        this.course = course;
    }

    public void start() {
        timer = new Timer(course.getNumCheckpoints());
    }
    
    public void stop() {
        timer.stopTimer();
    }

}
