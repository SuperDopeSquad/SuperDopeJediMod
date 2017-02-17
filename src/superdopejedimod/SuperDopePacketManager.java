package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.faction.PacketHandlerPlayerSetFaction;
import superdopesquad.superdopejedimod.faction.PacketPlayerSetFaction;


public class SuperDopePacketManager  {

	private Integer _nextMessageId = 0;
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SuperDopeJediMod.MODID);
	
	
	public SuperDopePacketManager() {
		
		INSTANCE.registerMessage(PacketHandlerPlayerSetFaction.class, PacketPlayerSetFaction.class, _nextMessageId++, Side.CLIENT);
	}
	
	
	
}
