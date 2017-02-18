package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.playerclass.PacketHandlerPlayerSetClass;
import superdopesquad.superdopejedimod.playerclass.PacketPlayerSetClass;


public class SuperDopePacketManager  {

	private Integer _nextMessageId = 0;
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SuperDopeJediMod.MODID);
	
	
	public SuperDopePacketManager() {
		
		INSTANCE.registerMessage(PacketHandlerPlayerSetClass.class, PacketPlayerSetClass.class, _nextMessageId++, Side.CLIENT);
	}
	
	
	
}
