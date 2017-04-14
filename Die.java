

package GroupBork;

/**
 * 
 * 
 * @author Ava
 */
class Die extends Event {
    /**
     * The die constructor simply creates the event of dying. Nothing
     * is taken in nor given out.
     */
    public Die(){
        
    }
    
    /**
     * The execute function that tells the adventurer that they have died
     * and ends the game.
     * 
     * @return String that the player has died
    */
    
    public String execute(String s){
        return "Tramatic event. Your death is emminent. Use your last move wisely.";
        
    }
    
}
