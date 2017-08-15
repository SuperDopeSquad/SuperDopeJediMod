package superdopesquad.superdopejedimod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;


public interface SuperDopeObject {

	public String getName();
	public String getFullName();
	
	// When we upgraded to forge version 1.12 - 14.21.1.2387, we replace "registerObject" with 
	// two new methods: registerBlocks and registerItems.
	public void registerBlocks(RegistryEvent.Register<Block> event);
    public void registerItems(RegistryEvent.Register<Item> event);
    
	public void registerModel();
	public void registerRecipe();
}
