
package zeitz_borkv3;

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
        //return health message
    }
}
