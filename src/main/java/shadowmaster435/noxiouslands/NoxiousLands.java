package shadowmaster435.noxiouslands;

import net.fabricmc.api.ModInitializer;
import shadowmaster435.noxiouslands.dimension.NoxiousLandsDimension;
import shadowmaster435.noxiouslands.init.NLBiomes;
import shadowmaster435.noxiouslands.init.NLBlocks;

public class NoxiousLands implements ModInitializer {

    public static String MOD_ID = "noxiouslands";

    @Override
    public void onInitialize() {
        NLBlocks.init();
        NLBiomes.init();
        NoxiousLandsDimension.init();

    }
}
