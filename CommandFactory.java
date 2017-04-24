/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;
import java.util.List;
import java.util.Arrays;

public class CommandFactory {

    private int digCount;
    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }else if (verb.equals("take")) {
            return new TakeCommand(noun);
        }else if (verb.equals("drop")) {
            return new DropCommand(noun);
        }else if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }else if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }else if (verb.equals("answer:")){
            return new AnswerCommand(noun);
        }else if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }else if (verb.equals("score")){
            return new ScoreCommand();
        }else if(verb.equals("health")){
            return new HealthCommand();
        }else if (verb.equals("dig")){
            digCount++;
            return new DigCommand(digCount);
        }
        return new UnknownCommand(command);
    }
}