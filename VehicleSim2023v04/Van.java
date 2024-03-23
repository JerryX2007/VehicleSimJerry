import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Van extends Vehicle
{
    private boolean isContainingBaby;
    private static GreenfootSound[] crys;
    private static int cryingIndex;
    private static GreenfootSound[] vrooms;
    private static int vroomIndex;
    public Van(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.0 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 8;
        int z;
        followingDistance = 6;
        isContainingBaby = false;
        GreenfootImage image = getImage();
        image.scale(image.getWidth(), 54);
    }

    public void act()
    {
        super.act();
    }
    
    public static void init() {
        cryingIndex = 0;
        crys = new GreenfootSound[3];
        for (int i=0;i<crys.length;i++) {
            crys[i] = new GreenfootSound("babyCry.mp3");
            crys[i].setVolume(30);
        }
        
        vroomIndex = 0;
        vrooms = new GreenfootSound[3];
        for (int i=0;i<vrooms.length;i++) {
            vrooms[i] = new GreenfootSound("vroom.mp3");
            vrooms[i].setVolume(30);
        }
    }
    
    //Code from Mr. Cohen
    public void playCryingSound() {
        crys[cryingIndex].play();
        cryingIndex++;
        if (cryingIndex >= crys.length){
            cryingIndex = 0;
        }
    }
    
    public void playVroomSound() {
        vrooms[vroomIndex].play();
        vroomIndex++;
        if (vroomIndex >= vrooms.length) {
            vroomIndex = 0;
        }
    }
    
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Pedestrian pedestrianInFront = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, 0, Pedestrian.class);
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2, Pedestrian.class);
        if(pedestrianInFront != null) {
            if(pedestrianInFront instanceof Adult && pedestrianInFront.isAwake()) {
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
                    playCryingSound();
                    pedestrianInFront.dies();
                    speedAway();
                    return true;
                }
                
            }
        }
        if(pedestrianOnLeft != null) {
            if(pedestrianOnLeft instanceof Adult && pedestrianOnLeft.isAwake()) {
                pedestrianOnLeft.knockDown();
                return true;
            }
            else if (pedestrianOnLeft instanceof Baby) {
                if(isContainingBaby) {
                    getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                    pedestrianOnLeft.dies();
                    return true;
                }
                else {
                    isContainingBaby = true;
                    playCryingSound();
                    pedestrianOnLeft.dies();
                    speedAway();
                    return true;
                }
                
            }
        }
        if(pedestrianOnRight != null) {
            if(pedestrianOnRight instanceof Adult && pedestrianOnRight.isAwake()) {
                pedestrianOnRight.knockDown();
                return true;
            }
            else if (pedestrianOnRight instanceof Baby) {
                if(isContainingBaby) {
                    getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                    pedestrianOnRight.dies();
                    return true;
                }
                else {
                    isContainingBaby = true;
                    playCryingSound();
                    pedestrianOnRight.dies();
                    speedAway();
                    return true;
                }
            }
        }
        return false;
        
    }
    
    /*
     * Method to make the van speed away after "kidnapping" a child
     */
    public void speedAway() {
        maxSpeed += 5;
        playVroomSound();
    }
}
