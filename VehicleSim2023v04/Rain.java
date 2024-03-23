import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Snowstorm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rain extends Effect
{
    private int actsLeft, turnAroundAct;
    private int speed;
    private int count;
    private int direction;
    private boolean turned;
    private boolean stop;
    private GreenfootSound windSound;

    public Rain () {
        actsLeft = 300;
        speed = 3;
    }

    public void addedToWorld (World w){
        if (image == null) {
            image = drawRain(getWorld().getWidth(), getWorld().getHeight()*10, 55);
        }
        setImage(image);
    }

    /**
     * Act - do whatever the Snowstorm wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (this != null && getWorld() != null) { // Check if the object and its world are still valid
            if(actsLeft <= 60) {
                fade(actsLeft, 60);
            }
            actsLeft--;
            setLocation(getX(), getY()+speed);
            if(actsLeft <= 400) {
                rainPedsEffect();
            }
            if (actsLeft == 0){
                getWorld().removeObject(this);
                return;
            }
            
            // From Mr. Cohen's demo code
            // slows down anything that touches it
            ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getIntersectingObjects(Vehicle.class);
            for (Vehicle v : vehicles){
                v.setSpeed(0.55);
            }
            ArrayList<Pedestrian> peds = (ArrayList<Pedestrian>) getIntersectingObjects(Pedestrian.class);
            for (Pedestrian p : peds){
                p.setSpeed(0.55);
            }
        }
    }

    public void rainPedsEffect() {
        ArrayList<Pedestrian> peds = (ArrayList<Pedestrian>) getWorld().getObjects(Pedestrian.class);
        for (Pedestrian p : peds) {
            if (!p.isAwake()) {
                if (!stop) {
                    p.setLocation(p.getX(), p.getY() + 2);
                    count = 0;
                }
                if (stop) {
                    if (count < 10) {
                        count++;
                    }

                    if (count >= 1) {
                        stop = false;
                    }
                }   
            }
        }
    }
    
    public static GreenfootImage drawRain (int width, int height, int density){

        Color[] swatch = new Color [32];
        Color[] lighterSwatch = new Color [32];

        int green = 140;

        // Build a color pallete out of shades of near-white yellow and near-white blue      
        for (int i = 0; i < swatch.length; i++){ // first half blue tones
            swatch[i] = new Color (12, green, 199);
            if (i%2 == 0) {
                green += 2;
            }
            else {
                green -= 2;
            }
            lighterSwatch[i] = new Color(42, green+30, 229);
        }

        // The temporary image, my canvas for drawing
        GreenfootImage temp = new GreenfootImage (width, height);

        // Run this loop one time per "density"
        for (int i = 0; i < density; i++){
            //swatch
            for (int j = 0; j < 100; j++){ // draw 100 circles
                int randSize;
                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(swatch.length);
                //int randTrans = Greenfoot.getRandomNumber(220) + 35; // around half transparent
                temp.setColor (swatch[randColor]);

                //setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);

                int tempVal = Greenfoot.getRandomNumber(250);
                int randHeight = Greenfoot.getRandomNumber(10)+5;
                temp.drawRect (randX, randY, 1, randHeight);
                temp.drawRect (randX, randY+4, 1, randHeight-3);
            }
            //lighterSwatch
            for (int j = 0; j < 100; j++){ // draw 100 circles
                int randSize;
                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(lighterSwatch.length);
                //int randTrans = Greenfoot.getRandomNumber(220) + 35; // around half transparent
                temp.setColor (lighterSwatch[randColor]);

                //setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);

                int tempVal = Greenfoot.getRandomNumber(250);
                int randHeight = Greenfoot.getRandomNumber(20)+5;
                temp.drawRect (randX, randY, 1, randHeight);
                temp.drawRect (randX, randY+4, 1, randHeight-3);
            }
        }

        return temp;
    }

}
