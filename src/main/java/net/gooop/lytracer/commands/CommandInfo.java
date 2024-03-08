/*
 * Name: CommandAnnotation
 * Description: Command annotation definition
 * Author(s): Gooop
 * License: MIT
 */
package net.gooop.lytracer.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
}