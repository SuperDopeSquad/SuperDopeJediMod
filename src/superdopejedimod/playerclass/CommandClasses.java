//package superdopesquad.superdopejedimod.playerclass;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import net.minecraft.command.CommandException;
//import net.minecraft.command.ICommand;
//import net.minecraft.command.ICommandSender;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TextComponentString;
//import net.minecraft.world.World;
//import superdopesquad.superdopejedimod.BaseCommand;
//import superdopesquad.superdopejedimod.SuperDopeJediMod;
//
//
//public class CommandClasses extends BaseCommand
//{ 
//	
//	static String commandName = "classes";
//	static String commandUsage = "classes";
//	
//	
//    public CommandClasses() { 
//
//    	super(commandName, commandUsage);
//    } 
//  
//    
//    @Override
//	public List<String> getCommandAliases() {
//		
//    	ArrayList aliases = new ArrayList();
//    	//aliases.add("fac");
//		return aliases;
//	}
//  
//	
//	@Override
//	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//		
//		World world = sender.getEntityWorld(); 
//		EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
//		    
//	    if (world.isRemote) { 
//	    	System.out.println("Not processing on Client side.  Aborting."); 
//	        return;
//	    } 
//	    
//	    // Debug info.
//	    //String playerName = sender.getName();
//    	//System.out.println(playerName);
//	    ClassInfo[] classes = SuperDopeJediMod.classManager.getClasses();
//	    for (ClassInfo classInfo : classes) {
//	    	
//	    	String message = classInfo.getName();
//	    	FactionInfo factionInfo = classInfo.getFaction();
//	    	if (factionInfo != null) {
//	    		message += " - affiliated with " + factionInfo.getName();
//	    	}
//	    	sender.addChatMessage(new TextComponentString(message));
//	    }
//	    
////	    // If there are no arguments, spit out the faction of the current user.
////	    if (args.length == 0) { 
////	    	
////	    	// String className = SuperDopeJediMod.classManager.getPlayerClassName(player);
////	    	
////	    	sender.addChatMessage(new TextComponentString("You are a member of the " + className + " class.")); 
////	    	return; 
////	    }
//	    
//	    // We only support zero arguments.
//	    if (args.length > 0) {
//	    	sender.addChatMessage(new TextComponentString("Invalid number of arguments.")); 
//	    	return;
//	    }
//	    
////	    // OK, we have 2 arguments.
////	    String verbName = args[0];
////	    String inputClassName = args[1];
////	    //System.out.println("Verb name: -->" + verbName + "<--");
////	    //System.out.println("Faction name: -->" + inputFactionName + "<--");
////	    	
////	    if (verbName.equalsIgnoreCase("set")) {
////	    		
////	    	// First let's set the faction, then, let's refetch it.
////	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
////	    	if (!classSetSuccessfully) {
////	    		sender.addChatMessage(new TextComponentString("Failed to set your class.")); 
////	    		return;
////	    	}
////	    	
////	    	String outputClassName = SuperDopeJediMod.classManager.getPlayerClassName(player);
////	    	sender.addChatMessage(new TextComponentString("Congratulations! You are now a member of the " + outputClassName + " class.")); 
////	    	return;
////	    }
////	    	
////	    else {
////	    	
////	    	sender.addChatMessage(new TextComponentString("Invalid argument: don't understand '" + verbName + "'")); 
////		    return;
////	    }
//	}
//}