
package zeitz_borkv3;


/**
 * Used to enter a locked exit if the command is the correct answer to the
 * riddle. This is another extension of the abstract command class. This
 * class will take in an answer from the adventurer and interpret it to
 * see if it is close, wrong or right. Every riddle will be only one word,
 * but there are some that might have more than one acceptable answer. Or
 * they may have an answer that is close to being correct, but not fully
 * acceptable.
 * 
 * @author Jake
 */
class AnswerCommand extends Command{
    
    private String answer;
    private ArrayList<String> answers;
    /**
     * This is the constructor method for the answer command when
     * prompted by the user. It takes in a String from the user to
     * interpret.
     * 
     * @param answer    an answer from the user is taken in and processed
     *                  and interpreted to see whether or not they can
     *                  can enter the room.
     */   
    AnswerCommand(String answer) {
        this.answer = answer;
    }
    /**
     * Executes the action of the command by taking in the answer from
     * the user and determining if it is an acceptable answer.
     * 
     * @return String   a message telling the adventurer if the answer they
     *                  provided is right, wrong, or close and if they have
     *                  gained access to the room.
     */
    String execute() {
        
        if(answer.equals("toyoda") || answer.equals("Toyoda")){
            GameState gs = GameState.instance();
            gs.getDungeon().getRoom("Vaders Command Room").unlock();
            return "Correct!";
        } else if(answer.equals("forks") || answer.equals("Forks")){
            GameState gs = GameState.instance();
            gs.getDungeon().getRoom("Conference Room").unlock();
            return "You got it!";
        } else if(answer.equals("sithy") || answer.equals("Sithy")){
            GameState gs = GameState.instance();
            gs.getDungeon().getRoom("Emperors Throne Room").unlock();
            return "Well done!";
        } else if(answer.equals("R2Detour") || answer.equals("r2detour")){
            GameState gs = GameState.instance();
            gs.getDungeon().getRoom("Super weapon Firing Room").unlock();
            return "Nice!";
        } else{
            return "Not quite. Try again?";
        }
        
    }
}
