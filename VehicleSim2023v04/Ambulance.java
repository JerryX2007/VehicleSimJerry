import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    private static GreenfootSound[] reviveSounds;
    private static int reviveSoundsIndex = 0;
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        maxSpeed = 2.5;
        speed = maxSpeed;
        yOffset = 3;
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/3, image.getHeight()/3-3);
        setImage(image);
    }
    
    /*
     * Method to initialize the sounds for this class
     */
    public static void init() {
        reviveSoundsIndex = 0;
        reviveSounds = new GreenfootSound[24];
        for (int i=0;i<reviveSounds.length;i++) {
            reviveSounds[i] = new GreenfootSound("fortniteRevive.mp3");
            reviveSounds[i].setVolume(30);
        }
    }
    
    //Code from Mr. Cohen
    public void playReviveSound() {
        reviveSounds[reviveSoundsIndex].play();
        reviveSoundsIndex++;
        if (reviveSoundsIndex >= reviveSounds.length){
            reviveSoundsIndex = 0;
        }
    }
    
    /**
     * Act - do whatever the Ambulance wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }

    public boolean checkHitPedestrian () {
        Pedestrian pedestrianInFront = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, 0, Pedestrian.class);
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2+3, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2-4, Pedestrian.class);
        
        //Check if the Pedestrian is awake. If it is not awake, then revive the Pedestrian
        
        if(pedestrianInFront != null && !pedestrianInFront.isAwake()) {
            pedestrianInFront.healMe();
            playReviveSound();
            return true;
        }
        if(pedestrianOnLeft != null && !pedestrianOnLeft.isAwake()) {
            pedestrianOnLeft.healMe();
            playReviveSound();
            return true;
        }
        if(pedestrianOnRight != null && !pedestrianOnRight.isAwake()) {
            pedestrianOnRight.healMe();
            playReviveSound();
            return true;
        }
        
        return false;
        
    } 
    
}
