


package GroupBork;

/**
 * Once a certain item has been used to its fullest potential, it can
 * disappear from the game entirely. A donut may be eaten or a can of Dr.
 * Pepper may be drank and then crushed and recycled and is then no longer
 * able to be seen ever again by the adventurer.
 * 
 * @author Ava
 */
class Disappear extends Event {

    /**
     * The constructor for the disappear class takes in an item that is
     * no longer going to be available to the adventurer.
     * 
    */ 
    
    public Disappear(){
        
    }
    
    /**
     * The execute method simply deletes the item from the dungeon inventory
     * or from the adventurers inventory. This removes it entirely from the
     * game. It is based on the item itself and what it takes for it to
     * completely disappear.
     * 
     * @return String that the item disappeared
    */
    
    public String execute(String itemName){
        //remove item from inventory if it is there
        if(GameState.instance().getInventoryNames().contains(itemName)){
            try{
                Item i = GameState.instance().getDungeon().getRoom(itemName).getItemNamed(itemName);
                GameState.instance().removeFromInventory(i);
            }
            catch (Item.NoItemException e) {
            System.out.println("Sorry! That Item doesnt exist!");}
            }
        
        //remove item from the dungeon inventory (hashtable)
        if(GameState.instance().getDungeon().getAllItems().containsKey(itemName)){
            GameState.instance().getDungeon().getAllItems().remove(itemName);
        }
        //remove item from room inventory 
        try{
            Room r = GameState.instance().getAdventurersCurrentRoom();
            Item i = r.getItemNamed(itemName);
            if(r.getContents().contains(i)){
                r.getContents().remove(i);
            }
        }catch (Item.NoItemException e) {
            System.out.println("Sorry! That Item doesnt exist!");
        }
             
        return "";
    }
    
}
