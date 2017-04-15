


package GroupBork;

import GroupBork.Item.NoItemException;


/**
* The transform class is an event class. This class extends the abstract class Event. 
* This class produces a message and alters an item when called. 
* @author Ava 
*/
public class Transform extends Event {
    
    private String itemName; 
    
 /**
 * This is the Transform constructor. When an item is transformed a transform event is created. 
 * @param itemName  a String name of the item that the existing item will become 
 * @author Ava 
 */
    
    public Transform(String itemName){
        this.itemName = itemName;
    }
    
/**
* This is the execute method that the Transform class must have after extending Event - execute alters the
* state of an item
* @return a String saying the Item was transformed
* @author Ava 
     * @throws GroupBork.Item.NoItemException 
*/
    
    public String execute(String s) {
        try{
            GameState gs = GameState.instance();
            //activate new item
            Item newItem = gs.getDungeon().getItem(itemName);
            Room current = gs.getAdventurersCurrentRoom();
            current.add(newItem);
            //disappear the old item
            Disappear d = new Disappear();
            d.execute(s);
            
            
            
            
        }catch (Item.NoItemException e) {}
        return "" + s + " has been transformed into " + itemName;
    }
    
}
