import greenfoot.*;
import java.util.List;
/*
 * Reflection in P2_Khot_Tanvi_Bear class
 */
public class P2_Khot_Tanvi_Pig extends P2_Khot_Tanvi_Creature {
    
    
    /** override the dropItems() method so a Meat object is added to the world at
     * the same location as this pig
     */
    @Override
    public void dropItems(){
        P2_Khot_Tanvi_Meat m = new P2_Khot_Tanvi_Meat();
        this.getWorld().addObject(m, this.getX(), this.getY());
    }
    
    // YOUR CODE HERE
    
    /**
     * Override the turn() method so the pig will turn toward nearby berries (within 100 cells)
     * if hungry.
     * 
     */
    @Override
    public void turn(){
        double closestDistance = 0;
        P2_Khot_Tanvi_Berry closestBerry = null;
        if(isHungry()){
            List<P2_Khot_Tanvi_Berry> berries = this.getObjectsInRange(100, P2_Khot_Tanvi_Berry.class);
            if(berries.size() > 0){
                for(P2_Khot_Tanvi_Berry berry : berries){
                    double distance = distance(berry.getX(), berry.getY(), getX(), getY());
                    
                    if(closestBerry == null || distance < closestDistance){
                        
                        closestDistance = distance;
                        closestBerry = berry;
                    }
                    
                }
                if(closestBerry != null){
                    turnTowards(closestBerry.getX(), closestBerry.getY());
                }
            }
        }
    }
    
    public double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(y2-y1, 2) + Math.pow(x2-x1, 2)); 
    }
    
   
    // YOUR CODE HERE
    
    /**
     * Override the isHungry() method to make the pig always hungry.
     */
    @Override
    public boolean isHungry(){
        return true;
    }
    // YOUR CODE HERE
}
