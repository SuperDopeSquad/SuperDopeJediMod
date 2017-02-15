//package superdopesquad.superdopejedimod.entity;
//
//
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.util.ResourceLocation;[/p]
//
//		
//public abstract class LayerRendererFactionIndicatorBase implements LayerRenderer {
// 
//	protected T field_177186_d;
//	//private final RendererLivingEntity<?> renderer;
//	private final RenderLiving<?> renderer;
//	private float alpha = 1.0F;
//	private float colorR = 1.0F;
//	private float colorG = 1.0F;
//	private float colorB = 1.0F;
// 
//	
// public LayerRendererFactionIndicatorBase(RenderLiving<?> rendererIn) {
// this.renderer = rendererIn;
// this.initBackpack();
// }
// 
// public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
// renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);
// }
// 
// private void renderLayer(EntityLivingBase entitylivingbaseIn, float f1, float f2, float partialTicks, float p_177182_5_, float p_177182_6_, float p_177182_7_, float scale) {
// T t = this.field_177186_d;
// t.setModelAttributes(this.renderer.getMainModel());
// t.setLivingAnimations(entitylivingbaseIn, f1, f2, partialTicks);
// t = (T)new ModelMediumBackpack(6.0F);
// this.setShownLayer(t);
// 
// this.renderer.bindTexture(new ResourceLocation(ModReference.MOD_RL + "/models/backpack/MediumBackpack.png"));
// 
// GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
// t.render(entitylivingbaseIn, f1, f2, p_177182_5_, p_177182_6_, p_177182_7_, scale);
// }
// public boolean shouldCombineTextures() {
// return false;
// }
// 
// public abstract void initBackpack();
// protected abstract void setShownLayer(T model);
//}
