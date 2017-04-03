//package superdopesquad.superdopejedimod.teleporter;
//
//
//import java.util.Random;
//
//import net.minecraft.block.BlockPortal;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.RenderItem;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemBlock;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.registry.GameRegistry;
//import superdopesquad.superdopejedimod.SuperDopeJediMod;
//import superdopesquad.superdopejedimod.SuperDopeObject;
//
//
//public class BasePortalBlock extends BlockPortal implements SuperDopeObject {
//
//	
//	protected String name = "";
//	
//	
//	public BasePortalBlock(String nameInput) {
//		
//		// Call our super class constructor, "Block".
//		super();
//		
//		// Stash our internal name that we'll use for this block.
//		this.name = nameInput;
//		
//		// I don't know what happens if you don't call this, but it is in every tutorial :-)
//		this.setUnlocalizedName(name);
//		
//		// By default, we'll put all new blocks in the blocks tab.
//		//this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
//		
//		// Insert this object into our collection of custom blocks, so we 
//		// can send separate events to it for lifecycle management.
//		SuperDopeJediMod.customObjects.add(this);
//	}
//	
//	
//	public String getName() { 
//		return name; 
//	}
//	
//	
//	@Override
//	public void registerObject() {
//		
//		// Register the block with the game.
//		GameRegistry.register(this.setRegistryName(this.name));
//		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
//	}
//	
//	
//	@Override
//	public void registerRecipe() {
//		return;
//	}
//	
//	
//	@Override
//	public void registerModel() {
//		
////		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
////		String location = SuperDopeJediMod.MODID + ":" + ((BasePortalBlock) this).getName();
////	    renderItem.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(location));
//	}
//}
