package Assignment3;

import java.util.Random;

/**
 * Created by Kristofer Svensson on 2016-11-30.
 */

/**
 * The class represents a grocery product. The class itself holds an array of items.
 */
public class FoodItem {

    private double weight, volume;
    private String name;
    private static FoodItem[] foodBuffer;
    private Random rand;

    /**
     * Each item has a number of properties.
     * @param weight The weight of the item.
     * @param volume The volume of the item.
     * @param name The name of the item.
     */
    public FoodItem(double weight, double volume, String name){
        this.weight=weight;
        this.volume=volume;
        this.name=name;
        rand = new Random();
    }

    public FoodItem(){
        initFoodBuffer();
        rand = new Random();
    }

    /**
     * Used for fetching a random item from the array of items.
     * @return A random FoodItem from the array.
     */
    public FoodItem getRandomItem(){
        return foodBuffer[rand.nextInt(20)];
    }

    public String getName(){
        return name;
    }

    public double getWeight(){
        return weight;
    }

    public double getVolume(){
        return volume;
    }

    /**
     * Initializes and fills the array with a number of example items.
     */
    public void initFoodBuffer(){
        foodBuffer = new FoodItem[20];
        foodBuffer[0] = new FoodItem(1.1, 0.5, "Milk");
        foodBuffer[1] = new FoodItem(0.6, 0.1, "Cream");
        foodBuffer[2] = new FoodItem(1.1, 0.5, "Yoghurt");
        foodBuffer[3] = new FoodItem(2.24, 0.55, "Butter");
        foodBuffer[4] = new FoodItem(2.4, 1.2, "Flower");
        foodBuffer[5] = new FoodItem(2.7, 1.8, "Sugar");
        foodBuffer[6] = new FoodItem(1.55, 0.27, "Salt");
        foodBuffer[7] = new FoodItem(0.6, 0.19, "Almonds");
        foodBuffer[8] = new FoodItem(1.98, 0.75, "Bread");
        foodBuffer[9] = new FoodItem(1.4, 0.5, "Donuts");
        foodBuffer[10] = new FoodItem(1.3, 1.5, "Jam");
        foodBuffer[11] = new FoodItem(4.1, 2.5, "Ham");
        foodBuffer[12] = new FoodItem(6.8, 3.9, "Chicken");
        foodBuffer[13] = new FoodItem(0.87, 0.55, "Sallad");
        foodBuffer[14] = new FoodItem(2.46, 0.29, "Orange");
        foodBuffer[15] = new FoodItem(2.44, 0.4, "Apple");
        foodBuffer[16] = new FoodItem(1.3, 0.77, "Pear");
        foodBuffer[17] = new FoodItem(2.98, 2.0, "Soda");
        foodBuffer[18] = new FoodItem(3.74, 1.5, "Beer");
        foodBuffer[19] = new FoodItem(2.0, 1.38, "Hotdogs");
    }
}
