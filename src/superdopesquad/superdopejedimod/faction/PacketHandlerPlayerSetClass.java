package superdopesquad.superdopejedimod.faction;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerPlayerSetClass implements IMessageHandler<PacketPlayerSetClass, IMessage> {

	
	@Override 
	public IMessage onMessage(PacketPlayerSetClass message, MessageContext ctx) {
 			
		try {
						
			UUID playerId = message.getPlayerId();
			int classId = message.getClassId();
			//System.out.println("DEBUG: RECEIVED PacketPlayerSetClass: " + playerId + ", " + classId);
			//System.out.println("MINECRAFT:  " + (Minecraft.getMinecraft() != null));
			//System.out.println("WORLD: " + (Minecraft.getMinecraft().theWorld != null));
			
			Entity entity = Minecraft.getMinecraft().world.getPlayerEntityByUUID(playerId);
			//System.out.println("ENTITY:" + (entity != null));
			EntityPlayer player = (EntityPlayer) entity;
			//System.out.println("PLAYER:" + (player != null));
			boolean setSuccess = SuperDopeJediMod.classManager.setPlayerClassByClientId(player, classId);
			//System.out.println(setSuccess);
		}
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketPlayerSetClass: " + exception.getMessage());
		}
		
		return null;
	}
}