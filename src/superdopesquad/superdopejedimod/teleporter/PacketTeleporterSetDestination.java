package superdopesquad.superdopejedimod.teleporter;


import java.util.UUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketTeleporterSetDestination implements IMessage {
  
	private BlockPos _blockPos;
	private UUID _playerId;
  
	// A default constructor is always required
	public PacketTeleporterSetDestination() {}

	public PacketTeleporterSetDestination(EntityPlayer player, BlockPos blockPos) {
	this._playerId = player.getUniqueID();
		this._blockPos = blockPos;
	}

	public BlockPos getBlockPos() {
		return this._blockPos;
	}
	
	public UUID getPlayerId() {		
 		return this._playerId;		
 	}
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
	 
	  // Get the Player id.		
 	 long mostsignificant  = buffer.readLong();		
 		 long leastsignificant  = buffer.readLong();		
 		 this._playerId = new UUID(mostsignificant, leastsignificant);
 
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
 
		 buffer.writeInt(this._blockPos.getX());
		 buffer.writeInt(this._blockPos.getY());
		 buffer.writeInt(this._blockPos.getZ());
	 }
}