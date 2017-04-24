
package GroupBork;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meredith
 */
public class DigCommand extends Command{
    private int digCount;
    //constructor 
    public DigCommand(int digCount){
        this.digCount = digCount;
    }
    
    String execute(){
        GameState gs = GameState.instance();
        int newHealth = gs.getHealth()- 10;
        gs.setHealth(newHealth);
        
        String returnString = "";
        if(digCount==1){
            returnString = "You have the right idea. Keep digging!";
        }
        if(digCount==2){
            returnString = "Keep going! I hope your strong enough to get out.";
        }
        if(digCount == 3){
            returnString = "Not out yet...";
        }
        if(digCount ==4){
            returnString = "Almost there! Just a little further!";
        }
        if(digCount== 5){
            try {
                gs.restore(gs.DEFAULT_SAVE_FILE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DigCommand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GameState.IllegalSaveFormatException ex) {
                Logger.getLogger(DigCommand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Dungeon.IllegalDungeonFormatException ex) {
                Logger.getLogger(DigCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            returnString = "You are free! All is back to normal...expect your health that is...";
        }
        return returnString; 
          
    }
    
}
