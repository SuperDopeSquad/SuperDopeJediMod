package superdopesquad.superdopejedimod.faction;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FactionCapabilityProvider  implements ICapabilitySerializable<NBTBase> {

	
	@CapabilityInject(FactionCapabilityInterface.class)
	 public static final Capability<FactionCapabilityInterface> FactionCapability = null;

	 private FactionCapabilityInterface instance = FactionCapability.getDefaultInstance();

	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		//System.out.println("Inside hasCapability");
		return capability == FactionCapability;
	}
	

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		return capability == FactionCapability ? FactionCapability.<T> cast(this.instance) : null;
	}

	
	@Override
	public NBTBase serializeNBT() {

		 return FactionCapability.getStorage().writeNBT(FactionCapability, this.instance, null);
	}

	
	@Override
	public void deserializeNBT(NBTBase nbt) {

		 FactionCapability.getStorage().readNBT(FactionCapability, this.instance, null, nbt);
	}
}