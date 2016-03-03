package superdopesquad.superdopejedimod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderTuskanRaider extends RenderLiving {

	protected ResourceLocation tuskanRaiderTexture;
	
	
	public RenderTuskanRaider(RenderManager renderManager, ModelBase par1ModelBase, float parShadowSize) {
//    {
//		RenderManager renderManager = new RenderManager(null, null);
//		
        super(renderManager, par1ModelBase, parShadowSize);
//        setEntityTexture();        
    }
 
	
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderCallbackTuskanRaider((EntityTuskanRaider) entity, f);
    }

    
    protected void preRenderCallbackTuskanRaider(EntityTuskanRaider entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
        // GL11.glScalef(2F, 2F, 2F); to scale up the entity
        // which is used for Slime entities.  I suggest having the entity cast to
        // your custom type to make it easier to access fields from your 
        // custom entity, eg. GL11.glScalef(entity.scaleFactor, entity.scaleFactor, 
        // entity.scaleFactor); 
    }

    
    protected void setEntityTexture()
    {
        tuskanRaiderTexture = new ResourceLocation(SuperDopeJediMod.MODID+":textures/entity/serpents/python.png");
    }

    
    /**
    * Returns the location of an entity's texture. Doesn't seem to be called 
    * unless you call Render.bindEntityTexture.
    */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	
		return this.tuskanRaiderTexture;
	}

	
}
