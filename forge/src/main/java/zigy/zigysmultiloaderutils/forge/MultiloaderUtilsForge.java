package zigy.zigysmultiloaderutils.forge;

import net.minecraftforge.fml.common.Mod;
import zigy.zigysmultiloaderutils.MultiloaderUtils;
import zigy.zigysmultiloaderutils.forge.network.ModPacketHandler;

@Mod(MultiloaderUtils.MOD_ID)
public class MultiloaderUtilsForge {
    public MultiloaderUtilsForge() {
        MultiloaderUtils.init();
        ModPacketHandler.register();
    }
}
