
package borkgroupeventclass;


/**
* The transform class is an event class. This class extends the abstract class Event. 
* This class produces a message and alters an item when called. 
*/
public class Transform extends Event {
    
    private Item p; 
    
 /**
 * This is the Transform constructor. When an item is transformed a transform event is created. 
 * @param p  an item that is to be transformed
 * @author Ava 
 */
    
    public Transform(Item p){
        
    }
    
/**
* This is the execute method that the Transform class must have after extending Event - execute alters the
* state of an item
* @return a String saying the Item was transformed
* @author Ava 
*/
    
    public String execute(){
        
    }
    
}
