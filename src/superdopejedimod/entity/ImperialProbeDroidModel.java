package superdopesquad.superdopejedimod.entity;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ImperialProbeDroidModel extends ModelBase {
	
	/* Constants */
	public static int TEXTURE_WIDTH = 64; // The texture we are importing is a 64x64 PNG
	public static int TEXTURE_HEIGHT = 64;
	
	/* Instance Members  */
	public ModelRenderer head;
	public ModelRenderer body;
	public List<ModelRenderer> arms = new ArrayList<ModelRenderer>();
	public List<ModelRenderer> arms2 = new ArrayList<ModelRenderer>();
    
	/* Constructors */
    public ImperialProbeDroidModel() {   
    	ModelRenderer subpart;
    	
    	// head, main
    	subpart = new ModelRenderer(this, 0, 32);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(-3.0F, -8.0F, -3.0F, 6, 4, 6);
        this.head = subpart;
        
        // head part 2
        subpart = new ModelRenderer(this, 24, 32);
        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
        subpart.setRotationPoint(0.0F, 0.0F, 0.0F);     
        subpart.addBox(-4.0F, -7.0F, -4.0F, 8, 2, 8);
        this.head.addChild(subpart);
        
        // body, main
        subpart = new ModelRenderer(this, 0, 20);
        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
        subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
        subpart.addBox(-5.0F, -4.0F, -5.0F, 10, 2, 10);
        this.body = subpart; 
        
        // body part2
        subpart = new ModelRenderer(this, 32, 42);
        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
        subpart.setRotationPoint(0.0F, 0.0F, 0.0F); 
        subpart.addBox(-3.0F, -2.0F, -3.0F, 6, 1, 6);
        this.body.addChild(subpart);
            
        // arms
		modelArms(this.body, -1.0F, 2.0F, false, 0);
		modelArms(this.body, -2.0F, 1.0F, true, 1);
		modelArms(this.body, -2.0F, -2.0F, false, 2);
		modelArms(this.body, 0.0F, -2.0F, true, 3);
		modelArms(this.body, 2.0F, -1.0F, false, 4);
		modelArms(this.body, 2.0F, 2.0F, true, 5); 
    }

    // CHEAT SHEET
    // x: left (negative) and right (positive)
    // y: up (negative) and down (positive)
    // z: front (negative) and back (positive)
    // width: left to right distance
    // height: up and down distance
    // depth: front to back distance
    
    /* */
    private void modelArms(ModelRenderer parent, float xOffset, float zOffset, boolean longStyle, int i) {
		ModelRenderer subpart = new ModelRenderer(this);
		subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT); 
		ModelRenderer subpart2 = new ModelRenderer(this);
		subpart2.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT); 
		
		// TODO: bring back longStyle.
		/*if (longStyle) {
			subpart.setTextureOffset(4, 53);
			subpart.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
			subpart.setRotationPoint(xOffset, -1.5F, zOffset);   
		} else */ { 
			subpart.setTextureOffset(0, 53);
			subpart.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
			subpart.setRotationPoint(xOffset, -1.5F, zOffset);  
			
			subpart2.setTextureOffset(0, 53);
			subpart2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
			subpart2.setRotationPoint(xOffset, 0.5F, zOffset + 3.0F);   
			
			// TODO: this code manually positions the lower-arms to where it should attach to the 
			// upper-arm at the elbow. As an improvement, we should calculate this with sin/cos so 
			// it wouldbe possible to move the upperarm and have the lower arm move with it.
			if (i == 0) {
				subpart.rotateAngleX = 0.9F;
				subpart2.setRotationPoint(xOffset, 0.5F, zOffset + 3.0F);   
			} else if (i == 1) {
				subpart.rotateAngleZ = 0.9F;
				subpart2.setRotationPoint(xOffset - 3.0F, 0.5F, zOffset); 
			} else if (i == 2) {
				subpart.rotateAngleX = 0.5F;
				subpart2.setRotationPoint(xOffset, 1.5F, zOffset + 2.0F); 
			} else if (i == 3) {
				subpart.rotateAngleX = -1.0F;
				subpart2.setRotationPoint(xOffset, 0.5F, zOffset - 3.0F); 
			} else if (i == 4) {
				subpart.rotateAngleZ = -1.0F;
				subpart2.setRotationPoint(xOffset + 3.0F, 0.5F, zOffset); 
			} else if (i == 5) {
				subpart.rotateAngleX = -0.5F;
				subpart2.setRotationPoint(xOffset, 1.5F, zOffset - 2.0F); 
			}
		}
	
		this.arms.add(subpart);
		this.arms2.add(subpart2);
	}
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	
    	if (this.head != null) {
    		head.render(scale);
    	}
        if (this.body != null) {
        	body.render(scale);
        }
        for (ModelRenderer subrenderer : arms) {
        	subrenderer.render(scale);
        }
        for (ModelRenderer subrenderer : arms2) {
        	subrenderer.render(scale);
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
    	// We allow the head to move left and right, but not up-and-down (this probe does not have a neck)
    	if (this.head != null) {
    		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    	}
       
       int i = 0;
       for (ModelRenderer subrenderer : arms2) {

    	   subrenderer.rotateAngleY = 0.0F;
    	   subrenderer.rotateAngleX = 0.0F;
    	   subrenderer.rotateAngleZ = 0.0F;
    	   
    	   // TODO: make this more random.
    	   if (i == 0) {
    		   subrenderer.rotateAngleX = MathHelper.cos(limbSwing /* * 0.6662F */) * 1.4F * limbSwingAmount * 1.0F;
    	   } else if (i < 3) {
    		   subrenderer.rotateAngleZ = MathHelper.cos(limbSwing /* * 0.6662F */) * 1.4F * limbSwingAmount * 1.0F;
    	   } else if (i < 4) {
    		   subrenderer.rotateAngleX = MathHelper.cos(limbSwing/* * 0.6662F*/ + (float)Math.PI) * 1.4F * limbSwingAmount * 1.0F;
    	   } else {
    		   subrenderer.rotateAngleZ = MathHelper.cos(limbSwing /** 0.6662F*/ + (float)Math.PI) * 1.4F * limbSwingAmount * 1.0F;
    	   }

    	   i++;
       }
    }
    
    
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {
    	/* TODO: this should be the hook where we can move the arms even if we are not moving. */
    }
}
