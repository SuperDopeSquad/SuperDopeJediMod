package superdopesquad.superdopejedimod.teleporter;


import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerServerTellingClientAboutTeleporterInfo implements IMessageHandler<PacketServerTellingClientAboutTeleporterInfo, IMessage> {

	
	@Override 
	public IMessage onMessage(PacketServerTellingClientAboutTeleporterInfo message, MessageContext ctx) {
 			
		try {
						
			int teleporterEntityId = message.getTeleporterEntityId();
			BlockPos blockPos = message.getBlockPos();	
			UUID playerId = message.getPlayerId();
			
			EntityPlayer entityPlayer = (EntityPlayer) Minecraft.getMinecraft().world.getPlayerEntityByUUID(playerId);
			//System.out.println("ENTITY:" + (entity != null));
			//EntityPlayer entityPlayer = (EntityPlayer) entity;	
			World world = entityPlayer.getEntityWorld();
		    
			// Get a handle to the teleporter entity, and it's info that we want to know about.
			Entity entity = world.getEntityByID(teleporterEntityId);
					
			if (entity == null) {
				
				System.out.println("ERROR! id returns null entity in PacketClientAskingServerAboutTeleporterDestination.");
				return null;
			}
						
			if (!(entity instanceof TeleporterEntity)) {
				
				System.out.println("ERROR! Received bad id in PacketClientAskingServerAboutTeleporterDestination.");
				return null;
			}
						
			// Actually do the work.
			TeleporterEntity teleporterEntity = (TeleporterEntity)entity;
			teleporterEntity.setTeleporterDestination(blockPos); 
		}
		
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketServerTellingClientAboutTeleporterInfo: " + exception.getMessage());
		}
		
		return null;
	}
}