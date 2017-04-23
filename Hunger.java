/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author Meredith
 */
public class Hunger extends Event{
    private int p;
    // constructor
    public Hunger(int p){
        this.p = p;
    }
    public String execute(String s){
        GameState.instance().updateHunger(this.p);
        return "";
    }
    
}
