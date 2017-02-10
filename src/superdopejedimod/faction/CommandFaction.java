package superdopesquad.superdopejedimod.faction;


import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseCommand;


public class CommandFaction extends BaseCommand
{ 
	
	static String commandName = "faction";
	static String commandUsage = "faction [set faction-name]";
	
	
    public CommandFaction() { 

    	super(commandName, commandUsage);
    } 
  
    
    @Override
	public List<String> getCommandAliases() {
		
    	ArrayList aliases = new ArrayList();
    	aliases.add("fac");
		return aliases;
	}
  
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		World world = sender.getEntityWorld(); 
		    
	    if (world.isRemote) { 
	    	System.out.println("Not processing on Client side.  Aborting."); 
	        return;
	    } 
	    
	    // Debug info.
	    String playerName = sender.getName();
    	System.out.println(playerName);
	        
	    // If there are no arguments, spit out the faction of the current user.
	    if (args.length == 0) { 
	    	sender.addChatMessage(new TextComponentString("You are a member of the Jedi faction.")); 
	    	return; 
	    }
	    
	    // We only support zero arguments, or 2 arguments.
	    if (args.length == 1 || args.length > 2) {
	    	sender.addChatMessage(new TextComponentString("Invalid number of arguments.")); 
	    	return;
	    }
	    
	    // OK, we have 2 arguments.
	    String verbName = args[0];
	    String factionName = args[1];
	    System.out.println("Verb name: -->" + verbName + "<--");
	    System.out.println("Faction name: -->" + factionName + "<--");
	    	
	    if (verbName.equalsIgnoreCase("set")) {
	    		
	    	sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + factionName + " faction.")); 
	    	return;
	    }
	    	
	    else {
	    	
	    	sender.addChatMessage(new TextComponentString("Invalid argument: don't understand '" + verbName + "'")); 
		    return;
	    }
	}
}