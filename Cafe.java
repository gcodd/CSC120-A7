/** A class representing a Cafe. Cafe class has an "is-a" relationship with Building class
 * @author Grace Codd
 * @version 11/01/2022
*/
public class Cafe extends Building{

    /** The amount of coffee in stock in ounces */
    private int nCoffeeOunces;
    /** The amount of sugar packets in stock */
    private int nSugarPackets;
    /** The amount of servings of cream in stock */
    private int nCreams;
    /** The amount of cups in stock */
    private int nCups;

    /**
     * Default constructor
     */
    public Cafe(){
        this("<Name Unkown>", "<Address Unknown>", 1);
    }

    /**
     * Overloaded constructor with name and address. Allows Cafe objects 
     * to be created without passing argument for nFloors
     * @param name the name of the cafe
     * @param address the address of the cafe
     */
    public Cafe(String name, String address){
        this(name, address, 1);
    }

    /** Constructor assigns set values to class fields and calls Building superclass
     * constructor to assign name, address, and nFloors fields to the Cafe object.
     * @param name the name of the cafe
     * @param address the address at which the cafe is located
     * @param nFloors the number of floors the cafe has
     */
    public Cafe(String name, String address, int nFloors){
        //Call superclass constructor passing name, address, and nFloors as args
        super(name, address, nFloors);
        //Assign set values to class fields
        nCoffeeOunces = 200;
        nSugarPackets = 300;
        nCreams = 100;
        nCups =  400;
        System.out.println("You have built a cafe: ☕");
    }

    /** Compares order to inventory and restocks inventory if necessary before selling product. 
     * Removes product sold from inventory. Decrements cups in inventory by one.
     * @param size ounces of coffee sold
     * @param nSugarpackets number of sugar packets in order
     * @param nCreams number of servings of cream in order
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams){
        /* If size, nSugarPackets, or nCreams are greater than what is stored in backstock, call restock method
        and pass 20 as arguments for each field*/
        if(size > nCoffeeOunces || nSugarPackets > this.nSugarPackets || nCreams > this.nCreams){
            restock(20, 20, 20, 20);
            }
        nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        nCups -= 1;
    }

    /** Increases the value stored in each class field to "restock" inventory
     * @param nCoffeeOunces number of ounces of coffee to add to inventory
     * @param nSugarPackets number of sugar packets to add to inventory
     * @param nCreams number of servings of cream to add to inventory
     * @param nCups number of cups to add to inventory
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }

    /** Displays the value of each element left in inventory */
    public void printInventory(){
        System.out.println("Coffee backstock = " + nCoffeeOunces + " ounces");
        System.out.println("Sugar backstock = " + nSugarPackets + " packets");
        System.out.println("Cream backstock = " + nCreams + " servings");
        System.out.println("Cups backstock = " + nCups + " cups");
    }

    /** 
    * Prints method options available to use when interacting with the library in the campus map.
    * Overrides showOptions() method from Building superclass. Calls superclass method to print all 
    * available options.
    */
    public void showOptions(){
        super.showOptions();
        System.out.print(" + sellCoffee(size, nSugarPackets, nCreams) \n ");
    }

    /**
    * Allows movement between floors. Since Cafe class does not have hasElevator attribute, throws error if trying to move between
    * nonconsecutive floors. 
    * @param floorNum the floor number to be moved to
    */
    public void goToFloor(int floorNum){
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
        if((this.activeFloor - floorNum) > 1 || (this.activeFloor - floorNum) < -1){
            throw new RuntimeException(this.name + " does not have an elevator. Cannot go up or down more than one floor at a time.");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }
    
    /** Main method for testing */
    public static void main(String[] args) {
        Cafe myCafe = new Cafe("Grace's Cafe", "228 Random Street", 3);
        myCafe.sellCoffee(16, 1, 1);

        myCafe.printInventory();

        myCafe.showOptions();

        myCafe.enter();
        myCafe.goToFloor(3);
    }
}
