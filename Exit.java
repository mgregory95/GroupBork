

package GroupBork;


import java.util.Scanner;
/**
 * The exit class constructs an exit object with the constructor and holds all
 * relevant information regarding the exit object - this information includes 
 * the source room and the destination of each object and whether it is hidden or not. 
 */
public class Exit {

    class NoExitException extends Exception {}

    private String dir;
    private Room src, dest;
    private boolean visibility;

    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }


    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        this.visibility = Boolean.parseBoolean(s.nextLine().substring(11));
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }
    

    // Common object initialization tasks.
    private void init() {
    }

    String describe() {
        if(this.getVisibility()==true)
            return "You can go " + dir + " to " + dest.getTitle() + ".";
        else
            return "";
    }

    String getDir() { return dir; }
    Room getSrc() { return src; }
    Room getDest() { return dest; }
    
    
 /**
 * 
 * This method is a getter for the visibility instance variable - Visibility will 
 * let the user know if an exit is hidden or not; If hidden visibility will be false.
 * @return visibility  - a boolean that expresses the visibility 
 *  @author Meredith Gregory
 * 
 */
    boolean getVisibility(){
        return this.visibility;
    }
}


