package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class BaseEntity extends EntityAnimal implements SuperDopeObject {

	protected String name = "";
	//protected Object mod = null;
	
	
	public BaseEntity(World worldIn) {
		super(worldIn);
		
		//this.mod = modIn;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getName() { 
		return name; 
	}
	
	
	public void registerObject() {
	
		//EntityRegistry.registerModEntity(this, this.name, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);	
		//EntityRegistry.registerModEntity(BaseMob.class, this.name, 66, this.mod, 80, 3, false);
		
		// Register the block with the game.
		//RenderingRegistry.instance().registerentityRenderingHandler();
		
		//GameRegistry.registerBlock(this, name);
	}
	
	
	public void registerRecipe() {
		return;
	}
	
	
	public void registerModel() {
	    
		//RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    //renderItem.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseBlock) this).getName(), "inventory"));
	}
  
	
	public void generateEnd(World world, Random random, int i, int j) {
		return;
	}
	
	
	public void generateSurface(World world, Random random, int i, int j) {
		return;
	}
	
	
	public void generateNether(World world, Random random, int i, int j) {
		return;
	}
    
}
