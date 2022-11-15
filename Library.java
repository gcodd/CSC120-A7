import java.util.Hashtable;

/** A class representing a Library 
 * 
 * References:
 * https://www.javacodeexamples.com/print-hashtable-in-java-example/3154
 * @author Grace Codd
 * @version 11/01/2022
*/
public class Library extends Building{

  /** Collection of books in the library stored as a Hashtable with key being book title 
   * and value being true or false */
  private Hashtable<String, Boolean> collection;
  /** True if library has an elevator, false if not */
  private boolean hasElevator;

  /**
   * Default constructor
   */
  public Library(){
    this("<Name Unknown", "<Address Unknown>", 1, false);
  }

  /** 
   * Overloaded constructor with name and address. Allows for Library objects to be created without 
   * passing args into nFloors and hasElevator.
   * @param name the name of the house
   * @param address the address of the house
   */
  public Library(String name, String address){
    //Call full constructor, hardcode for nFLoors and hasElevator
    this(name, address, 1, false);
  }
  
  /** Constructor calls superclass constructor to assign name, string, and nFloors to
   * Library object. Initializes a Hashtable and stores in class field collection
   * @param name the name of the library
   * @param address the address at which the library is located
   * @param floors the number of floors in the library
   */
  public Library(String name, String address, int nFloors, boolean hasElevator) {
    super(name, address, nFloors);
    collection = new Hashtable<>();
    this.hasElevator = hasElevator;
    System.out.println("You have built a library: 📖");
  }

  /**
   * Setter for class field hasElevator
   * @param hasElevator true or false if house has an elevator
   */
  public void setElevator(boolean hasElevator){
    this.hasElevator = hasElevator;
  }

  /** Adds a book to library's collection by calling put method from Hashtable class. Sets 
   * value as true so book is available once added to collection.
   * @param title the title of the book added to colelction
   */
  public void addTitle(String title){
    //Call put method from Hashtable class, passing title and "true" value as args
    collection.put(title, true);
  }

  /** Checks if book is in library's collection and if so, removes book from library's collection
   * @param title the title of book to be removed from collection
   * @return The title of the book removed 
   */
  public String removeTitle(String title){
    /* Call containsTitle method, passing title as arg. If containsTitle returns true,
    * call remove instance method, passing title as arg to remove title from collection */
    if(containsTitle(title)){
      collection.remove(title);
    }
    // If title is not found in collection, print error message
    else
      System.out.println("Error! " + title + " does not exist in the collection");
    return title;
  }

  /** Checks if book is available and replaces value stored at title key with false if available. 
   * @param title the name of the book to be checked out
  */
  public void checkOut(String title){
    /*Call isAvailable method, passing title as arg. If isAvailable returns true,
    * call replace instance method to change value stored at title key to "false"*/
    if(isAvailable(title)){
      collection.replace(title, false);
    }
    /* If title is not available, print error message */
    else
      System.out.println("Error! " + title + " is not available." );
  }

  /** Checks if book is checked out and changes value stored at title key to "true" to return book
   * @param title the name of the book to be returned
   */
  public void returnBook(String title){
    /* Call isAvaibalbe method, passing title as arg. If isAvailable returns false,
     * call replace instance method to change value stored at title key to "true"
     */
    if(!isAvailable(title)){
      collection.replace(title, true);
    }
    /* If title has not already been checked out, print error message */
    else
      System.out.println("Error! " + title + " cannot be returned.");
  }

  /** Checks to see if a title is part of the library's collection
   * @param title the name of the book to search collection for
   * @return True if title is found in collection, false if not
  */
  public boolean containsTitle(String title){
    /* Call containsKey instance method, passing title as arg. If containsKey returns true,
     * this means this title can be found in the collection.
     */
    if(collection.containsKey(title)){
      return true;
    }
    else
      return false;
  }

  /** Checks if a title is available to be checked out from the library
   * @param title the name of the book to check availability
   * @return True if title is available for checkout, false if not.
   */
  public boolean isAvailable(String title){
    /* Call get instance method, passing title as arg. get method will return the value (true or false)
     * stored at the title key. */
    if(collection.get(title) == true){
      return true;
    }
    else
      return false;
  }

  /** Prints every title in colleciton and whether or not the title is available */
  public void printCollection(){

    System.out.println("Title \t\t\t" + "Status");
    System.out.println("-------------------------------------");

    /* Call entrySet method to return a Set of elements in collection. Use forEach method to 
     * print out title and availability status for each element.
     * Reference for this function: https://www.javacodeexamples.com/print-hashtable-in-java-example/3154
     */
    collection.entrySet().forEach( entry -> 
                                    {System.out.print(entry.getKey() + "\t\t\t"); //Print title
                                      //If value is true, print "Available"
                                      if(entry.getValue() == true){
                                        System.out.println("Available");
                                      }
                                      //If value is not true, print "Not avalibale"
                                      else
                                        System.out.println("Not Available");
                                    });
  }

  /** 
   * Prints method options available to use when interacting with the library in the campus map.
   * Overrides showOptions() method from Building superclass. Calls superclass method to print all 
   * available options.
  */
  public void showOptions(){
    super.showOptions();
    System.out.print(" + checkOut(title) \n + returnBook(title) \n + isAvailable (title) \n + printCollection() \n");
  }

  /**
   * Allows movement between floors. Checks if library has an elevator before moving between non-consecutive floors. 
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

    //Create instance of Library class
    Library myLibrary = new Library("Neilson", "7 Neilson Drive", 4, true);

    //Add titles to collection by calling addTitle method
    myLibrary.addTitle("Twilight");
    myLibrary.addTitle("Macbeth");
    myLibrary.addTitle("Little Women");

    myLibrary.printCollection();

    //Check out a book by calling checkOut method
    myLibrary.checkOut("Little Women");

    //Print collection
    myLibrary.printCollection();

    //Try to check out Little Women again
    myLibrary.checkOut("Little Women");

    myLibrary.showOptions();
    
  }
  
}