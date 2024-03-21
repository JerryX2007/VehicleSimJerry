import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Effect extends Actor
{
    protected GreenfootImage image;
    /**
     * Act - do whatever the Effect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        
    }
    
    protected void fade (int timeLeft, int totalFadeTime){
        double percent = (double)timeLeft / totalFadeTime;
        if (percent > 1.0) return;
        int newTransparency = (int)(percent * 255);
        image.setTransparency(newTransparency);
    }
    
}
