
package zeitz_borkv3;

/**
 * Used when the adventurer prompts the system to return their current score
 * for a quick update. This class also extends the abstract Command class.
 * 
 * @author Jake
 */
class ScoreCommand extends Command{
    
    /**
     * This is the constructor method for the score command when
     * prompted by the user. It does not take in any parameters and
     * does not return anything either.
     */
    ScoreCommand() {
        
    }
    /**
     * Executes the action of the command by getting the score of the
     * adventurer and converting it to a String to be printed.
     * 
     * @return String   a string of the numeric score of the adventurer
     *  
     */
    String execute() {
        //return current score
    }
}
