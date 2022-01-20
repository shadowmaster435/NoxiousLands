package shadowmaster435.noxiouslands.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import shadowmaster435.noxiouslands.NoxiousLands;
import shadowmaster435.noxiouslands.world.biome.AcridOverhangs;

public class NLBiomes {
    public static final RegistryKey<Biome> TOXICHILLS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(NoxiousLands.MOD_ID, "acridoverhangs"));



    public static void init() {
        Registry.register(BuiltinRegistries.BIOME, TOXICHILLS_KEY.getValue(), AcridOverhangs.createToxicHills());
    }
}
