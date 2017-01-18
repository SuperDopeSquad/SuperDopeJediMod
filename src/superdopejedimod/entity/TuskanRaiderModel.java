package superdopesquad.superdopejedimod.entity;


import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Int;


// Note: if you want to have items in your entity's hands get rendered for you,
// you should be a daughter class of ModelBiped, not ModelBase.

@SideOnly(Side.CLIENT)
public class TuskanRaiderModel extends ModelBiped
{

	  /** The head box of the VillagerModel */
    public ModelRenderer villagerHead;
    /** The body of the VillagerModel */
    public ModelRenderer villagerBody;
    /** The arms of the VillagerModel */
    public ModelRenderer villagerArms;
    /** The right leg of the VillagerModel */
    public ModelRenderer rightVillagerLeg;
    /** The left leg of the VillagerModel */
    public ModelRenderer leftVillagerLeg;
    
    //public ModelRenderer villagerNose;
    public ModelRenderer spikeForeheadLeft;
    public ModelRenderer spikeForeheadRight;
    public ModelRenderer spikeBackheadLeft;
    public ModelRenderer spikeBackheadRight;
//    public ModelRenderer spikeFaceLeft;
//    public ModelRenderer spikeFaceRight;
//    public ModelRenderer eyeLeft;
//    public ModelRenderer eyeRight;
//    public ModelRenderer mouth;

    private boolean _debugHeadOnly = true;
    
    
    public TuskanRaiderModel()
    {   
    	this(1.0F);
    	
    	//super();
    }    

    
    public TuskanRaiderModel(float scale)
    {
        this(scale, 0.0F, 64, 64);
    }

    
    public TuskanRaiderModel(float scale, float p_i1164_2_, int width, int height)
    {
    	float yOffsetHeadSpike = -10.0F;
    	int textureOffsetSpikeX = 0;
    	int textureOffsetSpikeY = 32;
    	
    	// Debug info.
    	System.out.println("DEBUG width:" + String.valueOf(width) + ", height:" + String.valueOf(height) + ", p_i1164_2_:" + String.valueOf(p_i1164_2_) + ", scale:" + String.valueOf(scale));
    	
        this.villagerHead = (new ModelRenderer(this)).setTextureSize(width, height);
        this.villagerHead.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        
        // x: left (negative) and right (positive)
        // y: up (negative) and down (positive)
        // z: front (negative) and back (positive)
        // width: left to right distance
        // height: up and down distance
        // depth: front to back distance
        //public void addBox(float offX, float offY, float offZ, int width, int height, int depth, float scaleFactor)
        // public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn)
        
        this.spikeForeheadLeft = (new ModelRenderer(this)).setTextureSize(width, height);
        this.spikeForeheadLeft.setRotationPoint(0.0F, p_i1164_2_, 0.0F);     
        this.spikeForeheadLeft.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(-3.5F, yOffsetHeadSpike, -3.5F, 1, 1, 1, scale);
        this.villagerHead.addChild(this.spikeForeheadLeft);
        
        this.spikeForeheadRight = (new ModelRenderer(this)).setTextureSize(width, height);
        this.spikeForeheadRight.setRotationPoint(0.0F, p_i1164_2_, 0.0F);
        this.spikeForeheadRight.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(2.5F, yOffsetHeadSpike, -3.5F, 1, 1, 1, scale);
        this.villagerHead.addChild(this.spikeForeheadRight);
                
        this.spikeBackheadLeft = (new ModelRenderer(this)).setTextureSize(width, height);
        this.spikeBackheadLeft.setRotationPoint(0.0F, p_i1164_2_, 0.0F);     
        this.spikeBackheadLeft.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(-3.5F, yOffsetHeadSpike, 2.50F, 1, 1, 1, scale);
        this.villagerHead.addChild(this.spikeBackheadLeft);
        
        this.spikeBackheadRight = (new ModelRenderer(this)).setTextureSize(width, height);
        this.spikeBackheadRight.setRotationPoint(0.0F, p_i1164_2_, 0.0F);
        this.spikeBackheadRight.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(2.5F, yOffsetHeadSpike, 2.50F, 1, 1, 1, scale);
        this.villagerHead.addChild(this.spikeBackheadRight);
        
     
//        // spikeFaceLeft
//        this.spikeFaceLeft = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.spikeFaceLeft.setRotationPoint(0.0F, p_i1164_2_, 0.0F);     
//        this.spikeFaceLeft.setTextureOffset(24, 0).addBox(-3.5F, yOffsetHeadSpike, -6.0F, 1, 1, 1, scale);
//        this.villagerHead.addChild(this.spikeFaceLeft);
//        
//        this.spikeFaceRight = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.spikeFaceRight.setRotationPoint(0.0F, p_i1164_2_, 0.0F);
//        this.spikeFaceRight.setTextureOffset(24, 0).addBox(2.5F, yOffsetHeadSpike, -6.0F, 1, 1, 1, scale);
//        this.villagerHead.addChild(this.spikeFaceRight);
//        
//        this.mouth = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.mouth.setRotationPoint(0.0F, p_i1164_2_, 0.0F);
//        this.mouth.setTextureOffset(24, 0).addBox(-5.0F, yOffsetHeadSpike, 2.50F, 1, 1, 1, scale);
//        this.villagerHead.addChild(this.mouth);

        
        
//        this.villagerNose = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.villagerNose.setRotationPoint(0.0F, p_i1164_2_ - 2.0F, 0.0F);
//        this.villagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, scale);
//        this.villagerHead.addChild(this.villagerNose);
        
        	this.villagerBody = (new ModelRenderer(this)).setTextureSize(width, height);
        	this.villagerBody.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        	this.villagerBody.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 4, scale);
        	//this.villagerBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, scale + 0.5F);
     
        	  // if (!_debugHeadOnly) {
        	
//        	this.villagerArms = (new ModelRenderer(this)).setTextureSize(width, height);
//        	this.villagerArms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
//        	this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
//        	this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale);
//        	this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, scale);
//        
        	// was 22
        	this.rightVillagerLeg = (new ModelRenderer(this, 0, 16)).setTextureSize(width, height);
        	this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
        	this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        	this.leftVillagerLeg = (new ModelRenderer(this, 0, 16)).setTextureSize(width, height);
        	this.leftVillagerLeg.mirror = true;
        	this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
        	this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
      //  }
        
        this.villagerArms = (new ModelRenderer(this)).setTextureSize(width, height);
    	this.villagerArms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
     	this.villagerArms.setTextureOffset(40, 16).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	this.villagerArms.setTextureOffset(40, 16).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	//this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	//this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	//this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, scale);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.villagerHead.render(scale);
        
      
        	this.villagerBody.render(scale);
        	
       // 	  if (!_debugHeadOnly) {
        	this.rightVillagerLeg.render(scale);
        	this.leftVillagerLeg.render(scale);
//        	this.villagerArms.render(scale);
    //    }
        this.villagerArms.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.villagerHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.villagerHead.rotateAngleX = headPitch * 0.017453292F;
        
     //   if (!_debugHeadOnly) {
//        	this.villagerArms.rotationPointY = 3.0F;
//        	this.villagerArms.rotationPointZ = -1.0F;
//        	this.villagerArms.rotateAngleX = -0.75F;
        	this.rightVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        	this.leftVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        	this.rightVillagerLeg.rotateAngleY = 0.0F;
        	this.leftVillagerLeg.rotateAngleY = 0.0F;
    //    }
        
        this.villagerArms.rotationPointY = 3.0F;
    	this.villagerArms.rotationPointZ = -1.0F;
    	this.villagerArms.rotateAngleX = -0.75F;
    }
}