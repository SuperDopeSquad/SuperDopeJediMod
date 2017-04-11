package superdopesquad.superdopejedimod.teleporter;


import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


public class PacketClientAskingServerAboutTeleporterDestination implements IMessage {

	
	private int _teleporterEntityId;

	
	public PacketClientAskingServerAboutTeleporterDestination() {}

	
	public void setTeleporterEntityId(int teleporterEntityId) {
		
		this._teleporterEntityId = teleporterEntityId;
	}
	
	
	public int getTeleporterEntityId() {
		
		return this._teleporterEntityId;
	}
	
	
	@Override
	 public void fromBytes(ByteBuf buffer) {
	 
		 this._teleporterEntityId = buffer.readInt();
	 }

	 
	 @Override
	 public void toBytes(ByteBuf buffer) {
	
		 buffer.writeInt(this._teleporterEntityId);
	 }
}
