
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
        String filename = "earthquakeSave";
        
        String returnString = "";
        if(digCount==1){
            returnString = "You have the right idea. Keep digging!\n";
        }
        if(digCount==2){
            returnString = "Keep going! I hope your strong enough to get out.\n";
        }
        if(digCount == 3){
            returnString = "Not out yet...\n";
        }
        if(digCount ==4){
            returnString = "Almost there! Just a little further!\n";
        }
        if(digCount== 5){
            try{
                int health = gs.getHealth();
                gs.restore(filename + ".sav");
                gs.setHealth(health);
                gs.updateNumMoves(1);
                returnString = "You cleared the exits and the rebels have fallen back! All is back to normal...except your health that is...\n";
            } catch(Exception e) { 
            e.printStackTrace(); 
            }
        }
        return returnString; 
          
    }
    
}
