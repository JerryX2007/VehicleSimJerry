import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Head subclass
 */
public class Head extends Train
{
    /**
     * Act - do whatever the Head wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Head() {
        super();
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/5, image.getHeight()/5);
    }
    
    public void act()
    {
        super.act();
    }
    
}
