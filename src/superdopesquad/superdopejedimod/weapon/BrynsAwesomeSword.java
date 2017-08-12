package superdopesquad.superdopejedimod.weapon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class BrynsAwesomeSword extends BaseMeleeWeapon{

	public BrynsAwesomeSword(String unlocalizedName) {
	
		super(unlocalizedName, SuperDopeJediMod.brynsAwesomeSwordMaterial);
	
		this.setMaxStackSize(1);
	
		this.setCreativeTab(CreativeTabs.COMBAT);		
	}
	

	public void registerRecipe() {
	
			// Recipe for creating a BrynsAwesomeSword.
			ItemStack rubyStack = new ItemStack(SuperDopeJediMod.ruby);
    		ItemStack electricFluxIngotStack = new ItemStack(SuperDopeJediMod.electricFluxIngot);
    		ItemStack stickStack = new ItemStack(Items.STICK);
    		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this), "x", "y", "z", 'x', rubyStack, 'z', stickStack, 'y', electricFluxIngotStack);
		}
		
	}
		


