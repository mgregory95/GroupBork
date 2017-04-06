package zeitz_borkv3;

/**
 * Once a certain item has been used to its fullest potential, it can
 * disappear from the game entirely. A donut may be eaten or a can of Dr.
 * Pepper may be drank and then crushed and recycled and is then no longer
 * able to be seen ever again by the adventurer.
 * 
 * @author Ava
 */
class Disappear extends Event {
    
    private Item p;
    
    
    /**
     * The constructor for the disappear class takes in an item that is
     * no longer going to be available to the adventurer.
     * 
     * @param p is the Item that needs to disappear
    */ 
    
    public Disappear(Item p){
       this.p = p;
    }
    
    /**
     * The execute method simply deletes the item from the dungeon inventory
     * or from the adventurers inventory. This removes it entirely from the
     * game. It is based on the item itself and what it takes for it to
     * completely disappear.
     * 
     * @return String that the item disappeared
    */
    
    public String execute(){
        
    }
    
}
