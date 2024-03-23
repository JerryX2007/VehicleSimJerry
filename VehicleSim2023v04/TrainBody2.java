import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TrainBody2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrainBody2 extends Train
{
    public TrainBody2() {
        super();
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/5, image.getHeight()/5);
    }
    /**
     * Act - do whatever the TrainBody2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
    }
}
