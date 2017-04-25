
package GroupBork;

import java.util.Random;
import java.util.Scanner;

/**
 * The interpreter holds the main method for Bork - here is where the game 
 * interacts with the user and sends out commands according to user input; for
 * this to work there are a few static methods located here to help control 
 * the flow of the game. 
 */
public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter borkFile.bork|saveFile.sav.";
    

    public static void main(String args[]) {

        String command;
        Scanner commandLine = new Scanner(System.in);
        
        System.out.println("What adventure file would you like to play?");
        System.out.print("> ");
        String filename = commandLine.nextLine();

        try {
            state = GameState.instance();
            
            if (filename.endsWith(".zork")) {
                state.initialize(new Dungeon(filename));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
            } else if (filename.endsWith(".sav")) {
                state.restore(filename);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
            } else {
                System.err.println(USAGE_MSG);
                System.exit(2);
            }

            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);

            while (!command.equals("q")) {
                if(state.triggerEarthquake()==false){
                    if(state.getHealth()!=0 && state.getWinStatus()==false && state.getHunger()!=0){
                        if(state.getHunger()%10==0){
                            Hunger h = new Hunger(0);
                            System.out.println(h.getHungerMessage(state.getHunger()));
                        }
                        System.out.print(
                        CommandFactory.instance().parse(command).execute());

                        command = promptUser(commandLine);
                        }
                    else{
                        System.exit(0);
                    }
                }
                else{
                    state.earthquake();
                    System.out.print(
                        CommandFactory.instance().parse(command).execute());

                        command = promptUser(commandLine);
                        
                }    
            }

            System.out.println("Welp, Adios!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    private static String promptUser(Scanner commandLine) {

        System.out.print("> ");
        return commandLine.nextLine();
    }
    
    


}
