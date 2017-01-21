package superdopesquad.superdopejedimod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


@SideOnly(Side.CLIENT)
public abstract class BaseRender extends RenderLiving {

	private ResourceLocation _resourceLocationTexture;
	
	
	public BaseRender(RenderManager renderManager, ModelBase modelBase, float shadowSize, String textureFileName) {
	
        super(renderManager, modelBase, shadowSize);
        
        // This line of code was stolen from RenderBiped, to help me figure out how to render items held in the entity's hand.
        // Note that your code will fail if your model is not a daughter class of ModelBiped and actually has something in-hand.
        this.addLayer(new LayerHeldItem(this));
        
        // set the location of the texture.
    	this._resourceLocationTexture = new ResourceLocation(SuperDopeJediMod.MODID + ":textures/entities/" + textureFileName + ".png");
    }
 
	
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        
    }


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	
		return this._resourceLocationTexture;
	}
}