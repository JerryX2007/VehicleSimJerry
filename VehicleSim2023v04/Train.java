import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The Train subclass
 */
public abstract class Train extends SuperSmoothMover
{
    protected int speed;
    protected boolean moving;
    protected int actCount;
    protected static GreenfootSound trainWhistle;
    protected static GreenfootSound[] trainPassing;
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
        if (checkEdge()){ //Remove the object if it reaches the end of the world
            getWorld().removeObject(this);
            return;
        }
        
        if(!checkHitPedestrian()) {
            repelPedestrians();
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
            if(actCount >= 600) { //If the act count finishes, start to move the trains again
                for(Train train: trains) {
                    if(train != null) {
                        train.startMoving();
                        trainWhistle.play();
                        playTrainSound();
                    }
                }
                actCount = 0;
                stoppedAtMiddle = false;
            }
        }
        move(speed);
    }
    
    //Code from Vehicle class
    protected boolean checkEdge() {
        if (getX() > getWorld().getWidth() + 200){
            return true;
        }
        return false;
    }
    
    /*
     * If the Train hits an Adult, it should knock over the Adult
     * If the Train hits a Baby, it should caue an explosion.
     * Since the train is very strong, it will not be affected by the explosion
     */
    public boolean checkHitPedestrian () {
        Pedestrian pedestrianInFront = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, 0, Pedestrian.class);
        Pedestrian pedestrianOnLeft = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, getImage().getHeight()/2-6, Pedestrian.class);
        Pedestrian pedestrianOnRight = (Pedestrian)getOneObjectAtOffset((int)Math.ceil(speed) + getImage().getWidth()/2 + 1, -1*getImage().getHeight()/2+6, Pedestrian.class);
        
        // Check if the pedestrian is an instance of an Adult or a Baby. If it is an Adult, run the Adult over. If it is a Baby, explode.
        if(pedestrianInFront != null) {
            if(pedestrianInFront instanceof Adult && pedestrianInFront.isAwake()) {
                pedestrianInFront.knockDown();
                return true;
            }
            else if (pedestrianInFront instanceof Baby) {
                getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                pedestrianInFront.dies();
                return true;
            }
        }
        if(pedestrianOnLeft != null) {
            if(pedestrianOnLeft instanceof Adult && pedestrianOnLeft.isAwake()) {
                pedestrianOnLeft.knockDown();
                return true;
            }
            else if (pedestrianOnLeft instanceof Baby) {
                getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                pedestrianOnLeft.dies();
                return true;
            }
        }
        if(pedestrianOnRight != null) {
            if(pedestrianOnRight instanceof Adult && pedestrianOnRight.isAwake()) {
                pedestrianOnRight.knockDown();
                return true;
            }
            else if (pedestrianOnRight instanceof Baby) {
                getWorld().addObject(new Explosion(70, 332/2, 347/2), getX() + getImage().getWidth()/2, getY() - 60);
                pedestrianOnRight.dies();
                return true;
            }
        }
        return false;
    }
    
    public void repelPedestrians() {
        ArrayList<Pedestrian> pedsTouching = (ArrayList<Pedestrian>)getIntersectingObjects(Pedestrian.class);
        
        ArrayList<Actor> actorsTouching = new ArrayList<Actor>();
        
        // this works, but doesn't ignore knocked down Pedestrians
        //actorsTouching.addAll(pedsTouching);
        for (Pedestrian p : pedsTouching){
            if (p.isAwake()){
                actorsTouching.add(p);
            }
        }
        
        pushAwayFromObjects(actorsTouching, 4);
    }

    /**
     * New repel method! Seems to work well. Can be used in both directions, but for now
     * commented out movement on x so players are only "repelled" in a y-direction.
     * 
     * @author Mr Cohen
     * @since February 2023
     */
    public void pushAwayFromObjects(ArrayList<Actor> nearbyObjects, double minDistance) {
        // Get the current position of this actor
        int currentX = getX();
        int currentY = getY();

        // Iterate through the nearby objects
        for (Actor object : nearbyObjects) {
            // Get the position and bounding box of the nearby object
            int objectX = object.getX();
            int objectY = object.getY();
            int objectWidth = object.getImage().getWidth();
            int objectHeight = object.getImage().getHeight();
    
            // Calculate the distance between this actor and the nearby object's bounding oval
            double distance = Math.sqrt(Math.pow(currentX - objectX, 2) + Math.pow(currentY - objectY, 2));
    
            // Calculate the effective radii of the bounding ovals
            double thisRadius = Math.max(getImage().getWidth() / 2.0, getImage().getHeight() / 2.0);
            double objectRadius = Math.max(objectWidth / 2.0, objectHeight / 2.0);
    
            // Check if the distance is less than the sum of the radii
            if (distance < (thisRadius + objectRadius + minDistance)) {
                // Calculate the direction vector from this actor to the nearby object
                int deltaX = objectX - currentX;
                int deltaY = objectY - currentY;
    
                // Calculate the unit vector in the direction of the nearby object
                double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                double unitX = deltaX / length;
                double unitY = deltaY / length;
    
                // Calculate the amount by which to push the nearby object
                double pushAmount = (thisRadius + objectRadius + minDistance) - distance;
    
                // Update the position of the nearby object to push it away
                
                object.setLocation(objectX, objectY + (int)(pushAmount * unitY));
                
                // 2d version, allows pushing on x and y axis, commented out for now but it works, just not the
                // effect I'm after:
                //object.setLocation(objectX + (int)(pushAmount * unitX), objectY + (int)(pushAmount * unitY));
            }
        }
    }
    
    /*
     * Method to initialize the sounds for this class
     */
    public static void init() {
        trainPassing = new GreenfootSound[2];
        for(int i=0;i<trainPassing.length;i++) {
            trainPassing[i] = new GreenfootSound ("trainPassing.mp3");
            trainPassing[i].setVolume(70);
        }
        trainIndex = 0;
        
        trainWhistle = new GreenfootSound("trainWhistle.mp3");
        trainWhistle.setVolume(60);
    }
    
    //Code from Mr. Cohen
    public static void trainApproaching() {
        trainWhistle.play();
        playTrainSound();
    }
    
    public static void playTrainSound() {
        trainPassing[trainIndex].play();
        trainIndex++;
        if (trainIndex >= trainPassing.length){
            trainIndex = 0;
        }
    }
    
    /*
     * Setter method to set the speed of the Train to 0 and the moving state to false
     */
    public void stopMoving() {
        speed = 0;
        moving = false;
    }
    
    /*
     * Setter method to set the speed of the Train to 2 and the moving state to true
     */
    public void startMoving() {
        speed = 2;
        moving = true;
    }
    
}
