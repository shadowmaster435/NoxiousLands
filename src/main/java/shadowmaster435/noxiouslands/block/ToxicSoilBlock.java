package shadowmaster435.noxiouslands.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import shadowmaster435.noxiouslands.init.NLBlocks;

public class ToxicSoilBlock extends Block {
    public static final BooleanProperty ROCKBELOW = BooleanProperty.of("rockbelow");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(ROCKBELOW);
    }

    public ToxicSoilBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(ROCKBELOW, false));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (world.getBlockState(pos.down(1)) == NLBlocks.TOXICROCK.getDefaultState()) {
            world.setBlockState(pos, this.getDefaultState().with(ROCKBELOW, true));
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.getBlockState(pos.down(1)) == NLBlocks.TOXICROCK.getDefaultState()) {
            world.setBlockState(pos, this.getDefaultState().with(ROCKBELOW, true));
        }
    }
}
