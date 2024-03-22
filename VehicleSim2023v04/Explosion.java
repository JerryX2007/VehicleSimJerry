import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * IMAGE FROM: https://tenor.com/en-CA/view/nuclear-explosion-starcraft-remastered-terran-boom-nuclear-missile-gif-20426251
 */
public class Explosion extends Gif
{
    private GreenfootSound explosion;
    public Explosion(int time, int width, int height, GifImage image){
        this.time = time;
        this.width = width;
        this.height = height;
        this.image = image;
        
        for(GreenfootImage frame: image.getImages()){
            frame.scale(width, height);
        }
        explosion = new GreenfootSound("explosion.mp3");
        explosion.setVolume(50);
        explosion.play();
    }
    
    public Explosion(int time, int width, int height){
        this.time = time;
        this.width = width;
        this.height = height;
        this.image = new GifImage("explosion.gif");
        
        for(GreenfootImage frame: image.getImages()){
            frame.scale(width, height);
        }
        explosion = new GreenfootSound("explosion.mp3");
        explosion.setVolume(50);
        explosion.play();
    }
    
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        setImage(image.getCurrentImage());
        timeCounter++;
        if(timeCounter >= time){
            getWorld().removeObject(this);
            return;
        }
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>)getIntersectingObjects(Vehicle.class);
        for (Vehicle v : vehicles){
            getWorld().removeObject(v);
        }
        ArrayList<Pedestrian> peds = (ArrayList<Pedestrian>)getIntersectingObjects(Pedestrian.class);
        for (Pedestrian p : peds){
            p.knockDown();
        }
    }
}
