package superdopesquad.superdopejedimod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

// When we upgraded to forge version 1.12 - 14.21.1.2387, we replace "registerObject" with 
// two new methods: registerBlocks and registerItems.
public interface SuperDopeObject {

	// Must be implemented.
	public String getName();
	public String getFullName();
	
	// The following methods by default do nothing; override if you have something to register.
	default public void registerBlocks(RegistryEvent.Register<Block> event) {
	}
	
    default public void registerItems(RegistryEvent.Register<Item> event) {
    }
    
	default public void registerModel() {
	}
	
	default public void registerRecipe() {
	}
	
	default public void blockBreakEvent(BreakEvent e) {
		//System.out.println("Inside BaseBlock:blockBreakEvent");
		return;
	}
}
