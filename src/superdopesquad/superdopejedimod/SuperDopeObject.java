package superdopesquad.superdopejedimod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public interface SuperDopeObject {

	//public void registerObject();
	public void registerBlocks(RegistryEvent.Register<Block> event);
    public void registerItems(RegistryEvent.Register<Item> event);
	public void registerModel();
	public void registerRecipe();
}
