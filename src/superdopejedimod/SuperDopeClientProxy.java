package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;


public class SuperDopeClientProxy extends SuperDopeCommonProxy {

	
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        
		super.preInit(e);
    }

	
	@Override
	public void init(FMLInitializationEvent e){
	    
		super.init(e);
	      
    	// Iterate through all our custom objects, and see if we have any models to render.
        for (SuperDopeObject superDopeObject : SuperDopeJediMod.customObjects) {
        	superDopeObject.registerModel();
        }
	}

	
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
    
    @Override
	public void credit_displayCreditGui(int stackSize) {
    	
    	Minecraft.getMinecraft().displayGuiScreen(new CreditGUI(stackSize));
	}
}


