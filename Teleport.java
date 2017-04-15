



package GroupBork;

import java.util.ArrayList;
import java.util.Collections;

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
        GameState gs = GameState.instance();
        Room currentRoom = gs.getAdventurersCurrentRoom();
        
        ArrayList<String> roomsArray = Collections.list(gs.getDungeon().getAllRooms().keys());
        int index = roomsArray.size()/2;
        String newRoomName = roomsArray.get(index);
        String alternateRoomName = roomsArray.get(index-1);
        
        
        Room newRoom = gs.getDungeon().getRoom(newRoomName);
        Room alternateRoom = gs.getDungeon().getRoom(alternateRoomName);
        if(!currentRoom.equals(newRoom)){
            gs.setAdventurersCurrentRoom(newRoom);
            newRoom.setBeenHere(true);
            return "\n" + newRoom.describe() + "\n";
        }
        else {
            gs.setAdventurersCurrentRoom(alternateRoom);
            alternateRoom.setBeenHere(true);
            return alternateRoom.describe() + "\n";
        }    
    }
    
}
