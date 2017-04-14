

package GroupBork;

/**
 * The wound class is an event class. This class extends the abstract class Event. 
 * This class maintains a health value that the execute method alters when called. 
 * @author Ava
 */
public class Wound extends Event {
    
    private int p; 
    
    
 /**
 * This is the Wound constructor. When the adventurer is "wounded" a wound event is created. 
 * @param p  an integer that is the level of health that needs to be manipulated
 * @author Ava 
 */
    
    public Wound(int p){
        this.p = p;
    }
    
 /**
 * This is the execute method that the Wound class must have after extending Event - execute alters the 
 * health of the adventurer
 * @return a String saying the player was wounded
 * @author Ava 
 */ 
    public String execute(String s){
        int currentHealth = GameState.instance().getHealth();
        int newHealth = currentHealth + this.p;
        
        GameState.instance().setHealth(newHealth);
        return "";
    }
    
}
