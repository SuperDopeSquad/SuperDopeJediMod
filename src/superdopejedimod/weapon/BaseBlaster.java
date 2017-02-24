package superdopesquad.superdopejedimod.weapon;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.faction.*;


public abstract class BaseBlaster  extends BaseRangedWeapon implements SuperDopeObject, ClassAwareInterface {

	
	public BaseBlaster(String name) {
		
		super(name);
	}
	
		
	@Override
	public void registerRecipe() {
		return;
	}


	@Override
	public List<ClassInfo> GetUnfriendlyClasses() {
	
		// By default, we do not let force-wielding classes use blasters.
		return SuperDopeJediMod.classManager.getForceWieldingClasses();
	}


	@Override
	public boolean IsUseUnfriendlyBanned() {
	
		// By default, all blasters are banned from the unfriendly classes, namely, the force-wielding classes.
		return true;
	}
}
