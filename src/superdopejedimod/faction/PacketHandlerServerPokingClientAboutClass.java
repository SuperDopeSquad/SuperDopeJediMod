package superdopesquad.superdopejedimod.faction;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerServerPokingClientAboutClass implements IMessageHandler<PacketServerPokingClientAboutClass, IMessage> {

	
	@Override 
	public IMessage onMessage(PacketServerPokingClientAboutClass message, MessageContext ctx) {
 				
		try {
			System.out.println("RECEIVED PacketServerPokingClientAboutClass");
				
			// By receiving this messsage, we know that the server really wants us to ask it about what
			// our current class/faction is, because it feels like we might have wrong information.
			// So, Let's ask about our class/faction!
			PacketClientAskingServerAboutClass packet = new PacketClientAskingServerAboutClass();
			System.out.println("SENDING PacketClientAskingServerAboutClass");
			SuperDopeJediMod.packetManager.INSTANCE.sendToServer(message);
		}
		catch (Exception exception) {
			System.out.println("ERROR: processing PacketServerPokingClientAboutClass: " + exception.getMessage());
		}
		
		return null;
	}
}