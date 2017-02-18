package superdopesquad.superdopejedimod;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import superdopesquad.superdopejedimod.playerclass.CommandClass;
import superdopesquad.superdopejedimod.playerclass.CommandFaction;


public class CommandManager {

	
	void serverLoad(FMLServerStartingEvent event) {
		
		event.registerServerCommand(new CommandClass());
		event.registerServerCommand(new CommandFaction());
		//event.registerServerCommand(new CommandClasses());
	}
}
