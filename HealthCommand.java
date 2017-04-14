

package GroupBork;
/**
 * Used when the adventurer commands the system
 * to tell them what their health is currently at. Health
 * Command is another class extending the Command class. The health
 * of the adventurer depends on their actions with certain items 
 * and how well they keep track of hunger.
 * 
 * @author Jake
 */
class HealthCommand extends Command{
    /**
     * This is the constructor method for the health command when
     * prompted by the user. It does not take in any parameters and
     * does not return anything either.
     */
    HealthCommand() {
        
    }
    /**
     * Executes the action of the command by getting the health of the
     * adventurer and interpreting it.
     * 
     * @return String   a message describing how the adventurer "feels"
     *                  based on how much health he/she has.
     */
    String execute() {
        int health = GameState.instance().getHealth();
        String healthReturn = "";
        
        if(health > 0 && health <= 10){
            healthReturn =  "You are close to death.\n";
        }
        else if(health > 10 && health <=20){
            healthReturn = "You do not feel good at all and need to eat very soon.\n";
        }
        else if(health > 20 && health <=30){
            healthReturn = "You feel very light-headed.\n";
        }
        else if(health > 30 && health <=40){
            healthReturn = "Your stomach grumbles violently.\n";
        }
        else if(health > 40 && health <=50){
            healthReturn = "You are starting to feel a bit fatigued.\n";
        }
        else if(health > 50 && health <=60){
            healthReturn = "Your stomach grumbles lightly.\n";
        }
        else if(health > 70 && health <=80){
            healthReturn = "You feel good and could eat something, but not in a hurry.\n";
        }
        else if(health > 80 && health <=90){
            healthReturn = "You feel good and comfortable.\n";
        }
        else if(health > 90 && health <=100){
            healthReturn = "You feel great!\n";
        }
        
        return healthReturn;
    }
}
