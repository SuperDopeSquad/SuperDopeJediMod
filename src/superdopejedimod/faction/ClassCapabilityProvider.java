package superdopesquad.superdopejedimod.faction;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ClassCapabilityProvider  implements ICapabilitySerializable<NBTBase> {

	
	@CapabilityInject(ClassCapabilityInterface.class)
	 public static final Capability<ClassCapabilityInterface> ClassCapability = null;

	 private ClassCapabilityInterface instance = ClassCapability.getDefaultInstance();

	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		//System.out.println("Inside hasCapability");
		return capability == ClassCapability;
	}
	

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		return capability == ClassCapability ? ClassCapability.<T> cast(this.instance) : null;
	}

	
	@Override
	public NBTBase serializeNBT() {

		 return ClassCapability.getStorage().writeNBT(ClassCapability, this.instance, null);
	}

	
	@Override
	public void deserializeNBT(NBTBase nbt) {

		ClassCapability.getStorage().readNBT(ClassCapability, this.instance, null, nbt);
	}
}