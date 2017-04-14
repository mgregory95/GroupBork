
package GroupBork;
/**
 * This class keeps track of the adventurers score and will add or subtract
 * from it depending on what happens. The user can use a command to stay
 * up to date with their score throughout the game.
 * 
 * @author Ava
 */
class Score extends Event {
    
    private int additionalScore; 
    
    /**
     * The constructor method for the score event takes in the number of
     * points to add or subtract from the adventurers score.
     * 
     * @param int s is the change in point value
    */
    public Score(int s){
        this.additionalScore = s;
    }
    
    /**
     * The execute method lets the user know how many points were added to or
     * deducted from their score and tells them what their score is after
     * it has been updated. They can also call it again even if there isn't
     * a change in the score by using the score command.
     * 
     * @return a String saying that the user has scored and the updated score
    */ 
    public String execute(String s){
        int previousScore = GameState.instance().getScore();
        int newScore = previousScore + additionalScore;
        
        GameState.instance().setScore(newScore);
       return "You gained " + additionalScore + "points!\nYou now have " + newScore + " points!";
    }
    
}
