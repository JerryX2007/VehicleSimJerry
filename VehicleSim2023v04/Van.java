import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Van extends Vehicle
{
    private boolean isContainingBaby;
    private GreenfootSound cry;
    public Van(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.0 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 8;
        int z;
        followingDistance = 6;
        isContainingBaby = false;
        cry = new GreenfootSound("babyCry.mp3");
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
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2+6, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2-6, Pedestrian.class);
        if(pedestrianInFront != null) {
            if(pedestrianInFront instanceof Adult) {
                pedestrianInFront.knockDown();
                return true;
            }
            else if (pedestrianInFront instanceof Baby) {
                if(isContainingBaby) {
                    getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                    pedestrianInFront.dies();
                }
                else {
                    isContainingBaby = true;
                    makeCry();
                    pedestrianInFront.dies();
                    return true;
                }
                
            }
        }
        if(pedestrianOnLeft != null) {
            if(pedestrianInFront instanceof Adult) {
                pedestrianInFront.knockDown();
                return true;
            }
            else if (pedestrianInFront instanceof Baby) {
                if(isContainingBaby) {
                    getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                    pedestrianInFront.dies();
                    return true;
                }
                else {
                    isContainingBaby = true;
                    makeCry();
                    pedestrianInFront.dies();
                    return true;
                }
                
            }
        }
        if(pedestrianOnRight != null) {
            if(pedestrianInFront instanceof Adult) {
                pedestrianInFront.knockDown();
                return true;
            }
            else if (pedestrianInFront instanceof Baby) {
                if(isContainingBaby) {
                    getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                    pedestrianInFront.dies();
                    return true;
                }
                else {
                    isContainingBaby = true;
                    makeCry();
                    pedestrianInFront.dies();
                    return true;
                }
                
            }
        }
        return false;
        
    }
    public void makeCry() {
        cry.setVolume(30);
        cry.play();
    }
    public void speedAway() {
        
    }
}
