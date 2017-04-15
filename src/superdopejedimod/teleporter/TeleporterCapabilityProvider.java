package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;


public class TeleporterCapabilityProvider  implements ICapabilitySerializable<NBTBase> {

	
	@CapabilityInject(TeleporterCapabilityInterface.class)
	 public static final Capability<TeleporterCapabilityInterface> TeleporterCapability = null;

	 private TeleporterCapabilityInterface instance = TeleporterCapability.getDefaultInstance();

	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		return capability == TeleporterCapability;
	}
	

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		return capability == TeleporterCapability ? TeleporterCapability.<T> cast(this.instance) : null;
	}

	
	@Override
	public NBTBase serializeNBT() {

		 return TeleporterCapability.getStorage().writeNBT(TeleporterCapability, this.instance, null);
	}

	
	@Override
	public void deserializeNBT(NBTBase nbt) {

		TeleporterCapability.getStorage().readNBT(TeleporterCapability, this.instance, null, nbt);
	}
}