
package zeitz_borkv3;

/**
 * A brand new class that is only available after an Earthquake has happened.
 * If/when the adventurer experiences an earthquake, they are trapped in the
 * current room. All may seem lost, but if they are able to discover this
 * command, then they will be out and in the same dungeon in perfect condition
 * if they have enough health to do it.
 * 
 * @author Jake
 */
class DigCommand extends Command{
    /**
     * This is the constructor method for the dig command when
     * prompted by the user. It does not take in any parameters and
     * does not return anything either.
     */
    DigCommand() {
        
    }
    /**
     * Executes the action of the command by freeing the adventurer from
     * the room after an earthquake.
     * 
     * @return String   a message saying that they had enough health and
     *                  stamina to dig their way out and realizes that
     *                  the earthquake was only in that room and the rest
     *                  of the dungeon appears to be untouched.
     */
    String execute() {
        //return current score
    }
}
