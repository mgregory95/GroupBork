
package GroupBork;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 * GameState both creates the game state and holds methods related to the player's
 * game - this includes the current room, their inventory, and whether an earthquake
 * has created havoc throughout the game. 
 */
public class GameState {

    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "zork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork G3a save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";
    static String SCORE_LEADER = "Score:";
    static String HEALTH_LEADER = "Health:";

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;
    private int health = 100;
    private boolean win = false;
    private int score;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
        inventory = new ArrayList<Item>();
    }

    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '" +
                        itemName + "'");
                }
            }
        }
        if (s.hasNext()){
            String score = s.nextLine().substring(SCORE_LEADER.length());
            this.setScore(Integer.parseInt(score));
        }
        if (s.hasNext()){
            String health = s.nextLine().substring(HEALTH_LEADER.length());
            this.setHealth(Integer.parseInt(health));
        }
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<inventory.size()-1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size()-1).getPrimaryName());  
        }
        w.println(SCORE_LEADER + this.score);
        w.println(HEALTH_LEADER + this.health);
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }

    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }

    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    Dungeon getDungeon() {
        return dungeon;
    }
    
 /**
 * 
 * This method is called to initiate the effects of the earthquake to the game state -
 * There are no parameters and returns void - The method is called when the trigger
 * number equals the movement number. 
 * @author Meredith Gregory
 * 
 */
    void earthquake(){
        
    }
    
 /**
 * 
 * This method is a getter for the numMoves instance variable - It will be used 
 * to see if the random trigger number and the movement number are equal which 
 * sets off an earthquake when they are. 
 * @author Meredith Gregory
 * @return getNumMoves This returns the instance variable numMoves 
 * 
 */
    //int getNumMoves(){
        
    //}
    
  /**
 *
 * This method is a getter for the triggerNumber instance variable - It will be used 
 * to see if the random trigger number and the movement number are equal which 
 *  sets off an earthquake when they are. 
 * @author Meredith Gregory
 * @return getTriggerNumber  This returns the instance variable triggerNumber
 * 
 */
    //int getTriggerNumber(){
        
    //}
    
 /**
 * 
 *  This method is a boolean that will be true if an earthquake has occurred and 
 *      false if no earthquake has been triggered. 
 * @return triggerEarthquake   This returns the boolean of whether or not an earthquake has
 *                      been triggered. It is false if no earthquake has occurred. 
 * @param triggerNumber      The integer number triggerNumber is the only parameter. 
 *                      It will be equal to the instance variable also named triggerNumber.
 * @author Meredith Gregory
 */
   // boolean triggerEarthquake(int triggerNumber){
        
    //}
    
    int getHealth(){
        return this.health;
    }
    void setHealth(int health){
        this.health = health;
    }
    
    void setWinStatus(boolean b){
        this.win = b;
    }
    boolean getWinStatus(){
        return this.win;
    }
    int getScore(){
        return this.score;
    }
    void setScore(int scoreChange){
        this.score = this.score + scoreChange;
    }

}










