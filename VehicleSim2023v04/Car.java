import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    
    private int xcfsdf;
    
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 3 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 4;
        int z;
        followingDistance = 6;
    }

    public void act()
    {
        super.act();

    }
    
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Pedestrian pedestrianInFront = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, 0, Pedestrian.class);
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2-6, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2+6, Pedestrian.class);
        if(pedestrianInFront != null) {
            if(pedestrianInFront instanceof Adult && pedestrianInFront.isAwake()) {
                pedestrianInFront.knockDown();
                return true;
            }
            else if (pedestrianInFront instanceof Baby) {
                getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                pedestrianInFront.dies();
                return true;
            }
        }
        if(pedestrianOnLeft != null) {
            if(pedestrianOnLeft instanceof Adult && pedestrianOnLeft.isAwake()) {
                pedestrianOnLeft.knockDown();
                return true;
            }
            else if (pedestrianOnLeft instanceof Baby) {
                getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                pedestrianOnLeft.dies();
                return true;
            }
        }
        if(pedestrianOnRight != null) {
            if(pedestrianOnRight instanceof Adult && pedestrianOnRight.isAwake()) {
                pedestrianOnRight.knockDown();
                return true;
            }
            else if (pedestrianOnRight instanceof Baby) {
                getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                pedestrianOnRight.dies();
                return true;
            }
        }
        return false;
    }
}
