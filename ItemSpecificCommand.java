/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        

    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

    public String execute() {
        try{
            GameState gs = GameState.instance();
            Item i = gs.getDungeon().getItem(noun);
            String message = i.getMessageForVerb(verb);
            if(i.getEventFromVerb(verb)!= null){
                String event = i.getEventFromVerb(verb);
                if(event.contains(",")){
                    String [] separateEvents = event.split(",");
                    for(int j = 0; j< separateEvents.length; j++){
                        if(i.getEventFromVerb(verb)!=null){
                            System.out.println(EventFactory.instance().parse(separateEvents[j]).execute(noun));
                        }
                    }
                }
                else
                    System.out.println(EventFactory.instance().parse(event).execute(noun));
            }
            return message + "\n";
        } catch (NullPointerException e){
            return("Sorry, you can't " + verb + " the " + noun + ".\n");
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.\n";
        
        }
        
    }
    
}


