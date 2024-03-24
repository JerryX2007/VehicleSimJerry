import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    
    private static GreenfootSound[] revs;
    private static int revIndex;
    
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 3 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 2;
        followingDistance = 6;
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/5, image.getHeight()/5);
        setImage(image);
        playRevSound();
    }

    public void act()
    {
        super.act();
    }
    
    /**
     * When a Car hit's an Adult, it should knock it over
     * When a Car hits a Baby, it should explode
     */
    public boolean checkHitPedestrian () {
        Pedestrian pedestrianInFront = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, 0, Pedestrian.class);
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2-6, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2+6, Pedestrian.class);
        
        // Check if the pedestrian is an instance of an Adult or a Baby. If it is an Adult, run the Adult over. If it is a Baby, explode.
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
    
    /*
     * Method to initialize the sounds for this class
     */
    public static void init() {
        revIndex = 0;
        revs = new GreenfootSound[6];
        for (int i=0;i<revs.length;i++) {
            revs[i] = new GreenfootSound("rev.mp3");
            revs[i].setVolume(40);
        }
    }
    
    //Code from Mr. Cohen
    public void playRevSound() {
        revs[revIndex].play();
        revIndex++;
        if (revIndex >= revs.length){
            revIndex = 0;
        }
    }
    
}
