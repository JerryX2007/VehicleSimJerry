import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Felix Zhao helped me with this
 */
public abstract class Gif extends Actor
{
    protected int time;
    protected int timeCounter = 0;
    protected int width;
    protected int height;
    protected GifImage image;
    
    public abstract void act();
}
