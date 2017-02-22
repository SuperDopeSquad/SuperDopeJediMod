package superdopesquad.superdopejedimod.faction;


import java.util.UUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketPlayerSetClass implements IMessage {


	private UUID _playerId;
	private int _classId;
		  

	// A default constructor is always required
	public PacketPlayerSetClass() {}

	  
	public PacketPlayerSetClass(EntityPlayer player, int classId) {
	    
			this._playerId = player.getUniqueID();
			this._classId = classId;
	}

	
	public UUID getPlayerId() {
		return this._playerId;
	}
	
	
	public int getClassId() {
		return this._classId;
	}
	
	
	 @Override
	 public void fromBytes(ByteBuf buffer) {
	 
		 long mostsignificant  = buffer.readLong();
		 long leastsignificant  = buffer.readLong();

		 this._playerId = new UUID(mostsignificant, leastsignificant);
		 this._classId = buffer.readInt(); 
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		 buffer.writeLong(this._playerId.getMostSignificantBits());
		 buffer.writeLong(this._playerId.getLeastSignificantBits());
		 buffer.writeInt(this._classId);
	 }
}