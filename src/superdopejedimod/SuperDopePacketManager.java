package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.playerclass.PacketClientAskingServerAboutClass;
import superdopesquad.superdopejedimod.playerclass.PacketHandlerClientAskingServerAboutClass;
import superdopesquad.superdopejedimod.playerclass.PacketHandlerPlayerSetClass;
//import superdopesquad.superdopejedimod.playerclass.PacketHandlerServerPokingClientAboutClass;
import superdopesquad.superdopejedimod.playerclass.PacketPlayerSetClass;
//import superdopesquad.superdopejedimod.playerclass.PacketServerPokingClientAboutClass;


public class SuperDopePacketManager  {

	private Integer _nextMessageId = 0;
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SuperDopeJediMod.MODID);
	
	
	public SuperDopePacketManager() {
		
		INSTANCE.registerMessage(PacketHandlerPlayerSetClass.class, PacketPlayerSetClass.class, _nextMessageId++, Side.CLIENT);
		INSTANCE.registerMessage(PacketHandlerClientAskingServerAboutClass.class, PacketClientAskingServerAboutClass.class, _nextMessageId++, Side.SERVER);
		//INSTANCE.registerMessage(PacketHandlerServerPokingClientAboutClass.class, PacketServerPokingClientAboutClass.class, _nextMessageId++, Side.CLIENT);
	}
	
	
	
}
