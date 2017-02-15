package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.entity.LayerFactionCape;
import superdopesquad.superdopejedimod.entity.LayerFactionIndicator;
import superdopesquad.superdopejedimod.entity.SuperDopeEntity;
import superdopesquad.superdopejedimod.faction.FactionGUI;


public class SuperDopeClientProxy extends SuperDopeCommonProxy {

	
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        
		super.preInit(e);
		
		// Iterate through all our custom objects, and see if we have any entity models to render.
    	// We only want to call this method for instances of SuperDopeEntity.
        for (SuperDopeObject superDopeObject : SuperDopeJediMod.customObjects) {
        	if(superDopeObject instanceof SuperDopeEntity) {
        		((SuperDopeEntity)superDopeObject).registerEntityRender();
            }
        }
    }

	
	@Override
	public void init(FMLInitializationEvent e){
	    
		super.init(e);
	      
    	// Iterate through all our custom objects, and see if we have any models to render.
        for (SuperDopeObject superDopeObject : SuperDopeJediMod.customObjects) {
        	superDopeObject.registerModel();
        }
        
        // Should factions come with capes?  I think so!
        // For now, also including the more experimental FactionIndictor.
        
        // This sets our additional layers on the "default" player model, also known as Steve.
        RenderPlayer renderPlayerDefault = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default");
        renderPlayerDefault.addLayer(new LayerFactionIndicator(renderPlayerDefault));
        renderPlayerDefault.addLayer(new LayerFactionCape(renderPlayerDefault));
        
        // This sets our additional layers on the "slim" player model, also known as Alex.
        RenderPlayer renderPlayerSlim = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim");
        renderPlayerSlim.addLayer(new LayerFactionIndicator(renderPlayerSlim));
        renderPlayerSlim.addLayer(new LayerFactionCape(renderPlayerSlim));
          
        
        //Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(new LayerFactionIndictator(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")));
        // Where SKINTYPE can be “default” for Steve model, and “slim” for the Alex model.
	}

	
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
    
    @Override
	public void displayCreditGui(int stackSize) {
    	
    	Minecraft.getMinecraft().displayGuiScreen(new CreditGUI(stackSize));
	}
    
    
    @Override
    public void displayFactionGui(EntityPlayer player) {
    	
    	Minecraft.getMinecraft().displayGuiScreen(new FactionGUI(player));
    }
}


