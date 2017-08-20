package superdopesquad.superdopejedimod.teleporter;


import java.util.UUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketServerTellingClientAboutTeleporterInfo implements IMessage {


	private int _teleporterEntityId;
	private BlockPos _blockPos;
	private UUID _playerId;
		  

	// A default constructor is always required
	public PacketServerTellingClientAboutTeleporterInfo() {}

	public PacketServerTellingClientAboutTeleporterInfo(EntityPlayer player, int teleporterEntityId, BlockPos blockPos) {
	
		this._playerId = player.getUniqueID();    
		this._teleporterEntityId = teleporterEntityId;
		this._blockPos = blockPos;
	}
	
	public UUID getPlayerId() {		
 		return this._playerId;		
 	}
	
	public BlockPos getBlockPos() {
		return this._blockPos;
	}
	
	
	public int getTeleporterEntityId() {
		return this._teleporterEntityId;
	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
		
		 // Get the Player id.	
 		 long mostsignificant  = buffer.readLong();		
 		 long leastsignificant  = buffer.readLong();		
 		 this._playerId = new UUID(mostsignificant, leastsignificant);
 
		 // Get the teleporter id.
		 this._teleporterEntityId = buffer.readInt();
		 
		 // Get the BlockPos.
		 int x = buffer.readInt();
		 int y = buffer.readInt();
		 int z = buffer.readInt();
		 this._blockPos = new BlockPos(x, y, z);
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		// Write the Player Id.
 		buffer.writeLong(this._playerId.getMostSignificantBits());		
 		buffer.writeLong(this._playerId.getLeastSignificantBits());
 		
		  // Set the teleporter id.
		 buffer.writeInt(this._teleporterEntityId);
		 
		 // Set the BlockPos.
		 buffer.writeInt(this._blockPos.getX());
		 buffer.writeInt(this._blockPos.getY());
		 buffer.writeInt(this._blockPos.getZ());
	 }
}
