package superdopesquad.superdopejedimod;


import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import superdopesquad.superdopejedimod.entity.LayerClassCape;
import superdopesquad.superdopejedimod.entity.LayerClassIndicator;
import superdopesquad.superdopejedimod.entity.SuperDopeEntity;
import superdopesquad.superdopejedimod.faction.ClassGUI;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntity;
import superdopesquad.superdopejedimod.weapon.PlasmaShotRender;


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
        
        // Should classess come with capes?  I think so!
        // For now, also including the more experimental ClassIndictor.
        
        // This sets our additional layers on the "default" player model, also known as Steve.
        RenderPlayer renderPlayerDefault = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default");
        //renderPlayerDefault.addLayer(new LayerClassIndicator(renderPlayerDefault));
        renderPlayerDefault.addLayer(new LayerClassCape(renderPlayerDefault));
        
        // This sets our additional layers on the "slim" player model, also known as Alex.
        RenderPlayer renderPlayerSlim = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim");
        //renderPlayerSlim.addLayer(new LayerClassIndicator(renderPlayerSlim));
        renderPlayerSlim.addLayer(new LayerClassCape(renderPlayerSlim));
        
        
        //
    	System.out.println("inisde PlasmaFireEntity:registerEntityRender");
    	
    	RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
    	RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    	PlasmaShotRender plasmaShotRender = new PlasmaShotRender(renderManager, SuperDopeJediMod.weaponManager.plasmaShotItemBlue, renderItem);
  
     	System.out.println((Minecraft.getMinecraft() == null));
    	System.out.println((Minecraft.getMinecraft().getRenderManager() == null));
    	System.out.println((Minecraft.getMinecraft().getRenderItem() == null));
    	System.out.println((Minecraft.getMinecraft().getRenderManager().entityRenderMap == null));
    	    	    	
    	//Render<? extends Entity> renderInstance = new R
    	//this.entityRenderMap.put(EntitySnowball.class, new RenderSnowball(this, Items.SNOWBALL, itemRendererIn));
		Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(PlasmaShotEntity.class, plasmaShotRender);
		
		Object o = Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(PlasmaShotEntity.class);
		System.out.println("DEBUG: " + o.getClass().getName());
		
		PlasmaShotEntity plasmafireentity = new PlasmaShotEntity(null);
		Object o2 = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(plasmafireentity);
		System.out.println("DEBUG: " + o2.getClass().getName());
		
		

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
    public void displayClassGui(EntityPlayer player) {
    	
    	Minecraft.getMinecraft().displayGuiScreen(new ClassGUI(player));
    }
}


