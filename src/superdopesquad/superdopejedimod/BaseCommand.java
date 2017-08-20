package superdopesquad.superdopejedimod;


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


public abstract class BaseCommand implements ICommand
{ 
    private String _commandName;
    private String _commandUsage;
  
    
    public BaseCommand(String commandName, String commandUsage) { 
    	this._commandName = commandName;
    	this._commandUsage = commandUsage;
    } 


    @Override 
    public boolean isUsernameIndex(String[] var1, int var2) 
    { 
        // TODO Auto-generated method stub 
        return false;
    }

    
	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
	
		sender.sendMessage(new TextComponentString("This command not yet implemented.")); 
	    return; 
	}

	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		
		return true;
	}


	@Override
	public String getName() {
		return this._commandName; 
	}


	@Override
	public String getUsage(ICommandSender sender) {
		 return this._commandUsage; 
	}


	@Override
	public List<String> getAliases() {
		return null;
	}
	

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	} 
}