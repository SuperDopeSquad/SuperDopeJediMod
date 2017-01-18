package superdopesquad.superdopejedimod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


@SideOnly(Side.CLIENT)
public abstract class BaseRender extends RenderLiving {

	private ResourceLocation _resourceLocationTexture;
	
	
	public BaseRender(RenderManager renderManager, ModelBase par1ModelBase, float parShadowSize, String textureFileName) {
	
        super(renderManager, par1ModelBase, parShadowSize);
        
        //setEntityTexture();   
    	this._resourceLocationTexture = new ResourceLocation(SuperDopeJediMod.MODID + ":textures/entities/" + textureFileName + ".png");
    }
 
	
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        //preRenderCallbackTuskanRaider((TuskanRaiderEntity) entity, f);
    }

    
//    protected void preRenderCallbackTuskanRaider(TuskanRaiderEntity entity, float f)
//    {
//        // some people do some G11 transformations or blends here, like you can do
//        // GL11.glScalef(2F, 2F, 2F); to scale up the entity
//        // which is used for Slime entities.  I suggest having the entity cast to
//        // your custom type to make it easier to access fields from your 
//        // custom entity, eg. GL11.glScalef(entity.scaleFactor, entity.scaleFactor, 
//        // entity.scaleFactor); 
//    }

    
//    protected void setEntityTexture()
//    {
//        //resourceLocationTexture = new ResourceLocation(SuperDopeJediMod.MODID+":textures/entity/serpents/python.png");
//    	resourceLocationTexture = new ResourceLocation(SuperDopeJediMod.MODID + ":textures/entities/tuskanraider.png");
//    }

    
    /**
    * Returns the location of an entity's texture. Doesn't seem to be called 
    * unless you call Render.bindEntityTexture.
    */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	
		return this._resourceLocationTexture;
	}
}