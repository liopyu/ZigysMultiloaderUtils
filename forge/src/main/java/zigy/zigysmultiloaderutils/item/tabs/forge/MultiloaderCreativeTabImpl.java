package zigy.zigysmultiloaderutils.item.tabs.forge;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import zigy.zigysmultiloaderutils.item.tabs.MultiloaderCreativeTab;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/*
 *MIT License
 *
 *Copyright (c) 2023 Team Resourceful
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 *furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 */
public class MultiloaderCreativeTabImpl {
    private static final Map<String, DeferredRegister<CreativeModeTab>> CREATIVE_TABS = new ConcurrentHashMap<>();

    public static Supplier<CreativeModeTab> create(MultiloaderCreativeTab tab) {
        return Entry.of(tab);
    }

    private static RegistryObject<CreativeModeTab> register(ResourceLocation id, Supplier<CreativeModeTab> tab) {
        var register = CREATIVE_TABS.computeIfAbsent(id.getNamespace(), namespace -> {
            var registry = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, namespace);
            registry.register(FMLJavaModLoadingContext.get().getModEventBus());
            return registry;
        });
        return register.register(id.getPath(), tab);
    }

    private record Entry(RegistryObject<CreativeModeTab> builtTab) implements Supplier<CreativeModeTab> {

        public static Entry of(MultiloaderCreativeTab tab) {
            var creativeTab = CreativeModeTab.builder()
                    .icon(() -> tab.icon.get())
                    .title(Component.translatable("itemGroup." + tab.id.getNamespace() + "." + tab.id.getPath()));
            if (tab.hideScrollBar) creativeTab.noScrollBar();
            if (tab.hideTitle) creativeTab.hideTitle();
            creativeTab.displayItems((params, output) -> {
                tab.registries.forEach(registry -> registry.boundStream().forEach(output::accept));
                tab.stacks.stream().map(Supplier::get).forEach(output::accept);

                tab.contents.stream().flatMap(Supplier::get).forEach(output::accept);
            });
            return new Entry(register(tab.id, creativeTab::build));
        }

        @Override
        public CreativeModeTab get() {
            return builtTab.get();
        }
    }
}
