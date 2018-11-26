import greenfoot.*;

/**
 * This is the top level class for creatures. All creatures have common
 * general behaviors such as eating and moving that can be customized
 * by overriding the methods call in the act method.
 * 
 * Generally, creatures gain hunger each frame and will die if they are
 * more hungry than MAX_HUNGER.
 */
public class P2_Khot_Tanvi_Creature extends Actor {
    private int speed; // how much the creature moves each frame
    private int hunger; // how hungry the creature is
    private final GreenfootImage origImg; // the original image of the creature
    private final int MAX_HUNGER = 1000; // creatures generally die if more hungry than this
    private final int HUNGRY = 500; // This is the default level of hunger that determines a creature is hungry
    
    /**
     * Constructs a Creature with 4 speed and 0 hunger.
     */
    public P2_Khot_Tanvi_Creature () {
        speed = 4;
        hunger = 0;
        origImg = getImage();
    }
    
    /**
     * Constructs a Creature with the given speed and 0 hunger.
     * @param speed the speed of the creature
     */
    public P2_Khot_Tanvi_Creature (int speed) {
        this.speed = speed;
        hunger = 0;
        origImg = getImage();
    }
    
    /**
     * This performs all the actions that this creature will perform each frame.
     * In general, if the creature is hungry and can eat, it will prep food and then eat;
     * otherwise, it will turn and move (looping to opposite side if encountering an edge).
     * It's hunger will increase and the hunger bar will update to reflect that increase.
     * All of these behaviours can be overridden in subclasses to produce different behaviors.
     */
    public void act() {
        if (isHungry() && canEat()) {
            prepFood();
            eat();
        } else {
            turn();
            move();
            edgeLoop();
        }
        increaseHunger();
        updateHungerBar();
    }
    
    /**
     * Does whatever is needed to prep food (i.e. kill prey).
     * By default this does nothing.
     */
    public void prepFood() {
        // subclasses may override this
    }
    
    /**
     * moves the creature by its speed in the direction it is facing
     */
    public void move() {
        move(speed);
    }
    
    /**
     * Turns the creature according to its motivations.
     * By default this turns the creature a little bit in a random direction (+/- 3).
     */
    public void turn() {
        turn(Greenfoot.getRandomNumber(7) - 3);
    }
    
    /**
     * If the creature passes an edge of the world, it appears at the opposite edge.
     */
    public void edgeLoop() {
        int ww = getWorld().getWidth();
        int wh = getWorld().getHeight();
        int x = getX();
        int y = getY();
        if (x < 0) {
            x = ww;
        } else if (x > ww) {
            x = 0;
        }
        
        if (y < 0) {
            y = wh;
        } else if (y > wh) {
            y = 0;
        }
        setLocation(x, y);
    }
    
    /**
     * Returns true if the creature can eat and false otherwise.
     * By default this is true only if the creature is touching food.
     */
    public boolean canEat() {
        return intersectingFood() != null;
    }
    
    /**
     * Returns an intersecting food object or null if there is no food touching this creature.
     */
    public P2_Khot_Tanvi_Food intersectingFood() {
        return (P2_Khot_Tanvi_Food)getOneIntersectingObject(P2_Khot_Tanvi_Food.class);
    }
    
    /**
     * Gets an intersecting food and eats it, if there is one.
     */
    public void eat() {
        P2_Khot_Tanvi_Food food = intersectingFood();
        if (food != null) {
            hunger -= food.getFoodValue();
            getWorld().removeObject(food);
        }
    }
    
    /**
     * Returns true if the creature is hungry and false otherwise.
     * By default a creature is hungry if their hunger is at least the HUNGRY constant.
     */
    public boolean isHungry() {
        return hunger >= HUNGRY;
    }
    
    /**
     * Increases the hunger of the creature and kills it if hunger is more than
     * the MAX_HUNGER constant.
     */
    public void increaseHunger() {
        hunger++;
        if (hunger > MAX_HUNGER) {
            die();
        }
    }
    
    /**
     * Kills the creature, removing it from the world and dropping items.
     */
    public void die() {
        dropItems();
        getWorld().removeObject(this);
    }
    
    /**
     * Drops any items that should drop when a creature dies.
     * By default this does nothing, but most creatures will override this
     * to drop a corpse or food of some sort.
     */
    public void dropItems() {
        // do nothing
    }
    
    /**
     * Updates the hunger bar to reflect the current value of hunger
     * in relation to MAX_HUNGER.
     */
    public void updateHungerBar() {
        GreenfootImage img = new GreenfootImage(origImg);
        img.setColor(new Color(255, 0, 0, 100));
        img.fillRect(0, 0, hunger * img.getWidth() / MAX_HUNGER, img.getHeight());
        setImage(img);
    }
}
