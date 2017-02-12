package superdopesquad.superdopejedimod.entity;


import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.player.EntityPlayer;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class EntityAIAttackMeleeFactionAware extends EntityAIAttackMelee {

	
	private FactionInfo[] _factionsToAttack;
	
	
	public EntityAIAttackMeleeFactionAware(EntityCreature creature, double speedIn, boolean useLongMemory, FactionInfo[] factionsToAttack) {
		
		super(creature, speedIn, useLongMemory);	
		
		this._factionsToAttack = factionsToAttack;
	}
	
	
	@Override
	public boolean shouldExecute() {
	
		EntityLivingBase entity = this.attacker.getAttackTarget();
		if (entity == null) {
			return false;
		}
		
		String attackTargetName = entity.getName();
		
		System.out.println("Inside shouldExecute(), contemplating attacking " + attackTargetName );
		
		if (entity instanceof EntityPlayer) {
			
			String factionName = SuperDopeJediMod.factionManager.getPlayerFactionName((EntityPlayer)entity);
			System.out.println("Target is of faction " + factionName );
			
			
		}
			
		return super.shouldExecute();
	}

}
