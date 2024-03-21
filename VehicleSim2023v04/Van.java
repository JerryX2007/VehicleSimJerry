import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Van extends Vehicle
{
    private boolean isContainingChild;
    
    public Van(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.0 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 8;
        int z;
        followingDistance = 6;
        isContainingChild = false;
    }

    public void act()
    {
        super.act();

    }
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        if (p instanceof Adult) {
            if (p != null)
            {
                p.knockDown();
                return true;
            }
        }
        return false;
        
    }
}
