import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Baby is a type of Pedestrian. They will usually explode when run over or get kidnapped. Their goal is to cross the street.
 */
public class Baby extends Pedestrian
{
    public Baby(int direction) {
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
