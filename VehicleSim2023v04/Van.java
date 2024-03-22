import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Van extends Vehicle
{
    private boolean isContainingBaby;
    
    public Van(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.0 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 8;
        int z;
        followingDistance = 6;
        isContainingBaby = false;
    }

    public void act()
    {
        super.act();

    }
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Baby p = (Baby)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Baby.class);
        if (p == null){
            p = (Baby)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, -getImage().getHeight(), Baby.class);
        }
        if(p == null){
            p = (Baby)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, getImage().getHeight(), Baby.class);
        }
        if (p != null && !isContainingBaby)
        {
            getWorld().removeObject(p);
            isContainingBaby = true;
            p.makeCry();
            return true;
        }
        return false;
        
    }
}
