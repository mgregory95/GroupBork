

package GroupBork;

/**
* The teleport class is an event class. This class extends the abstract class Event. 
* This class moves the adventurer "magically" to a new location.
* @author Ava 
*/
public class Teleport extends Event {
    
    
 /**
 * This is the Teleport constructor. When the adventurer is teleported a teleport event is created. 
 * 
 * @author Ava 
 */

    public Teleport(){
        
    }
/**
* This is the execute method that the Teleport class must have after extending Event - execute alters the
* current room of the adventurer
* @return String saying that the user has teleported 
* @author Ava 
*/

    
    public String execute(String s){
        Room newRoom = GameState.instance().getDungeon().getRoom("Rotunda");
        GameState.instance().setAdventurersCurrentRoom(newRoom);
        if(GameState.instance().getAdventurersCurrentRoom().equals(newRoom)){
            Room alternateRoom = GameState.instance().getDungeon().getRoom("Basement");
            GameState.instance().setAdventurersCurrentRoom(alternateRoom);
            alternateRoom.setBeenHere(true);
            return "\n" + alternateRoom.describe() + "\n";
        }
        else{
            newRoom.setBeenHere(true);
            return "\n" + newRoom.describe() + "\n";
        }
    }
    
}
