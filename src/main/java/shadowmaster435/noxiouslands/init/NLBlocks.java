package shadowmaster435.noxiouslands.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shadowmaster435.noxiouslands.block.ToxicSoilBlock;

public class NLBlocks {
    public static final ToxicSoilBlock TOXICSOIL = new ToxicSoilBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRAVEL).breakByHand(true).strength(0.65f));
    public static final Block TOXICROCK = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).requiresTool().strength(1.5f));

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("noxiouslands", "toxicrock"), TOXICROCK);
        Registry.register(Registry.BLOCK, new Identifier("noxiouslands", "toxicsoil"), TOXICSOIL);
        //ItemBlocks
        Registry.register(Registry.ITEM, new Identifier("noxiouslands", "toxicrock"), new BlockItem(TOXICROCK, new FabricItemSettings().group(NLMisc.NLBLOCKSTAB)));
        Registry.register(Registry.ITEM, new Identifier("noxiouslands", "toxicsoil"), new BlockItem(TOXICSOIL, new FabricItemSettings().group(NLMisc.NLBLOCKSTAB)));

    }

}
