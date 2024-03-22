import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Adult is a type of pedestrian. Their goal is to cross the road or to assist a child being targetted by a robber.
 * 
 * @author Jerry Xing
 * @version 1.0
 */
public class Baby extends Pedestrian
{
    private GreenfootSound cry;
    public Baby(int direction) {
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        entering = true;
        this.direction = direction;
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/12, image.getHeight()/12);
        setImage(image);
        cry = new GreenfootSound("babyCry.mp3");
    }
    /**
     * Act - do whatever the Adult wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void makeCry() {
        cry.setVolume(30);
        cry.play();
    }
}
