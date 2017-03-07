package superdopesquad.superdopejedimod.faction;


import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseCommand;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class CommandClass extends BaseCommand
{ 
	
	static String commandName = "class";
	static String commandUsage = "class[es] [set class-name] | [list] | [clear] | [refresh]";
	
	
    public CommandClass() { 

    	super(commandName, commandUsage);
    } 
  
    
    @Override
	public List<String> getCommandAliases() {
		
    	ArrayList aliases = new ArrayList();
    	aliases.add("classes");
		return aliases;
	}
  
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		World world = sender.getEntityWorld(); 
		EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
		    
	    if (world.isRemote) { 
	    	System.out.println("Not processing on Client side.  Aborting."); 
	        return;
	    } 
	    
	    // Debug info.
	    //String playerName = sender.getName();
    	//System.out.println(playerName);
	        
	    // If there are no arguments, spit out the faction of the current user.
	    if (args.length == 0) { 
	    	
	    	String className = SuperDopeJediMod.classManager.getPlayerClassName(player);
	    	
	    	sender.addChatMessage(new TextComponentString("You are a member of the " + className + " class.")); 
	    	return; 
	    }
	    
	    // What is the first verb?  Is it 'clear'?
	    String verbName = args[0];
	    
	    if (verbName.equalsIgnoreCase("clear")) {
	    	
	    	// Let's clear the class.
	    	ClassInfo classInfoUnaffiliated = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.UNAFFILIATED);
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassById(player, classInfoUnaffiliated.getId());
	    	if (!classSetSuccessfully) {
	    		sender.addChatMessage(new TextComponentString("Failed to clear your class.")); 
	    		return;
	    	}
	    	
	    	// Let's pipe into the chat window..
	    	//String outputClassName = SuperDopeJediMod.classManager.getPlayerClassName(player);
	    	//sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + outputClassName + " class.")); 
	    	sender.addChatMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player))); 
	    	
	    	return;
	    }
	    
	    
	    else if (verbName.equalsIgnoreCase("refresh")) {
	    	
	    	// Let's start a conversation with the client about faction/classes, since we need to make
			// sure all connected clients get refreshed on the current faction/class info of this user.
			PacketServerPokingClientAboutClass packet = new PacketServerPokingClientAboutClass();
			System.out.println("SENDING PacketServerPokingClientAboutClass");
			SuperDopeJediMod.packetManager.INSTANCE.sendTo(packet, player);
			
	    	sender.addChatMessage(new TextComponentString("Class refresh request initiated.")); 
	    	
	    	return;
	    }
	    
	    
	    else if (verbName.equalsIgnoreCase("list")) {
	    	
	    	ArrayList<ClassInfo> classes = SuperDopeJediMod.classManager.getClasses();
	 	    
	    	for (ClassInfo classInfo : classes) {
	 	    	
	    		if (classInfo.getId() != SuperDopeJediMod.classManager.UNAFFILIATED) {
	    			String message = classInfo.getDescription();
//	    			FactionInfo factionInfo = classInfo.getFaction();
//	    			if (factionInfo != null) {
//	    				message += " - affiliated with " + factionInfo.getName();
//	    			}
	    			sender.addChatMessage(new TextComponentString(message));
	    		}
	 	    }
	    	
	 	    return;
	    }
	    
	    else if (verbName.equalsIgnoreCase("player") || verbName.equalsIgnoreCase("players")) {
	    	
	    	PlayerList playerList = server.getPlayerList();
	    	
	    	for (EntityPlayerMP playerMp : playerList.getPlayerList()) {
	    		
	    		String message = playerMp.getName();
	    		ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(playerMp);
	    		if (classInfo != null && (classInfo.getId() != SuperDopeJediMod.classManager.UNAFFILIATED)) {
	    			message += " - " + classInfo.getDescription();
	    		}
	    		sender.addChatMessage(new TextComponentString(message));
	    	}
	    	
	    	return;
	    }
	    
	    // For other commands, we only support zero arguments, or 2 arguments.
	    if (args.length == 1 || args.length > 2) {
	    	sender.addChatMessage(new TextComponentString("Invalid number of arguments.")); 
	    	return;
	    }
	    
	    // OK, we have 2 arguments.
	    //String verbName = args[0];
	    String inputClassName = args[1];
	  
	    	
	    if (verbName.equalsIgnoreCase("set")) {
	    		
	    	// First let's set the faction, then, let's refetch it.
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	    	if (!classSetSuccessfully) {
	    		sender.addChatMessage(new TextComponentString("Failed to set your class.")); 
	    		return;
	    	}
	    	
	    	//String outputClassName = SuperDopeJediMod.classManager.getPlayerClassName(player);
	    	//sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + outputClassName + " class.")); 
	    	sender.addChatMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player))); 

	    	return;
	    }
	    	
	    else {
	    	
	    	sender.addChatMessage(new TextComponentString("Invalid argument: don't understand '" + verbName + "'")); 
		    return;
	    }
	}
}