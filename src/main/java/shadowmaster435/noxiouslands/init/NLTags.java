package shadowmaster435.noxiouslands.init;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class NLTags {
    public static final Tag<Block> TOXICROCK = TagFactory.BLOCK.create(new Identifier("noxiouslands", "toxicrock"));
    public static final Tag<Block> TOXICSOIL = TagFactory.BLOCK.create(new Identifier("noxiouslands", "toxicsoil"));

}
