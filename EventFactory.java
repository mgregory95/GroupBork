
package GroupBork;
import java.util.List;
import java.util.Arrays;



/**
 *
 * @author Meredith
 */
public class EventFactory {
    private static EventFactory theInstance;
    public static List<Event> EVENTS;
    
    
    public static synchronized EventFactory instance() {
        if (theInstance == null) {
            theInstance = new EventFactory();
        }
        return theInstance;
    }
    
    private EventFactory(){}
    
    public Event parse(String event) throws Item.NoItemException {
        String eventName = "";
        String parameter = "";
        if(event.contains("(")){                       
                String [] eventParts = event.split("\\(");
                parameter = eventParts[1].replace(")", " ").trim();
                eventName = eventParts[0];
        }
        else{
            eventName = event;
        }
        if(eventName.equals("Wound"))      
            return new Wound(Integer.parseInt(parameter));
        if(eventName.equals("Win"))
            return new Win();
        if(eventName.equals("Die"))
            return new Die();
        if(eventName.equals("Disappear"))
            return new Disappear();
        if(eventName.equals("Score"))
            return new Score(Integer.parseInt(parameter));
        if(eventName.equals("Teleport"))
            return new Teleport();
        if(eventName.equals("Transform")){
            Item item = GameState.instance().getDungeon().getItem(parameter);
            return new Transform(item.toString());
        }
        else 
            return null;        
    }
}


