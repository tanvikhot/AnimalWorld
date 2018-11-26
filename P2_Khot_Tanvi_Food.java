import greenfoot.*;

/**
 * Subclasses of this class are considered food by and can be eaten by default.
 */
public class P2_Khot_Tanvi_Food extends P2_Khot_Tanvi_FadingActor {
    private int foodValue;
    
    public P2_Khot_Tanvi_Food() {
        foodValue = 100;
    }
    
    public P2_Khot_Tanvi_Food(int foodValue) {
        this.foodValue = foodValue;
    }
    
    public int getFoodValue() {
        return foodValue;
    }
    
    public void setFoodValue(int value) {
        foodValue = value;
    }
}
