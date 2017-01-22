package superdopesquad.superdopejedimod.entity;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class JawaModel extends ModelBiped {
	
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer arms;
	public ModelRenderer rightLeg;
	public ModelRenderer leftLeg;
    
	public static int textureWidth = 64;
	public static int textureHeight = 64;
	 
    
//    // create an animation cycle
//    // for movement based animations you need to measure distance moved
//    // and perform number of cycles per block distance moved.
//    protected double distanceMovedTotal = 0.0D;
//    // don't make this too large or animations will be skipped
//
//    protected static final double CYCLES_PER_BLOCK = 3.0D; 
//    protected int cycleIndex = 0;
//    protected float[][] undulationCycle = new float[][]
//    {
//        { 45F, -45F, -45F, 0F, 45F, 45F, 0F, -45F },
//        { 0F, 45F, -45F, -45F, 0F, 45F, 45F, 0F },
//        { -45F, 90F, 0F, -45F, -45F, 0F, 45F, 45F },
//        { -45F, 45F, 45F, 0F, -45F, -45F, 0F, 45F },
//        { 0F, -45F, 45F, 45F, 0F, -45F, -45F, 0F },
//        { 45F, -90F, 0F, 45F, 45F, 0F, -45F, -45F },
//    };
    
    
//    public JawaModel()
//    {   
//        
//        head = new ModelRenderer(this, 0, 0);
//        head.addBox(-2.5F, -1F, -5F, 5, 2, 5);
//        head.setRotationPoint(0F, 23F, -8F);
//        head.setTextureSize(textureWidth, textureHeight);
//        setRotation(head, 0F, 0F, 0F);
//        tongue = new ModelRenderer(this, 0, 13);
//        tongue.addBox(-0.5F, -0.5F, -10F, 1, 1, 5);
//        tongue.setRotationPoint(0F, 23F, -8F);
//        tongue.setTextureSize(textureWidth, textureHeight);
//        setRotation(tongue, 0F, 0F, 0F);
//        body1 = new ModelRenderer(this, 20, 20);
//        body1.addBox(-1.5F, -1F, -1F, 3, 2, 5);
//        body1.setRotationPoint(0F, 23F, -8F);
//        body1.setTextureSize(textureWidth, textureHeight);
//        setRotation(body1, 0F, 0F, 0F);
//        body2 = new ModelRenderer(this, 20, 20);
//        body2.addBox(-1.5F, -1F, -1F, 3, 2, 5);
//        body2.setRotationPoint(0F, 0F, 4F);
//        body2.setTextureSize(textureWidth, textureHeight);
//        body1.addChild(body2);
//        setRotation(body2, 0F, undulationCycle[0][0], 0F);
//        body3 = new ModelRenderer(this, 20, 20);
//        body3.addBox(-1.5F, -1F, -1F, 3, 2, 5);
//        body3.setRotationPoint(0F, 0F, 4F);
//        body3.setTextureSize(textureWidth, textureHeight);
//        setRotation(body3, 0F, undulationCycle[0][1], 0F);
//        body2.addChild(body3);
//        body4 = new ModelRenderer(this, 20, 20);
//        body4.addBox(-1.5F, -1F, -1F, 3, 2, 5);
//        body4.setRotationPoint(0F, 0F, 4F);
//        body4.setTextureSize(textureWidth, textureHeight);
//        setRotation(body4, 0F, undulationCycle[0][2], 0F);
//        body3.addChild(body4);
//        body5 = new ModelRenderer(this, 20, 20);
//        body5.addBox(-1.5F, -1F, -1F, 3, 2, 5);
//        body5.setRotationPoint(0F, 0F, 4F);
//        body5.setTextureSize(textureWidth, textureHeight);
//        setRotation(body5, 0F, undulationCycle[0][3], 0F);
//        body4.addChild(body5);
//        body6 = new ModelRenderer(this, 20, 20);
//        body6.addBox(-1.5F, -1F, -1F, 3, 2, 5);
//        body6.setRotationPoint(0F, 0F, 4F);
//        body6.setTextureSize(textureWidth, textureHeight);
//        setRotation(body6, 0F, undulationCycle[0][4], 0F);
//        body5.addChild(body6);
//        body7 = new ModelRenderer(this, 30, 0);
//        body7.addBox(-1F, -1F, -1F, 2, 2, 5);
//        body7.setRotationPoint(0F, 0F, 4F);
//        body7.setTextureSize(textureWidth, textureHeight);
//        setRotation(body7, 0F, undulationCycle[0][5], 0F);
//        body6.addChild(body7);
//        body8 = new ModelRenderer(this, 30, 0);
//        body8.addBox(-1F, -1F, -1F, 2, 2, 5);
//        body8.setRotationPoint(0F, 0F, 4F);
//        body8.setTextureSize(textureWidth, textureHeight);
//        setRotation(body8, 0F, undulationCycle[0][6], 0F);
//        body7.addChild(body8);
//        body9 = new ModelRenderer(this, 22, 12);
//        body9.addBox(-0.5F, -0.5F, -1F, 1, 1, 5);
//        body9.setRotationPoint(0F, 0F, 4F);
//        body9.setTextureSize(textureWidth, textureHeight);
//        setRotation(body9, 0F, undulationCycle[0][7], 0F);
//        body8.addChild(body9);
//
//    }    

    /**
     * Sets the models various rotation angles then renders the model.
     */
//    @Override
//    public void render(Entity parEntity, float parTime, float parSwingSuppress, 
//          float par4, float parHeadAngleY, float parHeadAngleX, float par7)
//    {
//        // best to cast to actual expected entity, to allow access to custom fields 
//        // related to animation
//        renderJawa((JawaEntity) parEntity, parTime, parSwingSuppress, par4, 
//              parHeadAngleY, parHeadAngleX, par7);
//    }
//    
//    
//    public void renderJawa(JawaEntity parEntity, float parTime, float parSwingSuppress, 
//          float par4, float parHeadAngleY, float parHeadAngleX, float par7)
//    {
//        setRotationAngles(parTime, parSwingSuppress, par4, parHeadAngleY, parHeadAngleX, 
//              par7, parEntity);
//
//        // scale the whole thing for big or small entities
//        GL11.glPushMatrix();
//       // GL11.glScalef(parEntity.getScaleFactor(), parEntity.getScaleFactor(), parEntity.getScaleFactor());
//
//        if (this.isChild)
//        {
//            float childScaleFactor = 0.5F;
//            GL11.glPushMatrix();
//            GL11.glScalef(1.0F * childScaleFactor, 1.0F * childScaleFactor, 1.0F 
//                  * childScaleFactor);
//            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
//            head.render(par7);
//            // flick tongue occasionally
//
//            if (parEntity.ticksExisted%60==0 && parSwingSuppress <= 0.1F) {tongue.render(par7);} 
//            body1.render(par7); // all rest of body are children of body1
//            GL11.glPopMatrix();
//        }
//        else
//        {
//            head.render(par7);
//            // flick tongue occasionally
//
//            if (parEntity.ticksExisted%60==0 && parSwingSuppress <= 0.1F) {tongue.render(par7);} 
//            body1.render(par7); // all rest of body are children of body1
//        }
//
//        // don't forget to pop the matrix for overall scaling
//        GL11.glPopMatrix();
//    }

    
//    @Override
//    public void setRotationAngles(float parTime, float parSwingSuppress, float par3, 
//          float parHeadAngleY, float parHeadAngleX, float par6, Entity parEntity)
//    {
//        // animate if moving        
//        updateDistanceMovedTotal(parEntity);
//        cycleIndex = (int) ((getDistanceMovedTotal(parEntity)*CYCLES_PER_BLOCK)
//              %undulationCycle.length);
//        // DEBUG
//        //System.out.println("ModelSerpent setRotationAngles(), distanceMoved ="
//        //      +getDistanceMovedTotal(parEntity)+", cycleIndex ="+cycleIndex);
//        body2.rotateAngleY = degToRad(undulationCycle[cycleIndex][0]) ;
//        body3.rotateAngleY = degToRad(undulationCycle[cycleIndex][1]) ;
//        body4.rotateAngleY = degToRad(undulationCycle[cycleIndex][2]) ;
//        body5.rotateAngleY = degToRad(undulationCycle[cycleIndex][3]) ;
//        body6.rotateAngleY = degToRad(undulationCycle[cycleIndex][4]) ;
//        body7.rotateAngleY = degToRad(undulationCycle[cycleIndex][5]) ;
//        body8.rotateAngleY = degToRad(undulationCycle[cycleIndex][6]) ;
//        body9.rotateAngleY = degToRad(undulationCycle[cycleIndex][7]) ;  
//    }
    
//    protected void updateDistanceMovedTotal(Entity parEntity) 
//    {
//        distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, 
//              parEntity.prevPosZ);
//    }
//    
//    protected double getDistanceMovedTotal(Entity parEntity) 
//    {
//        return (distanceMovedTotal);
//    }
//
//    protected float degToRad(float degrees)
//    {
//        return degrees * (float)Math.PI / 180 ;
//    }
//    
//    protected void setRotation(ModelRenderer model, float rotX, float rotY, float rotZ)
//    {
//        model.rotateAngleX = degToRad(rotX);
//        model.rotateAngleY = degToRad(rotY);
//        model.rotateAngleZ = degToRad(rotZ);        
//    }

//    // spin methods are good for testing and debug rotation points and offsets in the model
//    protected void spinX(ModelRenderer model)
//    {
//        model.rotateAngleX += degToRad(0.5F);
//    }
//    
//    protected void spinY(ModelRenderer model)
//    {
//        model.rotateAngleY += degToRad(0.5F);
//    }
//    
//    protected void spinZ(ModelRenderer model)
//    {
//        model.rotateAngleZ += degToRad(0.5F);
//    }
    
    
    public JawaModel() {   
    	
    	this(1.0F);
    }    

    
    public JawaModel(float scale) {
        
    	this(scale, 0.0F, textureWidth, textureHeight);
    }

    
    public JawaModel(float scale, float p_i1164_2_, int width, int height) {
    	
    	float yOffsetHeadSpike = -10.0F;
    	int textureOffsetSpikeX = 0;
    	int textureOffsetSpikeY = 32;
    	
    	// Debug info.
    	System.out.println("DEBUG width:" + String.valueOf(width) + ", height:" + String.valueOf(height) + ", p_i1164_2_:" + String.valueOf(p_i1164_2_) + ", scale:" + String.valueOf(scale));
    	
        this.head = (new ModelRenderer(this)).setTextureSize(width, height);
        this.head.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        
        // x: left (negative) and right (positive)
        // y: up (negative) and down (positive)
        // z: front (negative) and back (positive)
        // width: left to right distance
        // height: up and down distance
        // depth: front to back distance
        //public void addBox(float offX, float offY, float offZ, int width, int height, int depth, float scaleFactor)
        // public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn)
        
//        this.spikeForeheadLeft = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.spikeForeheadLeft.setRotationPoint(0.0F, p_i1164_2_, 0.0F);     
//        this.spikeForeheadLeft.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(-3.5F, yOffsetHeadSpike, -3.5F, 1, 1, 1, scale);
//        this.head.addChild(this.spikeForeheadLeft);
//        
//        this.spikeForeheadRight = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.spikeForeheadRight.setRotationPoint(0.0F, p_i1164_2_, 0.0F);
//        this.spikeForeheadRight.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(2.5F, yOffsetHeadSpike, -3.5F, 1, 1, 1, scale);
//        this.head.addChild(this.spikeForeheadRight);
//                
//        this.spikeBackheadLeft = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.spikeBackheadLeft.setRotationPoint(0.0F, p_i1164_2_, 0.0F);     
//        this.spikeBackheadLeft.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(-3.5F, yOffsetHeadSpike, 2.50F, 1, 1, 1, scale);
//        this.head.addChild(this.spikeBackheadLeft);
//        
//        this.spikeBackheadRight = (new ModelRenderer(this)).setTextureSize(width, height);
//        this.spikeBackheadRight.setRotationPoint(0.0F, p_i1164_2_, 0.0F);
//        this.spikeBackheadRight.setTextureOffset(textureOffsetSpikeX, textureOffsetSpikeY).addBox(2.5F, yOffsetHeadSpike, 2.50F, 1, 1, 1, scale);
//        this.head.addChild(this.spikeBackheadRight);
//        
     
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
        
        	
        this.body = (new ModelRenderer(this)).setTextureSize(width, height);
        this.body.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
        this.body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 4, scale);
        //this.villagerBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, scale + 0.5F);
     
        this.rightLeg = (new ModelRenderer(this, 0, 16)).setTextureSize(width, height);
        this.rightLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        this.leftLeg = (new ModelRenderer(this, 0, 16)).setTextureSize(width, height);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);

        this.arms = (new ModelRenderer(this)).setTextureSize(width, height);
    	this.arms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
     	this.arms.setTextureOffset(40, 16).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	this.arms.setTextureOffset(40, 16).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	//this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	//this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale);
    	//this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, scale);
    }

    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.head.render(scale);
        this.body.render(scale);
        this.rightLeg.render(scale);
        this.leftLeg.render(scale);
        this.arms.render(scale);
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
        
        this.arms.rotationPointY = 3.0F;
    	this.arms.rotationPointZ = -1.0F;
    	this.arms.rotateAngleX = -0.75F;

        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
    }
}
