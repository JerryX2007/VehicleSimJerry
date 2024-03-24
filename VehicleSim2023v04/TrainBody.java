import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The TrainBody subclass
 */
public class TrainBody extends Train
{
    private GreenfootImage trainBody1;
    private GreenfootImage trainBody2;
    private GreenfootImage trainBody3;
    
    private boolean isMiddleTrain;
    private boolean reachedBefore;
    
    public TrainBody(boolean middleTrain) {
        super();
        isMiddleTrain = middleTrain;
        trainBody1 = new GreenfootImage("trainbody1.png");
        trainBody2 = new GreenfootImage("trainbody2.png");
        trainBody3 = new GreenfootImage("trainbody3.png");
        trainBody1.scale(trainBody1.getWidth()/5, trainBody1.getHeight()/5);
        trainBody2.scale(trainBody2.getWidth()/5, trainBody2.getHeight()/5);
        trainBody3.scale(trainBody3.getWidth()/5, trainBody3.getHeight()/5);
        randomizeImage();
        reachedBefore = false;
    }
    
    /**
     * Act - do whatever the TrainBody wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
        if(this != null && isMiddleTrain) {
            reachedMiddleOfWorld(reachedBefore);
        }
    }
    
    /**
     * Method to randonmize the image of the TrainBody to one of three different presets
     */
    public void randomizeImage() {
        int image = Greenfoot.getRandomNumber(3);
        if(image == 0) {
            setImage(trainBody1);
        }
        else if (image == 1) {
            setImage(trainBody2);
        }
        else {
            setImage(trainBody3);
        }
    }
    
    /**
     * Method to check whether the Train has reached the "middle" of the world
     */
    public boolean reachedMiddleOfWorld(boolean alreadyReachedBefore) {
        if(!alreadyReachedBefore) {
            if(getX() == getWorld().getWidth()/2-100) {
                stoppedAtMiddle = true;
                reachedBefore = true;
                return true;
            }
        }
        return false;
    }
    
}
