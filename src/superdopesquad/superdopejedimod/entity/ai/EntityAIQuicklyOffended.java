package superdopesquad.superdopejedimod.entity.ai;

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
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class EntityAIQuicklyOffended extends EntityAITarget {
	
	/* Configurable properties. */
    private final boolean 					entityCallsForHelp;
    private final @Nullable FactionInfo 	factionToTurnTheCheek;
    
    /* State machine while we are actively attacking. */
    private int revengeTimerOld; 			// Store the previous revengeTimer timestamp

    
    /**
     * This AI module is meant to be installed in the "targetTasks" of an entity. It simply identifies a potential target, and if so, sets the target by
     * calling setAttackTarget() on the entity that is executing this AI. How to attack the target is handled by a seperate AI module; here, we just
     * identify the target.
     */
    public EntityAIQuicklyOffended(EntityCreature creatureIn, boolean entityCallsForHelpIn, boolean checkSight, 
    		@Nullable FactionInfo factionToTurnTheCheekIn) {
        super(creatureIn, checkSight);
        this.entityCallsForHelp = entityCallsForHelpIn;
        this.factionToTurnTheCheek = factionToTurnTheCheekIn;
        
        // TODO: figure out what this does
        this.setMutexBits(1);
    }

    
    /**
     * Called only before execution.
     */
    @Override
    public boolean shouldExecute() {
    	// Does the entity still hold a grudge against someone who recently hit him?
    	EntityLivingBase revengeTarget = this.taskOwner.getAttackTarget();
    	if (revengeTarget == null) {
    		return false;
    	}
    	
    	// Don't be a cannibal. Don't re-attack anyone of the same class. NOTE: added this so when ProbeDroids that are
    	// all attacking the same enemy accidentily hit each other, they don't descend into an all-all probe versus probe fight.
    	if (revengeTarget.getClass() == this.taskOwner.getClass()) {
    		return false;
    	}
    	
    	// The revenge timer is the timestamp of the last attack on us. We only process this once per attack, so if we already
    	// processed this attack, lets move on. Also, if we got hit more than 20 ticks ago, forget about it.
        int revengeTick = this.taskOwner.getRevengeTimer();
        if ((this.taskOwner.ticksExisted - revengeTick) > 20) {
        	return false;
        }
        
        // Make sure the potential target is legal to attack.
        if (!this.shouldAttack(revengeTarget)) {
        	return false;
        }
        
        //System.out.println("EntityAIQuicklyOffended: engaging QuicklyOffended with revenge=" + (this.taskOwner.ticksExisted - revengeTick));
        return true;
    }

    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
    	
    	// Officially set a target in the entity, which will allow other tasks to jump in and do something to the target.
        this.taskOwner.setAttackTarget(this.taskOwner.getAttackTarget());
        
        // Set state machine for when we are executing. 
        this.target = this.taskOwner.getAttackTarget();
        this.unseenMemoryTicks = 300;
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
        
        if (this.entityCallsForHelp) {
            this.alertOthers();
        }

        //System.out.println("EntityAIQuicklyOffended: starting QuicklyOffended with this=" + this + ", taskOwner=" + this.taskOwner + ", target="+ this.target);
        super.startExecuting();
    }
    
    /** 
     * Called during execution.
     */
    @Override
    public boolean shouldContinueExecuting() {
    	EntityLivingBase revengeTarget = this.taskOwner.getAttackTarget();
    	if (revengeTarget != this.target) {
    		//System.out.println("EntityAIQuicklyOffended: continueExecuting bailing because no target.");
    		return false;
    	}
    	
    	// If its been twenty ticks since the attack, cool down and don't seek revenge anymore.
        int revengeTick = this.taskOwner.getRevengeTimer();
        if ((this.taskOwner.ticksExisted - revengeTick) > 20) {
        	//System.out.println("EntityAIQuicklyOffended: continueExecuting bailing because revengeTick=" + (this.taskOwner.ticksExisted - revengeTick));
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
    	//System.out.println("EntityAIQuicklyOffended: resetting");
    	this.target = null;
    	this.revengeTimerOld = 0;
    	
    	this.taskOwner.setRevengeTarget(null);
    	this.taskOwner.setAttackTarget(null);
    }
    
    
    /**
     * A method used to see if an entity is a suitable target through a number of checks.
     */
    public boolean shouldAttack(@Nullable EntityLivingBase target)
    {
    	EntityLiving attacker = this.taskOwner;
    	
    	// If no target, or the target is us, or if it is already dead, then forget about it!
        if ((target == null) || (target == attacker) || !target.isEntityAlive()) {
            return false;
        }
        
        // Make sure the team logic is not broken.
        if (!attacker.canAttackClass(target.getClass()) || attacker.isOnSameTeam(target) || (attacker.getClass() == target.getClass())) {
            return false;
        }
       
        // If we own this, forget it.
        if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId() != null) {
            if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId().equals(target.getUniqueID())) {
                return false;
            }

            if (target == ((IEntityOwnable)attacker).getOwner()) {
                return false;
            }
        }
        
        // If we are allowing one particular faction to pick on us, then check to see.
        if ((this.factionToTurnTheCheek != null) && (target instanceof EntityPlayer)) {
        	EntityPlayer targetPlayer = (EntityPlayer) target;
			if (SuperDopeJediMod.classManager.isPlayerInFaction(targetPlayer, this.factionToTurnTheCheek))
				return false;
		}

        // If we are forced to use vision-only, verify we are in line of sight.
        if (this.shouldCheckSight && !attacker.getEntitySenses().canSee(target)) {
        	return false;
        }
        
        // We only attack within the "home distance".
        if (!this.taskOwner.isWithinHomeDistanceFromPosition(new BlockPos(target))) {
            return false;
        }
        
        // OK, we ran out of reasons not to attack. Attack!
        return true;
    }
    
    
    /**
     * 
     */
    protected void alertOthers() {
        double d0 = this.getTargetDistance();

        for (EntityCreature entitycreature : this.taskOwner.world.getEntitiesWithinAABB(this.taskOwner.getClass(), 
        		(new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(d0, 10.0D, d0)))
        {
            if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && (!(this.taskOwner instanceof EntityTameable) || ((EntityTameable)this.taskOwner).getOwner() == ((EntityTameable)entitycreature).getOwner()) && !entitycreature.isOnSameTeam(this.taskOwner.getAttackTarget()))
            {
                entitycreature.setRevengeTarget(this.target);
            }
        }
    }
}