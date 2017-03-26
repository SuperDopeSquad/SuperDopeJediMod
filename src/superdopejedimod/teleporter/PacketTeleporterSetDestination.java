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


	//private UUID _playerId;
	private UUID _teleporterEntityUuid;
	private int _teleporterEntityId;
	//private int _classId;
	private BlockPos _blockPos;
		  

	// A default constructor is always required
	public PacketTeleporterSetDestination() {}

	  
	public PacketTeleporterSetDestination(TeleporterEntity teleporterEntity, BlockPos blockPos) {
	    
			this._teleporterEntityUuid = teleporterEntity.getUniqueID();
			this._teleporterEntityId = teleporterEntity.getEntityId();
			
			EnumFacing facing = teleporterEntity.getHorizontalFacing();
			
			this._blockPos = blockPos;
	}

	
	public UUID getTeleporterEntityUuid() {
		return this._teleporterEntityUuid;
	}
	
	
	public int getTeleporterEntityId() {
		return this._teleporterEntityId;
	}
	
	
	public BlockPos getBlockPos() {
		return this._blockPos;
	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
	 
		 // Get the UUID.
		 long mostsignificant  = buffer.readLong();
		 long leastsignificant  = buffer.readLong();
		 this._teleporterEntityUuid = new UUID(mostsignificant, leastsignificant);
		 
		 // Get the Id.
		 this._teleporterEntityId = buffer.readInt();
		 
		 // Get the BlockPos.
		 int x = buffer.readInt();
		 int y = buffer.readInt();
		 int z = buffer.readInt();
		 this._blockPos = new BlockPos(x, y, z);
		 //this._classId = buffer.readInt(); 
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		 buffer.writeLong(this._teleporterEntityUuid.getMostSignificantBits());
		 buffer.writeLong(this._teleporterEntityUuid.getLeastSignificantBits());
		 
		 buffer.writeInt(this._teleporterEntityId);
		 
		 //buffer.writeInt(this._classId);
		 buffer.writeInt(this._blockPos.getX());
		 buffer.writeInt(this._blockPos.getY());
		 buffer.writeInt(this._blockPos.getZ());
	 }
}