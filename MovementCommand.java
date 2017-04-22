package groupbork;

class MovementCommand extends Command {

    private String dir;
    private int numMoves = 0; 
    
                       

    MovementCommand(String dir) {
        this.dir = dir;
    }

    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            numMoves++; 
            return "\n" + nextRoom.describe() + "\n";
            
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
