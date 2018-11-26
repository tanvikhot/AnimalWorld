import greenfoot.*;

/**
 * This class has an act method that causes the actor to become more transparent every frame,
 * until it finally is removed when it is fully transparent.
 */
public class P2_Khot_Tanvi_FadingActor extends Actor {
    public void act() {
        getImage().setTransparency(getImage().getTransparency() - 1);
        if (getImage().getTransparency() == 0) {
            getWorld().removeObject(this);
        }
    } 
}
