import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Train here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Train extends SuperSmoothMover
{
    protected int speed;
    protected boolean moving;
    protected int actCount;
    protected static GreenfootSound trainWhistle;
    protected static GreenfootSound trainPassing;
    protected static int trainIndex;
    protected boolean stoppedAtMiddle;
    public Train() {
        moving = true;
        speed = 2;
        stoppedAtMiddle = false;
        actCount = 0;
    }
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (checkEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        if(stoppedAtMiddle) {
            //Get all the trains and make them stop moving
            ArrayList<Train> trains = (ArrayList<Train>) getWorld().getObjects(Train.class);
            for (Train train : trains) {
                if(train != null) {
                    train.stopMoving();
                }
            }
            actCount++;
            if(actCount >= 600) {
                for(Train train: trains) {
                    if(train != null) {
                        train.startMoving();
                        trainWhistle.play();
                        fadeSound(trainPassing, 75, 0, false, 0.5);
                    }
                }
                actCount = 0;
                stoppedAtMiddle = false;
            }
        }
        move(speed);
    }
    
    protected boolean checkEdge() {
        if (getX() > getWorld().getWidth() + 200){
            return true;
        }
        return false;
    }
    
    public static void init() {
        trainPassing = new GreenfootSound("trainPassing.mp3");
        
        trainWhistle = new GreenfootSound("trainWhistle.mp3");
        trainWhistle.setVolume(70);
    }
    
    //Code from Mr. Cohen
    public static void trainApproaching() {
        trainWhistle.play();
        fadeSound(trainPassing, 0, 75, true, 0.5);
    }
    
    public static void fadeSound(GreenfootSound sound, int start, int degree, boolean louder, double increment) {
        sound.play();
        if (louder) {
            for(int i = start; i<=degree; i+=increment) {
                sound.setVolume(i);
            }
        }
        else {
            for (int i= start; i>0;i-=increment) {
                sound.setVolume(i);
            }
        }
    }
    
    public void stopMoving() {
        speed = 0;
    }
    
    public void startMoving() {
        speed = 2;
    }
    
}
