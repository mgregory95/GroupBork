
package GroupBork;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
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
    static String HUNGER_LEADER = "Hunger:";
    static String TRIGGER_LEADER = "Trigger Number:";
    static String MOVEMENT_LEADER = "Movement Number:";
    

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;
    private int health = 100;
    private boolean win = false;
    private int score;
    private int numMoves;
    private int triggerNum;
    private int hunger = 100;
    private boolean earthquakeTriggered = false;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
        inventory = new ArrayList<Item>();
        createRanTriggerNum();
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
        if (s.hasNext()){
            String hunger = s.nextLine().substring(HUNGER_LEADER.length());
            this.setHunger(Integer.parseInt(hunger));
        }
        if (s.hasNext()){
            String triggerNum = s.nextLine().substring(TRIGGER_LEADER.length());
            this.setTriggerNum(Integer.parseInt(triggerNum));
        }
        if (s.hasNext()){
            String movementNum = s.nextLine().substring(MOVEMENT_LEADER.length());
            this.setMovementNumber(Integer.parseInt(movementNum));
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
        w.println(HUNGER_LEADER + this.hunger);
        w.println(TRIGGER_LEADER + this.triggerNum);
        w.println(MOVEMENT_LEADER + this.numMoves);
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
        new SaveCommand();
        this.adventurersCurrentRoom.clearExits();
        System.out.println("An earthquake rocks your world. You are now stuck in this room. Your future looks dim.");
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
    int getNumMoves(){
        return this.numMoves;
    }
    // this one is used to update the movement number
    void updateNumMoves(int moveNum){
        this.numMoves = this.numMoves + moveNum;
    }
    //this one is used in the restore state
    void setMovementNumber(int movementNum){
        this.numMoves = movementNum;
    }
    
    private void createRanTriggerNum(){
         Random rand = new Random();
         this.triggerNum = rand.nextInt(75) + 15;
  
    }
    void setTriggerNum(int triggerNum){
        this.triggerNum = triggerNum;
    }
    boolean getEarthquakeTriggers(){
        return this.earthquakeTriggered;
    }
    
 /**
 * 
 *  This method is a boolean that will be true if an earthquake has occurred and 
 *      false if no earthquake has been triggered. 
 * @return triggerEarthquake   This returns the boolean of whether or not an earthquake has
 *                      been triggered. It is false if no earthquake has occurred. 
 * 
 * @author Meredith Gregory
 */
    boolean triggerEarthquake(){
        boolean triggerEarthquake = this.getEarthquakeTriggers();
        if(triggerEarthquake == false){
            if(this.numMoves==this.triggerNum){
                return true;
            } 
            else return false;
        }
        else 
            return true;
    }
    
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
    void setScore(int newScore){
        this.score = newScore;
    }
    
    // used in restore 
    void setHunger(int hunger){
        this.hunger = hunger;
    }
    //used to update hunger with every move
    void updateHunger(int hunger){
        this.hunger = this.hunger + hunger;
    }
    int getHunger(){
        return this.hunger;
    }

}

