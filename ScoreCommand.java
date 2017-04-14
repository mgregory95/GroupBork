

package GroupBork;

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
    String execute(){
        GameState gs = GameState.instance();
        int score = gs.getScore();
        
        String adventurerRank = "";
        if(score >= 0 && score < 10){
            adventurerRank = "Beginner";
        }
        else if  (score >= 10 && score < 25){
            adventurerRank = "Amateur Adventurer";
        }
        else if  (score >= 25 && score < 50){
            adventurerRank = "Advanced Adventurer";
        }
        else if  (score >= 50 && score < 75){
            adventurerRank = "Professional Adventurer";
        }
        else if  (score >= 75 && score < 100){
            adventurerRank = "Master Adventurer";
        }
        else if  (score >= 100){
            adventurerRank = "Indiana Jones";
        }
        return "You have " + score + " points on this adventure. You have a rank of " + adventurerRank.trim() + ".\n";
    }
}
