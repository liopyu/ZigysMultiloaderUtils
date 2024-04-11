package zigy.zigysmultiloaderutils.item.tabs.fabric;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import zigy.zigysmultiloaderutils.item.tabs.MultiloaderCreativeTab;

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
    /*public static Supplier<CreativeModeTab> create(MultiloaderCreativeTab tab) {
        var group = FabricItemGroupBuilder.create(new ResourceLocation("itemGroup." + tab.id.getNamespace() + "." + tab.id.getPath()))
                .icon(() -> tab.icon.get());
        group.appendItems((params, output) -> {
            tab.registries.forEach(registry -> registry.boundStream().forEach(output::accept));
            tab.stacks.stream().map(Supplier::get).forEach(output::accept);

            tab.contents.stream().flatMap(Supplier::get).forEach(output::accept);
        });
        CreativeModeTab tab1 = group.build();
        Registry.register(Registry., tab.id, tab1);
        return () -> tab1;
    }*/
}
