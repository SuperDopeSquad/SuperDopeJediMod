package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.faction.PacketClientAskingServerAboutClass;
import superdopesquad.superdopejedimod.faction.PacketHandlerClientAskingServerAboutClass;
import superdopesquad.superdopejedimod.faction.PacketHandlerPlayerSetClass;
import superdopesquad.superdopejedimod.faction.PacketHandlerServerPokingClientAboutClass;
import superdopesquad.superdopejedimod.faction.PacketPlayerSetClass;
import superdopesquad.superdopejedimod.faction.PacketServerPokingClientAboutClass;
import superdopesquad.superdopejedimod.teleporter.PacketClientAskingServerAboutTeleporterDestination;
import superdopesquad.superdopejedimod.teleporter.PacketHandlerClientAskingServerAboutTeleporterDestination;
import superdopesquad.superdopejedimod.teleporter.PacketHandlerServerTellingClientAboutTeleporterInfo;
import superdopesquad.superdopejedimod.teleporter.PacketHandlerTeleporterSetDestination;
import superdopesquad.superdopejedimod.teleporter.PacketServerTellingClientAboutTeleporterInfo;
import superdopesquad.superdopejedimod.teleporter.PacketTeleporterSetDestination;


public class SuperDopePacketManager  {

	private Integer _nextMessageId = 0;
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SuperDopeJediMod.MODID);
	
	
	public SuperDopePacketManager() {

		INSTANCE.registerMessage(PacketHandlerPlayerSetClass.class, PacketPlayerSetClass.class, _nextMessageId++, Side.CLIENT);
		INSTANCE.registerMessage(PacketHandlerClientAskingServerAboutClass.class, PacketClientAskingServerAboutClass.class, _nextMessageId++, Side.SERVER);
		INSTANCE.registerMessage(PacketHandlerServerPokingClientAboutClass.class, PacketServerPokingClientAboutClass.class, _nextMessageId++, Side.CLIENT);
		INSTANCE.registerMessage(PacketHandlerTeleporterSetDestination.class, PacketTeleporterSetDestination.class, _nextMessageId++, Side.CLIENT);
		INSTANCE.registerMessage(PacketHandlerClientAskingServerAboutTeleporterDestination.class, PacketClientAskingServerAboutTeleporterDestination.class, _nextMessageId++, Side.SERVER);	
		INSTANCE.registerMessage(PacketHandlerServerTellingClientAboutTeleporterInfo.class, PacketServerTellingClientAboutTeleporterInfo.class, _nextMessageId++, Side.CLIENT);	
	}
}
