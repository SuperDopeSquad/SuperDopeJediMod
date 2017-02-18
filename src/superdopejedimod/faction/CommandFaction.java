package superdopesquad.superdopejedimod.faction;


import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseCommand;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


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
		EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
		    
	    if (world.isRemote) { 
	    	System.out.println("Not processing on Client side.  Aborting."); 
	        return;
	    } 
	    
	    // Debug info.
	    String playerName = sender.getName();
    	System.out.println(playerName);
	        
	    // If there are no arguments, spit out the faction of the current user.
	    if (args.length == 0) { 
	    	
	    	String factionName = SuperDopeJediMod.factionManager.getPlayerFactionName(player);
	    	
	    	sender.addChatMessage(new TextComponentString("You are a member of the " + factionName + " faction.")); 
	    	return; 
	    }
	    
	    // We only support zero arguments, or 2 arguments.
	    if (args.length == 1 || args.length > 2) {
	    	sender.addChatMessage(new TextComponentString("Invalid number of arguments.")); 
	    	return;
	    }
	    
	    // OK, we have 2 arguments.
	    String verbName = args[0];
	    String inputFactionName = args[1];
	    System.out.println("Verb name: -->" + verbName + "<--");
	    System.out.println("Faction name: -->" + inputFactionName + "<--");
	    	
	    if (verbName.equalsIgnoreCase("set")) {
	    		
	    	// First let's set the faction, then, let's refetch it.
	    	boolean factionSetSuccessfully = SuperDopeJediMod.factionManager.setPlayerFactionByName(player, inputFactionName);
	    	if (!factionSetSuccessfully) {
	    		sender.addChatMessage(new TextComponentString("Failed to set your faction.")); 
	    		return;
	    	}
	    	
	    	String outputFactionName = SuperDopeJediMod.factionManager.getPlayerFactionName(player);
	    	sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + outputFactionName + " faction.")); 
	    	return;
	    }
	    	
	    else {
	    	
	    	sender.addChatMessage(new TextComponentString("Invalid argument: don't understand '" + verbName + "'")); 
		    return;
	    }
	}
}