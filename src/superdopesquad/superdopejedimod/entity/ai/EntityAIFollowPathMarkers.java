package superdopesquad.superdopejedimod.entity.ai;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import superdopesquad.superdopejedimod.GeometryUtil;

public class EntityAIFollowPathMarkers extends EntityAIBase
{
	public enum ScanDirection {
		NONE,
		NORTH,
		NORTHEAST,
		EAST,
		SOUTHEAST,
		SOUTH,
		SOUTHWEST,
		WEST,
		NORTHWEST,
		ILLEGAL
	};
	
	protected static final int MAX_WAIT_TIME = 1000;
	protected static final float DIRECTED_SCAN_SPREAD_RATIO = 0.5F;
	protected static final boolean DebugScan = false;
	
	private final EntityLiving 		entityHost;
    private final double 			speed;
    private final int				nextScanChance;
    private final List<Block>		blockPattern;
    private final int				maxRange;
    
    private BlockPos				nextMarkerPos;
    private int						lostHomeCount;
    private int						lostHomeWait;
    private int						lostNextCount;
    private int						lostNextWait;


    /*
     * 
     */
    public EntityAIFollowPathMarkers(EntityLiving creatureIn, double speedIn, int scanChanceIn, List<Block> blockPatternIn)
    {
    	// Configurable properties.
        this.entityHost = creatureIn;
        this.speed = speedIn;
        this.nextScanChance = scanChanceIn;
        this.blockPattern = blockPatternIn;
        this.maxRange = 16;
        
        // AI execution state machine.
        this.nextMarkerPos = null;
        this.lostHomeCount = 0;
        this.lostHomeWait = 10;
        this.lostNextCount = 0;
        this.lostNextWait = 10;
        
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {    	
    	// If we are already attacking someone, don't move.
    	EntityLivingBase currTarget = this.entityHost.getAttackTarget();
    	if (currTarget != null) {
    		return false;
    	}
    	
    	// Figure out how long we should wait before continuing again.
    	int nextChance = 0;
    	if (this.lostNextCount > 0) {
    		nextChance = this.lostNextWait;
    	} else if (this.lostHomeCount > 0) {
    		nextChance = this.lostHomeWait;
    	} else {
    		nextChance = this.nextScanChance;
    	}
    	
    	// We don't know our next marker; do a random number check to see if its time to scan.
    	int thisChance = this.entityHost.getRNG().nextInt(nextChance);
		if (thisChance != 0) {
			//System.out.println("FollowPath: chance skipped=" + thisChance + "(" + nextChance + "), lostNExt=" + this.lostNextCount + ", lostHomeCount=" + this.lostHomeCount);
			return false;
		}
    	
        // OK, time to execute!
		if (DebugScan)  System.out.println("FollowPath: chance taken=" + thisChance + "(" + nextChance + "), lostNExt=" + this.lostNextCount + ", lostHomeCount=" + this.lostHomeCount);
		return true;
    }

    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
    	// Perform a scan for the next marker.
    	this.entityHost.getNavigator().clearPathEntity();
    	if (this.nextMarkerPos == null) {
    		this.nextMarkerPos = this.startUndirectedScan(this.maxRange);
    	}
    	
    	// If we found our next marker, see if we are close enough to call it cool
    	if (this.nextMarkerPos == null) {
    		// Increment lost time.
    		this.lostHomeCount++;
    		this.lostHomeWait *= 2;
    		if (this.lostHomeWait > MAX_WAIT_TIME)
    			this.lostHomeWait = MAX_WAIT_TIME;
    		
    		if (DebugScan) System.out.println("FollowPathMarkers: could not find home marker. Aborting with lostcount=" + this.lostHomeCount);
    	} else {
    		// Reset lost timer.
    		this.lostHomeCount = 0;
    		this.lostHomeWait = 10;
    		
    		double distanceToMarker = GeometryUtil.distance(this.entityHost.getPosition(), this.nextMarkerPos);
    		if (distanceToMarker > 8.0) {
    			if (DebugScan) System.out.println("FollowPathMarkers: moving to home marker, still not close enough to " + this.nextMarkerPos + ", with distance=" + distanceToMarker);
    			this.entityHost.getNavigator().setPath(this.entityHost.getNavigator().getPathToPos(this.nextMarkerPos), this.speed);
    		} else {
    			if (DebugScan) System.out.println("FollowPathMarkers: Located at home marker " + this.nextMarkerPos);
    			ScanDirection scanDirection = determineScanDirection(this.nextMarkerPos);
    			if (scanDirection == ScanDirection.ILLEGAL) {
    				if (DebugScan) System.out.println("FollowPathMarkers: home marker has illegal direction configuration. Aborting.");
    				this.nextMarkerPos = null;
    			} else if (scanDirection == ScanDirection.NONE) {
    				if (DebugScan) System.out.println("FollowPathMarkers: home marker has no direction configuration. Aborting.");
    				this.nextMarkerPos = null;
    			} else {
    				if (DebugScan) System.out.println("FollowPathMarkers: home marker has direction configuration of " + scanDirection);
    				this.nextMarkerPos = this.startDirectedScan(scanDirection, (int)(this.maxRange * 1.5), this.nextMarkerPos, this.nextMarkerPos);
    				if (this.nextMarkerPos == null) {
    					if (DebugScan) System.out.println("FollowPathMarkers: Could not find second marker in " + scanDirection + ". Aborting.");
    				}
    			}
    			
				if (this.nextMarkerPos == null) {
					// Increment lost time.
					this.lostNextCount++;
					this.lostNextWait *= 2;
					if (this.lostNextWait > MAX_WAIT_TIME)
						this.lostNextWait = MAX_WAIT_TIME;
    			} else {
    				// reset lost state.
    				this.lostNextCount = 0;
    				this.lostNextWait = 10;
    				
    				if (DebugScan) System.out.println("FollowPathMarkers: nextScan complete now moving towards second marker at " + this.nextMarkerPos);
    				this.entityHost.getNavigator().setPath(this.entityHost.getNavigator().getPathToPos(this.nextMarkerPos), this.speed);
    			}
    		}
    	}
    }
    
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting()
    {
    	// If we are started attacking someone, don't move.
    	EntityLivingBase currTarget = this.entityHost.getAttackTarget();
    	if (currTarget != null) {
    		return false;
    	}
    	
    	// The scan we setup never found a marker. Bail. 
    	if (this.nextMarkerPos == null) {
    		//System.out.println("FollowPathMarkers: continueExecuting: could not find next marker");
    		return false;
    	}
    	 
    	// We found it and can't make it, or we actually made it. Either way, bail.
        if (this.entityHost.getNavigator().noPath()) {
        	//this.nextMarkerPos = null;
        	if (DebugScan) System.out.println("FollowPathMarkers: continueExecuting: no path to next marker, are we there?");
        	return false;
        }
        
        // If we made it here, we have a target, and we are still trying to get there.
        return true;
    }
    
    
    /**
     * @param currPos
     * @return
     */
    private boolean scanLocation(BlockPos currPos, BlockPos excludedPos) {
  
    	if ((excludedPos != null) && (currPos.equals(excludedPos)))
    		return false;
    	
    	BlockPos descPos = currPos;
    	for (Block block : blockPattern) {
    		IBlockState bstate = this.entityHost.getEntityWorld().getBlockState(descPos);
    		if (block != bstate.getBlock())
    			return false;
    		descPos = descPos.down();
    	}
    	
    	if (DebugScan) System.out.println("scanLocation: total match at location " + currPos);
    	return true;
    }
    
    
    /**
     * 
     * @param pos
     * @param direction
     * @param length
     * @return
     */
    private BlockPos scanSequence(BlockPos startPos, EnumFacing direction, int length, BlockPos excludedPos) {
    	for (int l = 0 ; l < length ; l++) {
    		BlockPos pos = startPos.offset(direction, l);
    		if (scanLocation(pos, excludedPos)) {
    			return pos;
    		}
    	}
    	
    	// If we got here, we found nothing.
    	return null;
    }
    
    
    
    /**
     * Starts a scan that starts with itself, then spreads outword in ever increasing cubes. Short circuits the first time it finds something.
     * @param startPos
     * @return
     */
    private BlockPos startUndirectedScan(int maxRange) {
    	
    	// Start at the center spot.
    	BlockPos startPos = this.entityHost.getPosition();
    	if (DebugScan) System.out.println("startUndirectedScan: starting scan at " + startPos);
    	if (scanLocation(startPos, null))
			return startPos;
    	
    	// Look at the outer surface of the 3x3x3 cube around us, then the 4x4x4 cube, etc, until we get to the maximum range.
    	int x, y, z;
    	for (int ring = 1 ; ring <= maxRange ; ring++) {
    		
    		// West side
    		x = -ring;
    		for (z = -ring ; z <= ring ; z++) {
    			for (y = -ring ; y <= ring ; y++) {
    				BlockPos currPos = startPos.add(x, y, z);
    				if (scanLocation(currPos, null))
    					return currPos;
    			}
    		}
    		
    		// East side
    		x = ring;
    		for (z = -ring ; z <= ring ; z++) {
    			for (y = -ring ; y <= ring ; y++) {
    				BlockPos currPos = startPos.add(x, y, z);
    				if (scanLocation(currPos, null))
    					return currPos;
    			}
    		}
    		
    		// North Side (cutting out corners)
    		z = -ring;
    		for (x = -(ring-1) ; x <= (ring-1) ; x++) {
    			for (y = -ring ; y <= ring ; y++) {
    				BlockPos currPos = startPos.add(x, y, z);
    				if (scanLocation(currPos, null))
    					return currPos;
    			}
    		}
    		
    		// South Side (cutting out corners)
    		z = ring;
    		for (x = -(ring-1) ; x <= (ring-1) ; x++) {
    			for (y = -ring ; y <= ring ; y++) {
    				BlockPos currPos = startPos.add(x, y, z);
    				if (scanLocation(currPos, null))
    					return currPos;
    			}
    		}
    		
    		// Down Side (cutting out 2 corners)
    		y = -ring;
    		for (x = -(ring-1) ; x <= (ring-1) ; x++) {
    			for (z = -(ring-1) ; z <= (ring-1) ; z++) {
    				BlockPos currPos = startPos.add(x, y, z);
    				if (scanLocation(currPos, null))
    					return currPos;
    			}
    		}
    		
    		// Up Side (cutting out 2 corners)
    		y = ring;
    		for (x = -(ring-1) ; x <= (ring-1) ; x++) {
    			for (z = -(ring-1) ; z <= (ring-1) ; z++) {
    				BlockPos currPos = startPos.add(x, y, z);
    				if (scanLocation(currPos, null))
    					return currPos;
    			}
    		}
    	}
    	
    	// If we got here, we did not find it.
    	return null;
    }
    
    
    
    /**
     * 
     * @param direction
     * @param maxRange
     * @param excludedPos
     * @return
     */
    private BlockPos startDirectedScan(ScanDirection direction, int maxRange, BlockPos startPos, BlockPos excludedPos) {
    	
    	// Start at the center spot.
    	//BlockPos startPos = this.entityHost.getPosition();
    	if (DebugScan) System.out.println("startDirectedScan: starting scan at " + startPos + ", in direction " + direction);
    	if (scanLocation(startPos, excludedPos))
			return startPos;
    	
    	// set after looking at direction.
    	EnumFacing depthFace1 = null;
    	EnumFacing depthFace2 = null;
    	EnumFacing sideFace1 = null;
    	EnumFacing sideFace2 = null;
    	switch (direction) {
  
    	case NORTH:
    		depthFace1 = EnumFacing.NORTH;
    		sideFace1 = EnumFacing.WEST;
    		sideFace2 = EnumFacing.EAST;
    		break;

    	case NORTHEAST:
    		depthFace1 = EnumFacing.NORTH;
    		depthFace2 = EnumFacing.EAST;
    		sideFace1 = EnumFacing.SOUTH;
    		sideFace2 = EnumFacing.WEST;
    		break;

    	case EAST:
    		depthFace1 = EnumFacing.EAST;
    		sideFace1 = EnumFacing.NORTH;
    		sideFace2 = EnumFacing.SOUTH;
    		break;

    	case SOUTHEAST:
    		depthFace1 = EnumFacing.SOUTH;
    		depthFace2 = EnumFacing.EAST;
    		sideFace1 = EnumFacing.NORTH;
    		sideFace2 = EnumFacing.WEST;
    		break;

    	case SOUTH:
    		depthFace1 = EnumFacing.SOUTH;
    		sideFace1 = EnumFacing.EAST;
    		sideFace2 = EnumFacing.WEST;
    		break;

    	case SOUTHWEST:
    		depthFace1 = EnumFacing.SOUTH;
    		depthFace2 = EnumFacing.WEST;
    		sideFace1 = EnumFacing.NORTH;
    		sideFace2 = EnumFacing.EAST;
    		break;

    	case WEST:
    		depthFace1 = EnumFacing.WEST;
    		sideFace1 = EnumFacing.SOUTH;
    		sideFace2 = EnumFacing.NORTH;
    		break;

    	case NORTHWEST:
    		depthFace1 = EnumFacing.NORTH;
    		depthFace2 = EnumFacing.WEST;
    		sideFace1 = EnumFacing.SOUTH;
    		sideFace2 = EnumFacing.EAST;
    		break;

    	default:
    		// nuttin
    	}
    	
    	BlockPos scanPos = null;
    	int x, y, z;
    	for (int ring = 1 ; ring <= maxRange ; ring++) {
    		
    		// Move our pointer to the center of the ring
    		BlockPos depthPos = startPos.offset(depthFace1, ring);
    		if (depthFace2 != null) {
    			depthPos = depthPos.offset(depthFace2, ring);
    		}
    		
    		int extraSpread = (int) (ring * DIRECTED_SCAN_SPREAD_RATIO);
    		
    		// side 1
    		for (int s = 1 ; s <= extraSpread ; s++) {
    			scanPos = scanSequence(depthPos.offset(sideFace1, s).down(extraSpread), EnumFacing.UP, ((extraSpread * 2) + 1), excludedPos);
        		if (scanPos != null)
        			return scanPos;
    		}
    		
    		// center of depth.
    		scanPos = scanSequence(depthPos.down(extraSpread), EnumFacing.UP, ((extraSpread * 2) + 1), excludedPos);
    		if (scanPos != null)
    			return scanPos;
    	
    		// side 2
    		for (int s = 1 ; s <= extraSpread ; s++) {
    			scanPos = scanSequence(depthPos.offset(sideFace2, s).down(extraSpread), EnumFacing.UP, ((extraSpread * 2) + 1), excludedPos);
        		if (scanPos != null)
        			return scanPos;
    		}
    	}
    	
    	// If we got here, we did not find it.
    	return null;
    }
    
    
    /**
     * looks at the blocks directly underneath the markerPos, and looks for them pointing in the direction of the next
     * marker, so we can di a directedScan in that direction.
     * @param markerPos
     * @return
     */
    protected ScanDirection determineScanDirection(BlockPos markerPos) {
    	
    	ScanDirection direction = ScanDirection.NONE;
    	
    	// Find the token right under the marker.
    	BlockPos downPos = markerPos.down(this.blockPattern.size());
    	Block tokenBlock = this.entityHost.getEntityWorld().getBlockState(downPos).getBlock();
    	
    	// Now wrap around looking to see if we can find a single block pointing in a specific direction. 
    	// If there are more than one, then we bug out and call it illegal.
    	BlockPos rndPos = downPos.north();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.NORTH : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.east();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.NORTHEAST : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.south();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.EAST : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.south();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.SOUTHEAST : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.west();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.SOUTH : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.west();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.SOUTHWEST : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.north();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.WEST : ScanDirection.ILLEGAL;
    	}
    	rndPos = rndPos.north();
    	if (this.entityHost.getEntityWorld().getBlockState(rndPos).getBlock() == tokenBlock) {
    		direction = (direction == ScanDirection.NONE) ? ScanDirection.NORTHWEST : ScanDirection.ILLEGAL;
    	}
    	
    	return direction;
    }
}