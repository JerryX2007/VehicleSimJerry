import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    protected static GreenfootSound[] trainPassing;
    protected static int trainIndex;
    public Train() {
        moving = true;
        speed = 2;
        actCount = 1;
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
        move(speed);
        actCount++;
    }
    
    protected boolean checkEdge() {
        if (getX() > getWorld().getWidth() + 200){
            return true;
        }
        return false;
    }
    
    public static void init() {
        trainIndex = 0;
        trainPassing = new GreenfootSound[2];
        for (int i=0;i<trainPassing.length;i++) {
            trainPassing[i] = new GreenfootSound("trainPassing.mp3");
            trainPassing[i].setVolume(30);
        }
        
        trainWhistle = new GreenfootSound("trainWhistle.mp3");
        trainWhistle.setVolume(70);
        
        trainIndex = 0;
    }
    
    //Code from Mr. Cohen
    public void playTrainSound() {
        trainPassing[trainIndex].play();
        trainIndex++;
        if (trainIndex >= trainPassing.length){
            trainIndex = 0;
        }
    }
    
    public static void trainApproaching() {
        trainWhistle.play();
        fadeSound(trainPassing[0], 0, 75, true);
    }
    
    public static void fadeSound(GreenfootSound sound, int start, int degree, boolean louder) {
        sound.play();
        if (louder) {
            for(int i = start; i<=degree; i++) {
                sound.setVolume(i);
            }
        }
        else {
            for (int i= start; i>0;i--) {
                sound.setVolume(i);
            }
        }
    }
}
