package superdopesquad.superdopejedimod.entity;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class StormTrooperModel extends ModelBiped
{
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer arms;
	public ModelRenderer rightLeg;
	public ModelRenderer leftLeg;
	
	public ModelRenderer classEmblem;
	private static boolean _showClassEmblem = false;
    
	public static int textureWidth = 64;
	public static int textureHeight = 64;
	
    
    public StormTrooperModel() {   
    	
    	this(1.0F);
    }    

    
    public StormTrooperModel(float scale) {
        
    	this(scale, 0.0F, textureWidth, textureHeight);
    }

    
    public StormTrooperModel(float scale, float p_i1164_2_, int width, int height) {
    	
    	float yOffset = 0.0F;  // this is a grand hack, to push the model down, since at render time, i resize it.
    	
    	// Debug info.
    	//System.out.println("DEBUG width:" + String.valueOf(width) + ", height:" + String.valueOf(height) + ", p_i1164_2_:" + String.valueOf(p_i1164_2_) + ", scale:" + String.valueOf(scale));
    	
        this.head = (new ModelRenderer(this)).setTextureSize(width, height);
        this.head.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-4.0F, (-8.0F + yOffset), -4.0F, 8, 8, 8, scale);
        
        
        
        // x: left (negative) and right (positive)
        // y: up (negative) and down (positive)
        // z: front (negative) and back (positive)
        // width: left to right distance
        // height: up and down distance
        // depth: front to back distance
        //public void addBox(float offX, float offY, float offZ, int width, int height, int depth, float scaleFactor)
        // public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn)
              	
        this.body = (new ModelRenderer(this)).setTextureSize(width, height);
        this.body.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.body.setTextureOffset(16, 16).addBox(-4.0F, (0.0F + yOffset), -3.0F, 8, 12, 4, scale);
     
        this.rightLeg = (new ModelRenderer(this, 0, 16)).setTextureSize(width, height);
        this.rightLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.rightLeg.addBox(-2.0F, (0.0F + yOffset), -2.0F, 4, 12, 4, scale);
        this.leftLeg = (new ModelRenderer(this, 0, 16)).setTextureSize(width, height);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.leftLeg.addBox(-2.0F, (0.0F + yOffset), -2.0F, 4, 12, 4, scale);

        this.arms = (new ModelRenderer(this)).setTextureSize(width, height);
    	//this.arms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
       	this.arms.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
     	this.arms.setTextureOffset(40, 16).addBox(-8.0F, (-2.0F + yOffset), -2.0F, 6, 12, 4, scale);
    	this.arms.setTextureOffset(40, 16).addBox(4.0F, (-2.0F + yOffset), -2.0F, 4, 12, 4, scale);
    	
    	if (_showClassEmblem) {
    	 this.classEmblem = (new ModelRenderer(this)).setTextureSize(width, height);
         //this.factionEmblem.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
         this.classEmblem.setTextureOffset(16, 16).addBox(-4.0F, (-20.0F + yOffset), -3.0F, 2, 2, 2, scale);
    	}
    }

    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        
    	float realScale = scale * 1.0F;  
    	
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, realScale, entityIn);
        this.head.render(realScale);
        this.body.render(realScale);
        this.rightLeg.render(realScale);
        this.leftLeg.render(realScale);
        this.arms.render(realScale);
        
        if (_showClassEmblem) {
        this.classEmblem.render(realScale);
        }
    }

    
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        
        //this.arms.rotationPointY = 3.0F;
    	//this.arms.rotationPointZ = -1.0F;
    	//this.arms.rotateAngleX = -0.75F;
        this.arms.rotationPointY = 0.0F;
    	this.arms.rotationPointZ = 0.0F;
    	this.arms.rotateAngleX = 0.0F;
    	
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
    }
}
