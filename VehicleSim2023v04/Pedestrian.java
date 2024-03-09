import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected int delayCount;
    protected double maxSpeed;
    protected int direction; // direction is always -1 or 1, for moving down or up, respectively
    protected boolean awake, entering;

    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public abstract void act();

    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    public abstract void knockDown ();

    public abstract void setAwake(boolean state);
    
    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public abstract void healMe ();

    public abstract boolean isAwake ();
    
    public abstract boolean paused();
    
    public abstract void setDelay(int actCount);
}
