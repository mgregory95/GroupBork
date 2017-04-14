
package GroupBork;

/**
 * The win class is an event class. This class extends the abstract class Event. 
 * This class produces a win message and closes down the game when instantiated.  
 * @author Ava 
 */
public class Win extends Event {
    
 /**
 * This is the Win constructor. When the adventurer wins a win event is created. 
 *
 * @author Ava 
 */
    public Win(){
        
    }
    /**
* This is the execute method that the Win class must have after extending Event - execute alters the
* game to close it down and produces a WINNER! message.
* @return A String letting the player know that they won
* @author Ava
*/
    
    public String execute(String s){
        GameState.instance().setWinStatus(true);
        return "YOU WIN! Congrats you beat the game! Type one final farewell message to close the game!";
    }
    
}
