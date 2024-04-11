package lio.liosmultiloaderutils.forge;

import net.minecraftforge.fml.common.Mod;
import lio.liosmultiloaderutils.MultiloaderUtils;
import lio.liosmultiloaderutils.forge.network.ModPacketHandler;

@Mod(MultiloaderUtils.MOD_ID)
public class MultiloaderUtilsForge {
    public MultiloaderUtilsForge() {
        MultiloaderUtils.init();
        ModPacketHandler.register();
    }
}
