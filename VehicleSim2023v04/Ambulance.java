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
        boolean healed = false;
        ArrayList<Pedestrian> allPedestrians = (ArrayList<Pedestrian>) getObjectsAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        ArrayList<Pedestrian> leftPedestrians = (ArrayList<Pedestrian>) getObjectsAtOffset((int)speed+getImage().getWidth()/2, -getImage().getHeight()-30, Pedestrian.class);
        ArrayList<Pedestrian> rightPedestrians = (ArrayList<Pedestrian>) getObjectsAtOffset((int)speed+getImage().getWidth()/2, getImage().getHeight()+30, Pedestrian.class);
        for( Pedestrian p : leftPedestrians) {
            allPedestrians.add(p);
        }
        for( Pedestrian p : rightPedestrians) {
            allPedestrians.add(p);
        }
        for( Pedestrian p : allPedestrians) {
            p.healMe();
            healed = true;
        }
        if(healed) {return true;}
        else {
            return false;
        }
        
    }
    
}
