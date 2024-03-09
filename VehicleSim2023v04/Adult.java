import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Adult is a type of pedestrian. Their goal is to cross the road or to assist a child being targetted by a robber.
 * 
 * @author Jerry Xing
 * @version 1.0
 */
public class Adult extends Pedestrian
{
    public Adult(int direction) {
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        entering = true;
        this.direction = direction;
    }
    /**
     * Act - do whatever the Adult wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(paused()) {
            delayCount--;
            return;
        }
        // Awake is false if the Pedestrian is "knocked down"
        if (awake){
            // Check in the direction I'm moving vertically for a Vehicle -- and only move if there is no Vehicle in front of me.
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (speed*direction));
            }
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > getWorld().getHeight() - 30){
                getWorld().removeObject(this);
            }

        }
    }
    public void knockDown () {
        speed = 0;
        setRotation (direction * 90);
        awake = false;
    }

    public void setAwake(boolean state) {
        awake = state;
    }
    
    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe () {
        speed = maxSpeed;
        setRotation (0);
        awake = true;
    }

    public boolean isAwake () {
        return awake;
    }
    
    public boolean paused() {
        return delayCount > 0;
    }
    
    public void setDelay(int actCount)
    {
        delayCount = actCount;
    }
}
