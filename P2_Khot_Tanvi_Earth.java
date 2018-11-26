import greenfoot.*;

/**
 * The world where creatures live.
 * This world starts with a bear in the middle of the world.
 * Every frame, it has a chance to add another creature or food.
 */
public class P2_Khot_Tanvi_Earth extends World {
    public P2_Khot_Tanvi_Earth()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        addObject(new P2_Khot_Tanvi_Bear(), getWidth() / 2, getHeight() / 2);
    }
    
    /**
     * This is called every frame. It has a chance to randomly spawns a pig, bear, or food.
     */
    public void act() {
        if (Greenfoot.getRandomNumber(1000) <= 7) {
            addObject(new P2_Khot_Tanvi_Pig(), getWidth() / 2, getHeight() / 2);
            
        } else if (Greenfoot.getRandomNumber(1000) <= 1) {
            addObject(new P2_Khot_Tanvi_Bear(), getWidth() / 2, getHeight() / 2);
        } else if (Greenfoot.getRandomNumber(1000) <= 4) {
            addObject(new P2_Khot_Tanvi_Berry(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        } 
    }
}
