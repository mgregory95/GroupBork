
package zeitz_borkv3;


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
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }
    
    
    //Another Exit Constructor     GROUP BORK
    
    /** Given a Scanner object positioned at the beginning of an "exit" file
        entry, read and return an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        * @param s The scanner object that will read through the file. 
        * @param visibility  this boolean allows the program to know is the exit
        * is hidden or not. The only place this will be true is for the 
        * hidden exit of which there will only be two. Every other exit will 
        * have a negative value for this boolean. 
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d, boolean visibility) throws NoExitException, IllegalDungeonFormatException {
        
    }

    // Common object initialization tasks.
    private void init() {
    }

    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
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
        
    }
}

