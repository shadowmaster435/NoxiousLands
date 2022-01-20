package shadowmaster435.noxiouslands.dimension;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import shadowmaster435.noxiouslands.dimension.chunkbuilder.NoxiousLandsChunkBuilder;

public class NoxiousLandsDimension {
    // The dimension options refer to the JSON-file in the dimension subfolder of the datapack,
    // which will always share it's ID with the world that is created from it
    public static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
            Registry.DIMENSION_KEY,
            new Identifier("noxiouslands", "noxiouslands")
    );

    public static RegistryKey<World> WORLD_KEY = RegistryKey.of(
            Registry.WORLD_KEY,
            DIMENSION_KEY.getValue()
    );

    public static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(
            Registry.DIMENSION_TYPE_KEY,
            new Identifier("noxiouslands", "noxiouslands")
    );

    public static void init() {
        Registry.register(Registry.CHUNK_GENERATOR, new Identifier("noxiouslands", "noxiouslands"), NoxiousLandsChunkBuilder.CODEC);
        WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier("noxiouslands", "noxiouslands"));
    }

    private static int swapTargeted(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerWorld serverWorld = player.getWorld();
        ServerWorld modWorld = getModWorld(context);

        if (serverWorld != modWorld) {
            TeleportTarget target = new TeleportTarget(new Vec3d(0.5, 101, 0.5), Vec3d.ZERO, 0, 0);
            FabricDimensions.teleport(player, modWorld, target);

            if (player.world != modWorld) {
                throw new CommandException(new LiteralText("Teleportation failed!"));
            }

            modWorld.setBlockState(new BlockPos(0, 100, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
            modWorld.setBlockState(new BlockPos(0, 101, 0), Blocks.TORCH.getDefaultState());
        } else {
            TeleportTarget target = new TeleportTarget(new Vec3d(0, 100, 0), Vec3d.ZERO,
                    (float) Math.random() * 360 - 180, (float) Math.random() * 360 - 180);
            FabricDimensions.teleport(player, getWorld(context, World.OVERWORLD), target);
        }

        return 1;
    }

    public static ServerWorld getModWorld(CommandContext<ServerCommandSource> context) {
        return getWorld(context, WORLD_KEY);
    }

    private static ServerWorld getWorld(CommandContext<ServerCommandSource> context, RegistryKey<World> dimensionRegistryKey) {
        return context.getSource().getServer().getWorld(dimensionRegistryKey);
    }
}