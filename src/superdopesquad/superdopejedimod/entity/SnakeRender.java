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
public class SnakeRender extends BaseRenderLiving {

	//protected ResourceLocation resourceLocationTexture;
	
	
	public SnakeRender(RenderManager renderManager, ModelBase par1ModelBase, float parShadowSize) {
	
        super(renderManager, par1ModelBase, parShadowSize, "snake");
        //setEntityTexture();        
    }
 
	
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        //preRenderCallbackSnake((SnakeEntity) entity, f);
    }

    
//    protected void preRenderCallbackSnake(SnakeEntity entity, float f)
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
//        resourceLocationTexture = new ResourceLocation(SuperDopeJediMod.MODID+":textures/entity/serpents/python.png");
//    }

    
//    /**
//    * Returns the location of an entity's texture. Doesn't seem to be called 
//    * unless you call Render.bindEntityTexture.
//    */
//	@Override
//	protected ResourceLocation getEntityTexture(Entity entity) {
//	
//		return this.resourceLocationTexture;
//	}
}