package superdopesquad.superdopejedimod.tools;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class Drill extends BaseTool {

	public Drill(String unlocalizedName) {
		
		super(unlocalizedName, SuperDopeJediMod.drillMaterial);
	
		this.setMaxStackSize(1);
	
		this.setCreativeTab(CreativeTabs.TOOLS);		
	}


	
	public void registerRecipe() {
		
		// Recipe for creating an Generator.
			ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
			ItemStack diamondStack = new ItemStack(Items.DIAMOND);
			ItemStack obsidianStack = new ItemStack(Blocks.OBSIDIAN);
		
			GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
					"BAB", 
					"BAB", 
					"CAC", 
					'A', ironIngotStack, 
					'B', obsidianStack, 
					'C', diamondStack);
					
				
			}

	
	
}