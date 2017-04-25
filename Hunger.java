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
    public String getHungerMessage(int hunger){
        
        if (hunger >= 90){
            System.out.println("Your belly is full!"); 
        }
        if (hunger <=89 && hunger >= 80){
            System.out.println("You are still feeling pretty full.");
        }
        if (hunger <= 79 && hunger >= 70){
            System.out.println("You could eat, but you can stand to wait."); 
        }
        if (hunger <=69 && hunger >= 60){
            System.out.println("Your stomach starts to growl");
        }
         if (hunger <=59 && hunger >= 50){
            System.out.println("Your getting pretty hungry, maybe you should eat"); 
        }
        if (hunger <=49 && hunger >= 40){
            System.out.println("You must eat very soon");
        }
        if (hunger <= 39 && hunger >= 30){
            System.out.println("You are getting very weak, your body can't hold on much longer"); 
        }
        if (hunger <=29 && hunger >= 20){
            System.out.println("You can hardly muster the energy to move, you need to find food immediatley");
        }
        if (hunger <=19 && hunger >= 10){
            System.out.println("Eat now or you will die very soon"); 
        }
        if (hunger <=9 && hunger >= 1){
            System.out.println("You are on the brink of death");
        }
        if (hunger == 0){
            System.out.print("You have died of starvation"); 
        }
        return "";
    }
    
}
