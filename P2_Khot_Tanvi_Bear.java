import greenfoot.*;
import java.util.List;
/*
 * Written by Tanvi Khot P2
 * 
 * This lab was challenging at first to write because
 * I hadn't worked with Greenfoot in a couple months,
 * so I spent the first 30 minutes finding my way around
 * the program. After I had figured out what I was doing, 
 * the rest of the coding was not difficult.
 */
public class P2_Khot_Tanvi_Bear extends P2_Khot_Tanvi_Creature {
    /**
     * Make a constructor that initializes speed to 5. To do this you will need
     * to call the correct super constructor.
     */
    public P2_Khot_Tanvi_Bear(){
        super(5);
    }
    // YOUR CODE HERE

    /** override the prepFood() method so it gets an intersecting pig and kills it if there is one
     */
    @Override
    public void prepFood(){
        P2_Khot_Tanvi_Pig p = (P2_Khot_Tanvi_Pig)getOneIntersectingObject(P2_Khot_Tanvi_Pig.class);
        if(p!= null){
            //getWorld().removeObject(p);
            p.die();
        }
    }

    // YOUR CODE HERE
    /** Override canEat() so it will return true if the super class method
     * would have returned true, but also returns true if touching a pig.
     * Otherwise it should return false.
     * */

    //YOUR CODE HERE
    @Override
    public boolean canEat(){
        return isTouching(P2_Khot_Tanvi_Pig.class) || super.canEat();
    }

    /**
     * Override the turn() method so that it will turn toward nearby pigs or food
     * if the bear is hungry. Lets say nearby means within 100 cells.
     * If there are no pigs or food in range, this method should do the same as the
     * super class method.
     */

    // YOUR CODE HERE
    @Override
    public void turn(){
        double closestDistance = 0;
        P2_Khot_Tanvi_Food closestFood = null;
        P2_Khot_Tanvi_Pig closestPig = null;
        if(isHungry()){
            List<P2_Khot_Tanvi_Food> food = this.getObjectsInRange(100, P2_Khot_Tanvi_Food.class);
            if(food.size() > 0){
                for(P2_Khot_Tanvi_Food foods : food){
                    double distance = distance(foods.getX(), foods.getY(), getX(), getY());

                    if(closestFood == null || distance < closestDistance){

                        closestDistance = distance;
                        closestFood = foods;
                    }

                }
                // if(closestFood != null){
                // turnTowards(closestFood.getX(), closestFood.getY());
                // }
            }

            List<P2_Khot_Tanvi_Pig> pigs = this.getObjectsInRange(100, P2_Khot_Tanvi_Pig.class);
            if(pigs.size() > 0){
                for(P2_Khot_Tanvi_Pig p : pigs){
                    double distance = distance(p.getX(), p.getY(), getX(), getY());

                    if(closestFood == null || distance < closestDistance){

                        closestDistance = distance;
                        closestPig = p;
                    }

                }
                // if(closestPig != null){
                // turnTowards(closestPig.getX(), closestPig.getY());
                // }
            }
            if(closestPig != null){
                turnTowards(closestPig.getX(), closestPig.getY());
            }else if(closestFood != null){
                turnTowards(closestFood.getX(), closestFood.getY());
            }else{
                super.turn();
            }
        }
    }

    public double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(y2-y1, 2) + Math.pow(x2-x1, 2)); 
    }
    /** override the dropItems() method so a DeadBear object is added to the world at
     * the same location as this bear
     */

    // YOUR CODE HERE
    
     @Override
    public void dropItems(){
        P2_Khot_Tanvi_DeadBear db = new P2_Khot_Tanvi_DeadBear();
        this.getWorld().addObject(db, this.getX(), this.getY());
    }
}
