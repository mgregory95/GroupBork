package zeitz_borkv3;

/**
* The Event class is an abstract class from which all different events
* extend from.
*
* @author  Ava
* 
*/
abstract class Event {
    
    /**
     * The execute method that every class needs if it extends this class. 
     * 
     * @return @return a String that the Event was executed
     */
    abstract String execute(); 
    
}

