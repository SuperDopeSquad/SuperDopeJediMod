package superdopesquad.superdopejedimod.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityAIQuicklyOffended extends EntityAITarget {
	
	/* Configurable properties. */
    private final boolean entityCallsForHelp;
    
    /* State machine while we are actively attacking. */
    private int revengeTimerOld; 			// Store the previous revengeTimer value

    
    /**
     * 
     */
    public EntityAIQuicklyOffended(EntityCreature creatureIn, boolean entityCallsForHelpIn, boolean checkSight) {
        super(creatureIn, checkSight);
        this.entityCallsForHelp = entityCallsForHelpIn;
        this.setMutexBits(1);
    }

    
    /**
     * Called both before and during execution.
     */
    @Override
    public boolean shouldExecute() {
    	// Does the entity still hold a grudge against someone who recently hit him?
    	EntityLivingBase revengeTarget = this.taskOwner.getAITarget();
    	if (revengeTarget == null) {
    		return false;
    	}
    	
    	// Don't be a cannibal.
    	if (revengeTarget.getClass() == this.taskOwner.getClass()) {
    		return false;
    	}
    	
    	// The revenge timer is the timestamp of the last attack on us. We only process this once per attack, so if we already
    	// processed this attack, lets move on. Also, if we got hit more than 20 ticks ago, forget about it.
        int revengeTick = this.taskOwner.getRevengeTimer();
        if ((this.taskOwner.ticksExisted - revengeTick) > 20) {
        	return false;
        }
        
        if (!this.shouldAttack(revengeTarget)) {
        	return false;
        }
        
        System.out.println("EntityAIQuicklyOffended: engaging QuicklyOffended with revenge=" + (this.taskOwner.ticksExisted - revengeTick));
        return true;
    }

    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
    	
    	// Officially set a target in the entity, which will allow other tasks to jump in and do something to the target.
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
        
        // Set state machine for when we are executing. 
        this.target = this.taskOwner.getAttackTarget();
        this.unseenMemoryTicks = 300;
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
        
        if (this.entityCallsForHelp) {
            this.alertOthers();
        }

        System.out.println("EntityAIQuicklyOffended: starting QuicklyOffended with this=" + this + ", taskOwner=" + this.taskOwner + ", target="+ this.target);
        super.startExecuting();
    }
    
    
    @Override
    public boolean continueExecuting() {
    	EntityLivingBase revengeTarget = this.taskOwner.getAITarget();
    	if (revengeTarget != this.target) {
    		System.out.println("EntityAIQuicklyOffended: continueExecuting bailing because no target.");
    		return false;
    	}
    	
    	// If the revenge timer has not decremented since the last time we checked, bail.
        int revengeTick = this.taskOwner.getRevengeTimer();
        if ((this.taskOwner.ticksExisted - revengeTick) > 10) {
        	System.out.println("EntityAIQuicklyOffended: continueExecuting bailing because revengeTick=" + (this.taskOwner.ticksExisted - revengeTick));
        	return false;
        }
        
        return true;
    }
    
    
    /**
     * Resets the task, called when the task manager is ending our execution.
     */
    @Override
    public void resetTask() {
    	// Clear the state machine.
    	System.out.println("EntityAIQuicklyOffended: resetting");
    	this.target = null;
    	this.revengeTimerOld = 0;
    	
    	this.taskOwner.setRevengeTarget(null);
    	this.taskOwner.setAttackTarget(null);
    }

    
    /**
     * 
     */
    protected void alertOthers() {
        double d0 = this.getTargetDistance();

        for (EntityCreature entitycreature : this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), 
        		(new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(d0, 10.0D, d0)))
        {
            if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && (!(this.taskOwner instanceof EntityTameable) || ((EntityTameable)this.taskOwner).getOwner() == ((EntityTameable)entitycreature).getOwner()) && !entitycreature.isOnSameTeam(this.taskOwner.getAITarget()))
            {
                entitycreature.setRevengeTarget(this.target);
            }
        }
    }
    
    
    /**
     * A static method used to see if an entity is a suitable target through a number of checks.
     */
    public static boolean shouldAttack(EntityLiving attacker, @Nullable EntityLivingBase target, boolean checkSight)
    {
        if ((target == null) || (target == attacker) || !target.isEntityAlive()) {
            return false;
        }
        
        if (!attacker.canAttackClass(target.getClass()) || attacker.isOnSameTeam(target) || (attacker.getClass() == target.getClass())) {
            return false;
        }
       
        if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId() != null)
        {
            if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId().equals(target.getUniqueID())) {
                return false;
            }

            if (target == ((IEntityOwnable)attacker).getOwner()) {
                return false;
            }
        }

        return !checkSight || attacker.getEntitySenses().canSee(target);
    }

    /**
     * 
     */
    protected boolean shouldAttack(@Nullable EntityLivingBase target)
    {
        if (!shouldAttack(this.taskOwner, target, this.shouldCheckSight)) {
            return false;
        }
        
        if (!this.taskOwner.isWithinHomeDistanceFromPosition(new BlockPos(target))) {
            return false;
        }

        return true;
    }
}