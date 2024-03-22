import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Snowstorm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rain extends Effect
{
    private int actsLeft, turnAroundAct;
    private double speed;
    private int direction;
    private boolean turned;
    private double brakeForceNeeded;

    private GreenfootSound windSound;

    public Rain () {
        image = new GreenfootImage("rain.png");
        setImage (image);
        actsLeft = 300;

        turned = false;

        turnAroundAct = (actsLeft / 2) + (Greenfoot.getRandomNumber(60) - 30);
        speed = 3.0;
        direction = 1;
    }

    public void addedToWorld (World w){
        setLocation (w.getWidth()/2, w.getHeight()/2);
        
    }

    /**
     * Act - do whatever the Snowstorm wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (this != null && getWorld() != null) { // Check if the object and its world are still valid
            actsLeft--;
            fade(actsLeft, 120);
    
            applyMove();
            
            if (actsLeft == 0){
                getWorld().removeObject(this);
                return;
            }
            
            // From Mr. Cohen's demo code
            // slows down anything that touches it
            ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getIntersectingObjects(Vehicle.class);
            for (Vehicle v : vehicles){
                v.setSpeed(0.55);
            }
            ArrayList<Pedestrian> peds = (ArrayList<Pedestrian>) getIntersectingObjects(Pedestrian.class);
            for (Pedestrian p : peds){
                p.setSpeed(0.55);
            }
        }
    }

    private void applyMove(){
        int speedChange = Greenfoot.getRandomNumber(20);
        if (actsLeft == turnAroundAct){
            direction *= -1;
            speed = 1.0;
        }
        else if (actsLeft - turnAroundAct == 30){
            brakeForceNeeded = speed / 30;
        }
        else if (actsLeft - turnAroundAct < 30 && actsLeft - turnAroundAct > 0){
            speed = Math.max(0, speed -brakeForceNeeded);
        }      
        else  if (speedChange > 15){
            speed += speedChange / 100.0;
        }

        setLocation (getX(), getY()- ((int)speed*direction));
    }

}
