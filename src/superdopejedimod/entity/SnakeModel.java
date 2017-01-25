package superdopesquad.superdopejedimod.entity;


import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class SnakeModel extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer tongue;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer body4;
    public ModelRenderer body5;
    public ModelRenderer body6;
    public ModelRenderer body7;
    public ModelRenderer body8;
    public ModelRenderer body9;
    
    public int textureWidth = 64;
    public int textureHeight = 32;

    // create an animation cycle
    // for movement based animations you need to measure distance moved
    // and perform number of cycles per block distance moved.
    protected double distanceMovedTotal = 0.0D;
    // don't make this too large or animations will be skipped

    protected static final double CYCLES_PER_BLOCK = 3.0D; 
    protected int cycleIndex = 0;
    protected float[][] undulationCycle = new float[][]
    {
        { 45F, -45F, -45F, 0F, 45F, 45F, 0F, -45F },
        { 0F, 45F, -45F, -45F, 0F, 45F, 45F, 0F },
        { -45F, 90F, 0F, -45F, -45F, 0F, 45F, 45F },
        { -45F, 45F, 45F, 0F, -45F, -45F, 0F, 45F },
        { 0F, -45F, 45F, 45F, 0F, -45F, -45F, 0F },
        { 45F, -90F, 0F, 45F, 45F, 0F, -45F, -45F },
    };
    
    
    public SnakeModel()
    {   
        
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2.5F, -1F, -5F, 5, 2, 5);
        head.setRotationPoint(0F, 23F, -8F);
        head.setTextureSize(textureWidth, textureHeight);
        setRotation(head, 0F, 0F, 0F);
        tongue = new ModelRenderer(this, 0, 13);
        tongue.addBox(-0.5F, -0.5F, -10F, 1, 1, 5);
        tongue.setRotationPoint(0F, 23F, -8F);
        tongue.setTextureSize(textureWidth, textureHeight);
        setRotation(tongue, 0F, 0F, 0F);
        body1 = new ModelRenderer(this, 20, 20);
        body1.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body1.setRotationPoint(0F, 23F, -8F);
        body1.setTextureSize(textureWidth, textureHeight);
        setRotation(body1, 0F, 0F, 0F);
        body2 = new ModelRenderer(this, 20, 20);
        body2.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body2.setRotationPoint(0F, 0F, 4F);
        body2.setTextureSize(textureWidth, textureHeight);
        body1.addChild(body2);
        setRotation(body2, 0F, undulationCycle[0][0], 0F);
        body3 = new ModelRenderer(this, 20, 20);
        body3.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body3.setRotationPoint(0F, 0F, 4F);
        body3.setTextureSize(textureWidth, textureHeight);
        setRotation(body3, 0F, undulationCycle[0][1], 0F);
        body2.addChild(body3);
        body4 = new ModelRenderer(this, 20, 20);
        body4.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body4.setRotationPoint(0F, 0F, 4F);
        body4.setTextureSize(textureWidth, textureHeight);
        setRotation(body4, 0F, undulationCycle[0][2], 0F);
        body3.addChild(body4);
        body5 = new ModelRenderer(this, 20, 20);
        body5.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body5.setRotationPoint(0F, 0F, 4F);
        body5.setTextureSize(textureWidth, textureHeight);
        setRotation(body5, 0F, undulationCycle[0][3], 0F);
        body4.addChild(body5);
        body6 = new ModelRenderer(this, 20, 20);
        body6.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body6.setRotationPoint(0F, 0F, 4F);
        body6.setTextureSize(textureWidth, textureHeight);
        setRotation(body6, 0F, undulationCycle[0][4], 0F);
        body5.addChild(body6);
        body7 = new ModelRenderer(this, 30, 0);
        body7.addBox(-1F, -1F, -1F, 2, 2, 5);
        body7.setRotationPoint(0F, 0F, 4F);
        body7.setTextureSize(textureWidth, textureHeight);
        setRotation(body7, 0F, undulationCycle[0][5], 0F);
        body6.addChild(body7);
        body8 = new ModelRenderer(this, 30, 0);
        body8.addBox(-1F, -1F, -1F, 2, 2, 5);
        body8.setRotationPoint(0F, 0F, 4F);
        body8.setTextureSize(textureWidth, textureHeight);
        setRotation(body8, 0F, undulationCycle[0][6], 0F);
        body7.addChild(body8);
        body9 = new ModelRenderer(this, 22, 12);
        body9.addBox(-0.5F, -0.5F, -1F, 1, 1, 5);
        body9.setRotationPoint(0F, 0F, 4F);
        body9.setTextureSize(textureWidth, textureHeight);
        setRotation(body9, 0F, undulationCycle[0][7], 0F);
        body8.addChild(body9);

    }    

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity parEntity, float parTime, float parSwingSuppress, 
          float par4, float parHeadAngleY, float parHeadAngleX, float par7)
    {
        // best to cast to actual expected entity, to allow access to custom fields 
        // related to animation
        renderSnake((SnakeEntity) parEntity, parTime, parSwingSuppress, par4, 
              parHeadAngleY, parHeadAngleX, par7);
    }
    
    
    public void renderSnake(SnakeEntity parEntity, float parTime, float parSwingSuppress, 
          float par4, float parHeadAngleY, float parHeadAngleX, float par7)
    {
        setRotationAngles(parTime, parSwingSuppress, par4, parHeadAngleY, parHeadAngleX, 
              par7, parEntity);

        // scale the whole thing for big or small entities
        GL11.glPushMatrix();
       // GL11.glScalef(parEntity.getScaleFactor(), parEntity.getScaleFactor(), parEntity.getScaleFactor());

        if (this.isChild)
        {
            float childScaleFactor = 0.5F;
            GL11.glPushMatrix();
            GL11.glScalef(1.0F * childScaleFactor, 1.0F * childScaleFactor, 1.0F 
                  * childScaleFactor);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            head.render(par7);
            // flick tongue occasionally

            if (parEntity.ticksExisted%60==0 && parSwingSuppress <= 0.1F) {tongue.render(par7);} 
            body1.render(par7); // all rest of body are children of body1
            GL11.glPopMatrix();
        }
        else
        {
            head.render(par7);
            // flick tongue occasionally

            if (parEntity.ticksExisted%60==0 && parSwingSuppress <= 0.1F) {tongue.render(par7);} 
            body1.render(par7); // all rest of body are children of body1
        }

        // don't forget to pop the matrix for overall scaling
        GL11.glPopMatrix();
    }

    
    @Override
    public void setRotationAngles(float parTime, float parSwingSuppress, float par3, 
          float parHeadAngleY, float parHeadAngleX, float par6, Entity parEntity)
    {
        // animate if moving        
        updateDistanceMovedTotal(parEntity);
        cycleIndex = (int) ((getDistanceMovedTotal(parEntity)*CYCLES_PER_BLOCK)
              %undulationCycle.length);
        // DEBUG
        //System.out.println("ModelSerpent setRotationAngles(), distanceMoved ="
        //      +getDistanceMovedTotal(parEntity)+", cycleIndex ="+cycleIndex);
        body2.rotateAngleY = degToRad(undulationCycle[cycleIndex][0]) ;
        body3.rotateAngleY = degToRad(undulationCycle[cycleIndex][1]) ;
        body4.rotateAngleY = degToRad(undulationCycle[cycleIndex][2]) ;
        body5.rotateAngleY = degToRad(undulationCycle[cycleIndex][3]) ;
        body6.rotateAngleY = degToRad(undulationCycle[cycleIndex][4]) ;
        body7.rotateAngleY = degToRad(undulationCycle[cycleIndex][5]) ;
        body8.rotateAngleY = degToRad(undulationCycle[cycleIndex][6]) ;
        body9.rotateAngleY = degToRad(undulationCycle[cycleIndex][7]) ;  
    }
    
    protected void updateDistanceMovedTotal(Entity parEntity) 
    {
        distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, 
              parEntity.prevPosZ);
    }
    
    protected double getDistanceMovedTotal(Entity parEntity) 
    {
        return (distanceMovedTotal);
    }

    protected float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
    
    protected void setRotation(ModelRenderer model, float rotX, float rotY, float rotZ)
    {
        model.rotateAngleX = degToRad(rotX);
        model.rotateAngleY = degToRad(rotY);
        model.rotateAngleZ = degToRad(rotZ);        
    }

    // spin methods are good for testing and debug rotation points and offsets in the model
    protected void spinX(ModelRenderer model)
    {
        model.rotateAngleX += degToRad(0.5F);
    }
    
    protected void spinY(ModelRenderer model)
    {
        model.rotateAngleY += degToRad(0.5F);
    }
    
    protected void spinZ(ModelRenderer model)
    {
        model.rotateAngleZ += degToRad(0.5F);
    }
}