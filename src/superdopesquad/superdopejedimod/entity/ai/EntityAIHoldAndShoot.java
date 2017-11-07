package superdopesquad.superdopejedimod.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityAIHoldAndShoot extends EntityAIBase
{
    /* Configurable properties. */
	private final EntityLiving 		entityHost; 			// The entity the AI instance has been applied to
	private final IRangedAttackMob 	entityHostAsAttacker; 	// The same entity (as a RangedAttackMob) the AI instance has been applied to.
    private final int 				attackIntervalMin; 		// The minimum time the AI has to wait before performing another ranged attack.
    private final float 			attackRadius;
    private final float 			attackRadiusSquared;
    
    /* State machine while we are actively attacking. */
    private EntityLivingBase 		attackTarget; 			// The entity we are shooting at.
    private int 					attackIntervalCounter;	// Count down to when the next attack will come.
    private int 					outOfRangeCounter;		// Count up from when the target went out of range.
    
    
    /**
     * 
     */
    public EntityAIHoldAndShoot(IRangedAttackMob attacker, int attackIntervalIn, float attackRadiusIn) {
    	
    	// Sanity check. Whoever this is applied to, must also implement the IRangedAttackMob interface.
        if (!(attacker instanceof EntityLivingBase)) {
            throw new IllegalArgumentException("EntityAIHoldAndShoot requires Mob implements RangedAttackMob");
        } 
        
    	// Save a reference to the entity we are controlling.
        this.entityHostAsAttacker = attacker;
        this.entityHost = (EntityLiving)attacker;
        
        // Set all the configurable properties.
        this.attackIntervalMin = attackIntervalIn;
        this.attackRadius = attackRadiusIn;
        this.attackRadiusSquared = attackRadiusIn * attackRadiusIn;
        
        // TODO: learn what this does.
        this.setMutexBits(3);
    }

    
    /**
     * Called repeatedly when we are not executing, to see if we should start.
     */
    @Override
    public boolean shouldExecute() {
    	// If another task has not identified a target yet, we can't do anything yet.
        EntityLivingBase favoriteTarget = this.entityHost.getAttackTarget();
        if (favoriteTarget == null) {
            return false;
        }
        
        double distSquared = this.entityHost.getDistanceSq(favoriteTarget.posX, favoriteTarget.getEntityBoundingBox().minY, favoriteTarget.posZ);
        if (distSquared > (double) this.attackRadiusSquared) {
        	return false;
        }
        
        // Save who our target is. It might change, but this AI is going to focus on the first guy to mess with us.
        //System.out.println("EntityAIHoldAndShoot: engaging with target=" + favoriteTarget);
        this.attackTarget = favoriteTarget;
        return true;
    }

    
    /**
     * Called when this task starts executing.
     */
    @Override
    public void startExecuting() {
    	// Initialize the attack state machine.
    	assert(this.attackTarget != null); // Should have been set in shouldExecute();
        this.attackIntervalCounter = 1; // next tick should provoke an attack
        this.outOfRangeCounter = 0;
    }
    
    
    /**
     * Called while already executing; we need to determine if we should continue, or bail on this task.
     * We bail once the target goes out of range.
     */
    @Override
    public boolean shouldContinueExecuting() {
    	double distSquared = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
    	boolean shouldContinue = (distSquared <= (double) this.attackRadiusSquared)
    								&& (outOfRangeCounter < 20);
    	
        //System.out.println("EntityAIHoldAndShoot: continue to attack with shouldContinue=" + shouldContinue + ", distSq=" + distSquared + 
        //		" (" + attackRadiusSquared + "), target=" + this.attackTarget + ", entityTarget=" + this.entityHost.getAttackTarget());
        return shouldContinue;
    }
    

    /**
     * Resets the task, called when the task manager is ending our execution.
     */
    @Override
    public void resetTask() {
    	// Clear the state machine.
    	//System.out.println("EntityAIHoldAndShoot: resetting");
        this.attackTarget = null;
        this.attackIntervalCounter = 0;
        this.outOfRangeCounter = 0;
    }

    /**
     * Updates the task: called every tick while we are executing.
     */
    @Override
    public void updateTask()
    {
        double distSquared = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
        boolean canSee = this.entityHost.getEntitySenses().canSee(this.attackTarget);

        // Continue pointing our head towards the target.
        this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);

        // Decrement the attackTime counter, and see if its time to shoot! 
        --this.attackIntervalCounter;
        if (this.attackIntervalCounter == 0)
        {
        	// If they are out of range or behind a tree, don't shoot. 
            if (distSquared > (double) this.attackRadiusSquared || !canSee)
            {
            	this.outOfRangeCounter++;
                return;
            }

            // Actually shoot the ranged weapon.
            float distanceRatio = MathHelper.sqrt(distSquared) / this.attackRadius; // ratio 0.0-1.0 of how far it is towards maximum distance
            distanceRatio = MathHelper.clamp(distanceRatio, 0.1F, 1.0F); // clamp it between 0.1-1.0
            this.entityHostAsAttacker.attackEntityWithRangedAttack(this.attackTarget, distanceRatio);
            
            // Calculate the counter until our next attack.
            this.attackIntervalCounter = this.attackIntervalMin;
        }
        else
        {
        	assert(this.attackIntervalCounter > 0); // should never go negative.
        }
    }
}