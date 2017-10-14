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
	static String commandUsage = "faction[s] [list]";
	
	
    public CommandFaction() { 

    	super(commandName, commandUsage);
    } 
  
    
    @Override
	public List<String> getAliases() {
		
    	ArrayList aliases = new ArrayList();
    	aliases.add("factions");
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
	    //String playerName = sender.getName();
    	//System.out.println(playerName);
	        
	    // If there are no arguments, spit out the faction of the current user.
	    if (args.length == 0) { 
	    	
	    	this.spitOutFactionList(sender);
	    	return;
//	    	String className = SuperDopeJediMod.classManager.getPlayerClassName(player);
//	    	
//	    	sender.addChatMessage(new TextComponentString("You are a member of the " + className + " class.")); 
//	    	return; 
	    }
	    
	    // What is the first verb?  Is it 'clear'?
	    String verbName = args[0];
	    
//	    if (verbName.equalsIgnoreCase("clear")) {
//	    	
//	    	// Let's clear the class.
//	    	ClassInfo classInfoUnaffiliated = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.UNAFFILIATED);
//	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassById(player, classInfoUnaffiliated.getId());
//	    	if (!classSetSuccessfully) {
//	    		sender.addChatMessage(new TextComponentString("Failed to clear your class.")); 
//	    		return;
//	    	}
//	    	
//	    	// Let's pipe into the chat window..
//	    	//String outputClassName = SuperDopeJediMod.classManager.getPlayerClassName(player);
//	    	//sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + outputClassName + " class.")); 
//	    	sender.addChatMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player))); 
//	    	
//	    	return;
//	    }
	    
	    
	    if (verbName.equalsIgnoreCase("list")) {
	    	
	    	this.spitOutFactionList(sender);
	    	return;
	    }
//	    	ClassInfo[] classes = SuperDopeJediMod.classManager.getClasses();
//	 	    
//	    	for (ClassInfo classInfo : classes) {
//	 	    	
//	 	    	String message = classInfo.getName();
//		    	FactionInfo factionInfo = classInfo.getFaction();
//		    	if (factionInfo != null) {
//		    		message += " - affiliated with " + factionInfo.getName();
//		    	}
//		    	sender.addChatMessage(new TextComponentString(message));
//	 	    }
//	    	
//	 	    return;
//	    }
	    
//	    // For other commands, we only support zero arguments, or 2 arguments.
//	    if (args.length == 1 || args.length > 2) {
//	    	sender.addChatMessage(new TextComponentString("Invalid number of arguments.")); 
//	    	return;
//	    }
//	    
//	    // OK, we have 2 arguments.
//	    //String verbName = args[0];
//	    String inputClassName = args[1];
//	  
//	    	
//	    if (verbName.equalsIgnoreCase("set")) {
//	    		
//	    	// First let's set the faction, then, let's refetch it.
//	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
//	    	if (!classSetSuccessfully) {
//	    		sender.addChatMessage(new TextComponentString("Failed to set your class.")); 
//	    		return;
//	    	}
//	    	
//	    	//String outputClassName = SuperDopeJediMod.classManager.getPlayerClassName(player);
//	    	//sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + outputClassName + " class.")); 
//	    	sender.addChatMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player))); 
//
//	    	return;
//	    }
	    	
	    else {
	    	
	    	sender.sendMessage(new TextComponentString("Invalid argument: don't understand '" + verbName + "'")); 
		    return;
	    }
	}
	
	
	private void spitOutFactionList(ICommandSender sender) {
		
		ArrayList<FactionInfo> factions = SuperDopeJediMod.classManager.getFactions();
 	    
    	for (FactionInfo factionInfo : factions) {
 	    	
 	    	String message = factionInfo.getName();
	    	
 	    	ArrayList<ClassInfo> classes = factionInfo.getClasses();
	    	if (classes.size() > 0) {
	    		message += " - classes are ";
	    		
	    		boolean thisIsFirstOne = true;
	    		for (ClassInfo classInfo : classes) {
	    			if (!thisIsFirstOne) {
	    				message += ", ";
	    			}
	    			else {
	    				thisIsFirstOne = false;
	    			}
	    			
	    			message += classInfo.getName();
	    		}
	    	}
	    	
	    	
	    	sender.sendMessage(new TextComponentString(message));
 	    }
    	
 	    return;
	}
}