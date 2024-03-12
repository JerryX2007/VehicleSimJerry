import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DayNightCycle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DayNightCycle extends Effect
{
    private static GreenfootImage background;
    private static GreenfootImage image;
    /**
     * Act - do whatever the DayNightCycle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected static GreenfootImage currentBackground(boolean night) {
        if (night) {
            background = new GreenfootImage ("night.png");
        }
        else{
            background = new GreenfootImage ("day.png");
        }
        return background;
    }
}
