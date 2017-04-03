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
import superdopesquad.superdopejedimod.SuperDopeJediMod;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.


public class PacketHandlerServerTellingClientAboutTeleporterInfo implements IMessageHandler<PacketServerTellingClientAboutTeleporterInfo, IMessage> {

	
	@Override 
	public IMessage onMessage(PacketServerTellingClientAboutTeleporterInfo message, MessageContext ctx) {
 			
		try {
						
			//UUID teleporterEntityUuid = message.getTeleporterEntityUuid();
			int teleporterEntityId = message.getTeleporterEntityId();
			BlockPos blockPos = message.getBlockPos();		
			//EnumFacing facing = message.getFacing();
			//Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(teleporterEntityId);
			//Entity entity = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(playerId);			
				
			EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;	
			World world = Minecraft.getMinecraft().theWorld;
		    
			
			
			////
			
			// Get a handle to the teleporter entity, and it's info that we want to know about.
			//int teleporterEntityId = message.getTeleporterEntityId();
			Entity entity = world.getEntityByID(teleporterEntityId);
					
					//server.getEntityFromUuid(teleporterEntityId);
						if (entity == null) {
							System.out.println("ERROR! id returns null entity in PacketClientAskingServerAboutTeleporterDestination.");
							return null;
						}
						if (!(entity instanceof TeleporterEntity)) {
							System.out.println("ERROR! Received bad id in PacketClientAskingServerAboutTeleporterDestination.");
							return null;
						}
						
						TeleporterEntity teleporterEntity = (TeleporterEntity)entity;
						teleporterEntity.setTeleporterDestination(blockPos);
						//teleporterEntity.setTeleporterDirection(facing);
						//BlockPos blockPos = teleporterEntity.getTeleporterDestination();
						//EnumFacing facing = teleporterEntity.getTeleportDirection();
			
			////
			
			
			
			
//			// Check to make sure this blockPos is clear.
//			if (!(world.isAirBlock(blockPos))) {
//				entityPlayer.addChatMessage(new TextComponentString("Your destination is blocked!  Teleportation suspended.")); 
//			    return null;
//			}
				
			// Move the current player.
			//entityPlayer.setLocationAndAngles(blockPos.getX(), blockPos.getY(), blockPos.getZ(), entityPlayer.rotationYaw, entityPlayer.rotationPitch);
		}
		
		catch (Exception exception) {
			System.out.println("ERROR: unpacking PacketServerTellingClientAboutTeleporterInfo: " + exception.getMessage());
		}
		
		return null;
	}
}