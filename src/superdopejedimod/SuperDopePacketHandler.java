package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class SuperDopePacketHandler  {

	private Integer _nextMessageId = 0;
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SuperDopeJediMod.MODID);
	
	
	public SuperDopePacketHandler() {
		
		INSTANCE.registerMessage(SuperDopeMessageHandler.class, SuperDopePacketMessage.class, _nextMessageId++, Side.CLIENT);
	}
	
	
	
}
