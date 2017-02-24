package superdopesquad.superdopejedimod;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import superdopesquad.superdopejedimod.faction.CommandClass;
import superdopesquad.superdopejedimod.faction.CommandFaction;


public class CommandManager {

	
	void serverLoad(FMLServerStartingEvent event) {
		
		event.registerServerCommand(new CommandClass());
		event.registerServerCommand(new CommandFaction());
		//event.registerServerCommand(new CommandClasses());
	}
}
