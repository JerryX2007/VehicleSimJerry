import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    private int delayCount;
    private double lastSpeed;
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        lastSpeed = speed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(paused()) {
            delayCount--;
            return;
        }
       super.act();
    }
    
    public boolean paused() {
        return delayCount > 0;
    }
    
    public void setDelay(int actCount)
    {
        delayCount = actCount;
    }
    
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        Pedestrian pp = (Pedestrian) getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Pedestrian.class);
        Pedestrian ppp = (Pedestrian) getOneObjectAtOffset(0, (int)(-1*direction * getImage().getHeight()/2 + (int)(-1*direction * speed)), Pedestrian.class);
        if (p != null && p.isAwake())
        {
            p.setAwake(false);
            setMoving(false);
            setDelay(60);
            speed = 0;
            getWorld().removeObject(p);
            return true;
        }
        if(pp != null && pp.isAwake()) {
            pp.setAwake(false);
            setMoving(false);
            setDelay(60);
            getWorld().removeObject(pp);
            return true;
        }
        if(ppp != null && ppp.isAwake()) {
            ppp.setAwake(false);
            setMoving(false);
            setDelay(60);
            getWorld().removeObject(ppp);
            return true;
        }
        setMoving(true);
        return false;
    }
}
