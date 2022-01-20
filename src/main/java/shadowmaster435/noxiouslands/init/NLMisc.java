package shadowmaster435.noxiouslands.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class NLMisc {

    public static final ItemGroup NLBLOCKSTAB = FabricItemGroupBuilder.create(
                    new Identifier("noxiouslands", "blocks"))
            .icon(() -> new ItemStack(NLBlocks.TOXICROCK))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(NLBlocks.TOXICROCK));
                stacks.add(new ItemStack(NLBlocks.TOXICSOIL));
            })
            .build();
    public static final ItemGroup NLITEMSTAV = FabricItemGroupBuilder.create(
                    new Identifier("noxiouslands", "items"))
            .icon(() -> new ItemStack(Items.AIR))
            .appendItems(stacks -> {
            })
            .build();
}
