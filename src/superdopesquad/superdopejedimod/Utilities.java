package superdopesquad.superdopejedimod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;


public class Utilities {

	
	public static ResourceLocation GetRegistryNameRecycler(Item item) {
		
	  	return Utilities.GetRegistryNameModified(item, "_recycle");
	} 
	
	
	public static ResourceLocation GetRegistryNameRecycler(Block block) {
		
	  	return Utilities.GetRegistryNameModified(block, "_recycle");
	} 
	
	
	public static ResourceLocation GetRegistryNameBoots(Item item) {
		
	  	return Utilities.GetRegistryNameModified(item, "_boots");
	} 

	
	public static ResourceLocation GetRegistryNameHelmet(Item item) {
		
	  	return Utilities.GetRegistryNameModified(item, "_helmet");
	} 
	
	
	public static ResourceLocation GetRegistryNameChestplate(Item item) {
		
	  	return Utilities.GetRegistryNameModified(item, "_chestplate");
	} 
	
	
	public static ResourceLocation GetRegistryNameLeggings(Item item) {
		
	  	return Utilities.GetRegistryNameModified(item, "_leggings");
	} 
	
	
	public static ResourceLocation GetRegistryNameBackdoor(Item item) {
		
	  	return Utilities.GetRegistryNameModified(item, "_backdoor");
	} 
	
	
	public static ResourceLocation GetRegistryNameBackdoor(Block block) {
		
	  	return Utilities.GetRegistryNameModified(block, "_backdoor");
	} 
	
	
	public static ResourceLocation GetRegistryNameModified(Item item, String modifier) {
		
	  	ResourceLocation resourceLocationRecycle = new ResourceLocation(item.getRegistryName().toString() + modifier);    
	  	return resourceLocationRecycle;
	}
	
	
	private static ResourceLocation GetRegistryNameModified(Block block, String modifier) {
		
	  	ResourceLocation resourceLocationRecycle = new ResourceLocation(block.getRegistryName().toString() + modifier);    
	  	return resourceLocationRecycle;
	}
}
