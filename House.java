import java.util.ArrayList;

/** A class representing a house
 * @author Grace Codd
 * @version 11/01/2022
 * References: 
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
*/
public class House extends Building {

  /** List of residents living in the house */
  private ArrayList<String> residents;
  /** True if house has a dining room, false if not */
  private boolean hasDiningRoom;
  /** True if house has an elevator, false if not */
  private boolean hasElevator;


  /**
   * Default constructor
   */
  public House(){
    this("<Name Unknown", "<Address Unknown>", 1, false, false);
  }

  /**
   * Overloaded constructor with name and address. Allows for House objects to be created without
   * passing args for nFloors, hasDiningRoom, and hasElevator
   * @param name the name of the house
   * @param address the address of the house
   */
  public House(String name, String address){
    //Call full constructor, hardcoding for nFloors, hasDiningRoom, and hasElevator
    this(name, address, 1, false, false);
  }

  /**
   * Constructor calls superclass constructor to assign name, string, and nFloors to House object.
   * Initializes hasDiningRoom class field and initializes an ArrayList stored in residents field
   * @param name the name of the house
   * @param address the address at which the house is located
   * @param nFloors the number of floors the house has
   * @param hasDiningRoom true or false if house has a dining room 
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
    super(name, address, nFloors);
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator = hasElevator;
    residents = new ArrayList<String>();
    System.out.println("You have built a house: 🏠");
  }

  /**
   * Setter for class field hasDiningRoom
   * @param hasDiningRoom true or false if house has dining room
   */
  public void setDiningRoom(boolean hasDiningRoom){
    this.hasDiningRoom = hasDiningRoom;
  }

  /**
   * Setter for class field hasElevator
   * @param hasElevator true or false if house has an elevator
   */
  public void setElevator(boolean hasElevator){
    this.hasElevator = hasElevator;
  }

  /** Accessor for class field hasDiningRoom
   * @return value stored in hasDiningRoom 
   */
  public boolean diningRoom(){
    return hasDiningRoom;
  }

  /** Accessor for number of residents in the house
   * @return number of elements stored in residents
   */
  public int nResidents(){
    return residents.size();
  }

  /** Takes in a String object representing a name and adds that name to the
   * list of residents in the house. 
   * @param name the name of the student moving in*/
  public void moveIn(String name){
    residents.add(name);
    System.out.println(name + " has moved in.");
  }

  public void moveIn(String name, int roomNum){
    residents.add(name);
  }

  /** Takes in String object representing a name and removes corresponding element from list of residents
   * if name can be found in residents
   * @param name the name of the student who is moving out
   * @return The name of the student moving out
   */
  public String moveOut(String name){
    /* Call isResident method. If returns false, print error message */
    if(!isResident(name)){
      System.out.println("Error! " + name + " is not a resident of " + super.getName());
    }
    /* If returns true, remove element from residents */
    else{
      residents.remove(name);
      System.out.println(name + " has moved out.");
    }
    return name;
  }

  /** Checks if a name matches an existing resident of a house
   * @param person the name of a person
   * @return true if name person is an element in residents, false if not
   */
  public boolean isResident(String person){
    // Call contains method, passing person as argument
    return residents.contains(person);
  }

  /** 
   * Prints method options available to use when interacting with a house in the campus map.
   * Overrides showOptions() method from Building superclass. Calls superclass method to print all 
   * available options.
  */
  public void showOptions(){
    super.showOptions();
    System.out.print(" + moveIn(name) \n + moveOut(name) \n");
 }

  /**
   * Allows movement between floors. Checks if house has an elevator before moving between non-consecutive floors. 
   * @param floorNum the floor number to be moved to
   */
  public void goToFloor(int floorNum){
    if (this.activeFloor == -1) {
      throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
      throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    if(!this.hasElevator){
      if((this.activeFloor - floorNum) > 1 || (this.activeFloor - floorNum) < -1){
        throw new RuntimeException(this.name + " does not have an elevator. Cannot go up or down more than one floor at a time.");
      }
    }
    System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    this.activeFloor = floorNum;
  }

  /** Main method for testing
   * @param args the command line arguments (ignored)
   */
  public static void main(String[] args) {

    //Instantiate House object
    House newHouse = new House("Grace's House", "102 Lake St", 
                              2, true, false);
    
    //Add residents to house by calling moveIn instance method                         
    newHouse.moveIn("Grace"); // Me
    newHouse.moveIn("Kira"); // My roommate
    newHouse.moveIn("Fiadh"); // My cat 

    //Print list of residents by calling toString method 
    System.out.println(newHouse.residents.toString());

    //Test moveOut method by passing a name that does NOT match a current resident
    newHouse.moveOut("Jordan");
    //Test moveOut method by passing a name that does match a current resident
    newHouse.moveOut("Grace");

    //Print list of residents to see if moveOut method worked
    System.out.println(newHouse.residents.toString());
    System.out.println("There are " + newHouse.nResidents() + " residents living at " + newHouse.getAddress());

    //Test inherited toString method 
    System.out.println(newHouse.toString());

    newHouse.showOptions();

    newHouse.enter();
    newHouse.goToFloor(2);
  }

}