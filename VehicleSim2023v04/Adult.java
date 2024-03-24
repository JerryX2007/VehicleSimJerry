import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Adult is a type of pedestrian. Their goal is to cross the road.
 */
public class Adult extends Pedestrian
{
    public Adult(int direction) {
        super(direction);
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/12, image.getHeight()/12);
        setImage(image);
    }
    /**
     * Act - do whatever the Adult wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
