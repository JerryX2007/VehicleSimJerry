import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        maxSpeed = 2.5;
        speed = maxSpeed;
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
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2+6, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2-6, Pedestrian.class);
        
        if(pedestrianInFront != null && !pedestrianInFront.isAwake()) {
            pedestrianInFront.healMe();
            return true;
        }
        if(pedestrianOnLeft != null && !pedestrianOnLeft.isAwake()) {
            pedestrianOnLeft.healMe();
            return true;
        }
        if(pedestrianOnRight != null && !pedestrianOnRight.isAwake()) {
            pedestrianOnRight.healMe();
            return true;
        }
        
        return false;
        
    } 
    
}
