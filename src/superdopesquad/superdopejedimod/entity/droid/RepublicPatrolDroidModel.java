package superdopesquad.superdopejedimod.entity.droid;


import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RepublicPatrolDroidModel extends ModelBiped
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
	
    
    public RepublicPatrolDroidModel() {   
    	
    	this(1.0F);
    }    

    
    public RepublicPatrolDroidModel(float scale) {
        
    	this(scale, 0.0F, textureWidth, textureHeight);
    }

    
    public RepublicPatrolDroidModel(float scale, float p_i1164_2_, int width, int height) {
    	
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
     	this.arms.setTextureOffset(40, 16).addBox(-8.0F, (-2.0F + yOffset), -2.0F, 4, 8, 4, scale);
    	this.arms.setTextureOffset(40, 16).addBox(4.0F, (-2.0F + yOffset), -2.0F, 4, 8, 4, scale);
    	
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



//package superdopesquad.superdopejedimod.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.lwjgl.opengl.GL11;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//
//@SideOnly(Side.CLIENT)
//public class RepublicSentryDroidModel extends ModelBase {
//	
//	/* Constants */
//	public static int TEXTURE_WIDTH = 64; // The texture we are importing is a 64x64 PNG
//	public static int TEXTURE_HEIGHT = 64;
//	
//	protected enum ArmDirection {FORWARD, BACKWARD, LEFTWARD, RIGHTWARD};
//	
//	/* Instance Members  */
//	public ModelRenderer head;
//	public ModelRenderer body;
//	public List<ModelRenderer> arms = new ArrayList<ModelRenderer>();
//	public List<ModelRenderer> arms2 = new ArrayList<ModelRenderer>();
//    
//	/* Constructors */
//    public RepublicSentryDroidModel() {   
//    	ModelRenderer subpart;
//    	
//    	// head, main
//    	subpart = new ModelRenderer(this, 0, 32);
//    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
//    	subpart.addBox(-3.0F, -8.0F, -3.0F, 6, 4, 6);
//        this.head = subpart;
//        
//        // head part 2
//        subpart = new ModelRenderer(this, 24, 32);
//        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//        subpart.setRotationPoint(0.0F, 0.0F, 0.0F);     
//        subpart.addBox(-4.0F, -7.0F, -4.0F, 8, 2, 8);
//        this.head.addChild(subpart);
//        
//        // body, main
//        subpart = new ModelRenderer(this, 0, 20);
//        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//        subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        subpart.addBox(-5.0F, -4.0F, -5.0F, 10, 2, 10);
//        this.body = subpart; 
//        
//        // body part2
//        subpart = new ModelRenderer(this, 32, 42);
//        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//        subpart.setRotationPoint(0.0F, 0.0F, 0.0F); 
//        subpart.addBox(-3.0F, -2.0F, -3.0F, 6, 1, 6);
//        this.body.addChild(subpart);
//            
//        // arms
//		modelArms(this.body, -1.0F, 2.0F, false, ArmDirection.BACKWARD,  0.8F, 0);
//		modelArms(this.body, -2.0F, 1.0F, true, ArmDirection.LEFTWARD, 1.0F, 1);
//		modelArms(this.body, -2.0F, -2.0F, false, ArmDirection.FORWARD, 0.9F, 2);
//		modelArms(this.body, 0.0F, -2.0F, true, ArmDirection.FORWARD, .4F, 3);
//		modelArms(this.body, 2.0F, -1.0F, false, ArmDirection.RIGHTWARD, 0.9F, 4);
//		modelArms(this.body, 2.0F, 2.0F, true, ArmDirection.BACKWARD,  0.6F, 5); 
//    }
//
//    // CHEAT SHEET
//    // x: left (negative) and right (positive)
//    // y: up (negative) and down (positive)
//    // z: front (negative) and back (positive)
//    // width: left to right distance
//    // height: up and down distance
//    // depth: front to back distance
//    // angles are in radians!
//    
//    
//    /* */
//    private void modelArms(ModelRenderer parent, float xOffset, float zOffset, boolean longStyle, ArmDirection armDirection, float angle, int i) {
//		// hardcoded y offset, upperArms hanging right underneath the body.
//		float yOffset = -1.5F;
//		
//		// Build the upper arm.
//		int upperArmLength = 0;
//		ModelRenderer upperArm = new ModelRenderer(this);
//		upperArm.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT); 
//		if (longStyle) {
//			upperArmLength = 6;
//			upperArm.setTextureOffset(4, 53);
//		} else { 
//			upperArmLength = 4;
//			upperArm.setTextureOffset(0, 53);
//		}
//		upperArm.addBox(0.0F, 0.0F, 0.0F, 1, upperArmLength, 1);
//		upperArm.setRotationPoint(xOffset, yOffset, zOffset);  	// global space coordinate, upper arm is anchored right under the body,
//																//  with xOffset and zOffset scattered across the edge on the yOffset plane.
//			
//		// Build the lower arm.
//		ModelRenderer lowerArm = new ModelRenderer(this);
//		lowerArm.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT); 
//		lowerArm.setTextureOffset(0, 53);
//		if (longStyle ) {
//			lowerArm.setTextureOffset(4, 53);
//		} else {
//			lowerArm.setTextureOffset(0, 53);
//		}
//		lowerArm.addBox(0.0F, 0.0F, 0.0F, 1, upperArmLength, 1);
//		
//		float xCoord = 0.0F;
//		float yCoord = 0.0F;
//		float zCoord = 0.0F;
//		switch (armDirection) {		
//		case BACKWARD:
//			upperArm.rotateAngleX = angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleX));
//			zCoord = (float) (((float) upperArmLength) * Math.sin(upperArm.rotateAngleX));
//			break;
//			
//		case FORWARD:
//			upperArm.rotateAngleX = -angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleX));
//			zCoord = (float) (((float) upperArmLength) * Math.sin(upperArm.rotateAngleX));
//			break;
//	
//		case RIGHTWARD:
//			upperArm.rotateAngleZ = -angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleZ));
//			xCoord = (float) (((float) -upperArmLength) * Math.sin(upperArm.rotateAngleZ));
//			break;
//		
//		case LEFTWARD:
//			upperArm.rotateAngleZ = angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleZ));
//			xCoord = (float) (((float) -upperArmLength) * Math.sin(upperArm.rotateAngleZ));
//			break;
//		}
//		lowerArm.setRotationPoint(xOffset + xCoord, yOffset + yCoord, zOffset + zCoord); 
//			
//		this.arms.add(upperArm);
//		this.arms2.add(lowerArm);
//	}
//    
//    
//    /**
//     * Sets the models various rotation angles then renders the model.
//     */
//    @Override
//    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//        
//    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
//    	
//    	if (this.head != null) {
//    		head.render(scale);
//    	}
//        if (this.body != null) {
//        	body.render(scale);
//        }
//        for (ModelRenderer subrenderer : arms) {
//        	subrenderer.render(scale);
//        }
//        for (ModelRenderer subrenderer : arms2) {
//        	subrenderer.render(scale);
//        }
//    }
//
//    
//    /**
//     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
//     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
//     * "far" arms and legs can swing at most.
//     */
//    @Override
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//    	// We allow the head to move left and right, but not up-and-down (this probe does not have a neck)
//    	if (this.head != null) {
//    		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
//    	}					
//       
//       int i = 0;
//       for (ModelRenderer subrenderer : arms2) {
//
//    	   subrenderer.rotateAngleY = 0.0F;
//    	   subrenderer.rotateAngleX = 0.0F;
//    	   subrenderer.rotateAngleZ = 0.0F;
//    	   
//    	   // TODO: make this more random.
//    	  if (i == 0) {
//    		   subrenderer.rotateAngleX = .75F + MathHelper.cos(limbSwing /* * 0.6662F */) * 1.4F * limbSwingAmount * 1.0F;
//    	   } else if (i < 3) {
//    		   subrenderer.rotateAngleZ = MathHelper.cos(limbSwing /* * 0.6662F */) * 1.4F * limbSwingAmount * 1.0F;
//    	   } else if (i < 4) {
//    		   subrenderer.rotateAngleX = .75F + MathHelper.cos(limbSwing/* * 0.6662F*/ + (float)Math.PI) * 1.4F * limbSwingAmount * 1.0F;
//    	   } else {
//    		   subrenderer.rotateAngleZ = MathHelper.cos(limbSwing /** 0.6662F*/ + (float)Math.PI) * 1.4F * limbSwingAmount * 1.0F;
//    	   }
//
//    	   i++;
//       }
//    }
//    
//    
//    @Override
//    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {
//    	/* TODO: this should be the hook where we can move the arms even if we are not moving. */
//    }
//}


//package superdopesquad.superdopejedimod.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.lwjgl.opengl.GL11;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//
//@SideOnly(Side.CLIENT)
//public class RepublicPatrolDroidModel extends ModelBase {
//	
//	/* Constants */
//	public static int TEXTURE_WIDTH = 64; // The texture we are importing is a 64x64 PNG
//	public static int TEXTURE_HEIGHT = 64;
//	
//	protected enum ArmDirection {FORWARD, BACKWARD, LEFTWARD, RIGHTWARD};
//	
//	/* Instance Members  */
//	public ModelRenderer head;
//	public ModelRenderer body;
//	public List<ModelRenderer> arms = new ArrayList<ModelRenderer>();
//	public List<ModelRenderer> arms2 = new ArrayList<ModelRenderer>();
//    
//	/* Constructors */
//    public RepublicPatrolDroidModel() {   
//    	ModelRenderer subpart;
//    	
//    	// head, main
//    	subpart = new ModelRenderer(this, 0, 32);
//    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
//    	subpart.addBox(-3.0F, -8.0F, -3.0F, 6, 4, 6);
//        this.head = subpart;
//        
//        // head part 2
//        subpart = new ModelRenderer(this, 24, 32);
//        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//        subpart.setRotationPoint(0.0F, 0.0F, 0.0F);     
//        subpart.addBox(-4.0F, -7.0F, -4.0F, 8, 2, 8);
//        this.head.addChild(subpart);
//        
//        // body, main
//        subpart = new ModelRenderer(this, 0, 20);
//        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//        subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
//        subpart.addBox(-5.0F, -4.0F, -5.0F, 10, 2, 10);
//        this.body = subpart; 
//        
//        // body part2
//        subpart = new ModelRenderer(this, 32, 42);
//        subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
//        subpart.setRotationPoint(0.0F, 0.0F, 0.0F); 
//        subpart.addBox(-3.0F, -2.0F, -3.0F, 6, 1, 6);
//        this.body.addChild(subpart);
//            
//        // arms
//		modelArms(this.body, -1.0F, 2.0F, false, ArmDirection.BACKWARD,  0.8F, 0);
//		modelArms(this.body, -2.0F, 1.0F, true, ArmDirection.LEFTWARD, 1.0F, 1);
//		modelArms(this.body, -2.0F, -2.0F, false, ArmDirection.FORWARD, 0.9F, 2);
//		modelArms(this.body, 0.0F, -2.0F, true, ArmDirection.FORWARD, .4F, 3);
//		modelArms(this.body, 2.0F, -1.0F, false, ArmDirection.RIGHTWARD, 0.9F, 4);
//		modelArms(this.body, 2.0F, 2.0F, true, ArmDirection.BACKWARD,  0.6F, 5); 
//    }
//
//    // CHEAT SHEET
//    // x: left (negative) and right (positive)
//    // y: up (negative) and down (positive)
//    // z: front (negative) and back (positive)
//    // width: left to right distance
//    // height: up and down distance
//    // depth: front to back distance
//    // angles are in radians!
//    
//    
//    /* */
//    private void modelArms(ModelRenderer parent, float xOffset, float zOffset, boolean longStyle, ArmDirection armDirection, float angle, int i) {
//		// hardcoded y offset, upperArms hanging right underneath the body.
//		float yOffset = -1.5F;
//		
//		// Build the upper arm.
//		int upperArmLength = 0;
//		ModelRenderer upperArm = new ModelRenderer(this);
//		upperArm.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT); 
//		if (longStyle) {
//			upperArmLength = 6;
//			upperArm.setTextureOffset(4, 53);
//		} else { 
//			upperArmLength = 4;
//			upperArm.setTextureOffset(0, 53);
//		}
//		upperArm.addBox(0.0F, 0.0F, 0.0F, 1, upperArmLength, 1);
//		upperArm.setRotationPoint(xOffset, yOffset, zOffset);  	// global space coordinate, upper arm is anchored right under the body,
//																//  with xOffset and zOffset scattered across the edge on the yOffset plane.
//			
//		// Build the lower arm.
//		ModelRenderer lowerArm = new ModelRenderer(this);
//		lowerArm.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT); 
//		lowerArm.setTextureOffset(0, 53);
//		if (longStyle ) {
//			lowerArm.setTextureOffset(4, 53);
//		} else {
//			lowerArm.setTextureOffset(0, 53);
//		}
//		lowerArm.addBox(0.0F, 0.0F, 0.0F, 1, upperArmLength, 1);
//		
//		float xCoord = 0.0F;
//		float yCoord = 0.0F;
//		float zCoord = 0.0F;
//		switch (armDirection) {		
//		case BACKWARD:
//			upperArm.rotateAngleX = angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleX));
//			zCoord = (float) (((float) upperArmLength) * Math.sin(upperArm.rotateAngleX));
//			break;
//			
//		case FORWARD:
//			upperArm.rotateAngleX = -angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleX));
//			zCoord = (float) (((float) upperArmLength) * Math.sin(upperArm.rotateAngleX));
//			break;
//	
//		case RIGHTWARD:
//			upperArm.rotateAngleZ = -angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleZ));
//			xCoord = (float) (((float) -upperArmLength) * Math.sin(upperArm.rotateAngleZ));
//			break;
//		
//		case LEFTWARD:
//			upperArm.rotateAngleZ = angle;
//			yCoord = (float) (((float) upperArmLength) * Math.cos(upperArm.rotateAngleZ));
//			xCoord = (float) (((float) -upperArmLength) * Math.sin(upperArm.rotateAngleZ));
//			break;
//		}
//		lowerArm.setRotationPoint(xOffset + xCoord, yOffset + yCoord, zOffset + zCoord); 
//			
//		this.arms.add(upperArm);
//		this.arms2.add(lowerArm);
//	}
//    
//    
//    /**
//     * Sets the models various rotation angles then renders the model.
//     */
//    @Override
//    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//        
//    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
//    	
//    	if (this.head != null) {
//    		head.render(scale);
//    	}
//        if (this.body != null) {
//        	body.render(scale);
//        }
//        for (ModelRenderer subrenderer : arms) {
//        	subrenderer.render(scale);
//        }
//        for (ModelRenderer subrenderer : arms2) {
//        	subrenderer.render(scale);
//        }
//    }
//
//    
//    /**
//     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
//     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
//     * "far" arms and legs can swing at most.
//     */
//    @Override
//    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
//    {
//    	// We allow the head to move left and right, but not up-and-down (this probe does not have a neck)
//    	if (this.head != null) {
//    		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
//    	}					
//       
//       int i = 0;
//       for (ModelRenderer subrenderer : arms2) {
//
//    	   subrenderer.rotateAngleY = 0.0F;
//    	   subrenderer.rotateAngleX = 0.0F;
//    	   subrenderer.rotateAngleZ = 0.0F;
//    	   
//    	   // TODO: make this more random.
//    	  if (i == 0) {
//    		   subrenderer.rotateAngleX = .75F + MathHelper.cos(limbSwing /* * 0.6662F */) * 1.4F * limbSwingAmount * 1.0F;
//    	   } else if (i < 3) {
//    		   subrenderer.rotateAngleZ = MathHelper.cos(limbSwing /* * 0.6662F */) * 1.4F * limbSwingAmount * 1.0F;
//    	   } else if (i < 4) {
//    		   subrenderer.rotateAngleX = .75F + MathHelper.cos(limbSwing/* * 0.6662F*/ + (float)Math.PI) * 1.4F * limbSwingAmount * 1.0F;
//    	   } else {
//    		   subrenderer.rotateAngleZ = MathHelper.cos(limbSwing /** 0.6662F*/ + (float)Math.PI) * 1.4F * limbSwingAmount * 1.0F;
//    	   }
//
//    	   i++;
//       }
//    }
//    
//    
//    @Override
//    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {
//    	/* TODO: this should be the hook where we can move the arms even if we are not moving. */
//    }
//}
