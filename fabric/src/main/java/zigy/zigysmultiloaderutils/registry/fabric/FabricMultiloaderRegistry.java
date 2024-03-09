package zigy.zigysmultiloaderutils.registry.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import zigy.zigysmultiloaderutils.registry.MultiloaderRegistry;
import zigy.zigysmultiloaderutils.registry.RegistryEntries;
import zigy.zigysmultiloaderutils.registry.RegistryEntry;

import java.util.Collection;
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
public class FabricMultiloaderRegistry<T> implements MultiloaderRegistry<T> {

    private final RegistryEntries<T> entries = new RegistryEntries<>();
    private final Registry<T> registry;
    private final String id;

    public FabricMultiloaderRegistry(Registry<T> registry, String id) {
        this.registry = registry;
        this.id = id;
    }

    @Override
    public <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier) {
        return entries.add(FabricRegistryEntry.of(this.registry, new ResourceLocation(this.id, id), supplier));
    }

    @Override
    public Collection<RegistryEntry<T>> getEntries() {
        return this.entries.getEntries();
    }

    @Override
    public void init() {
        // NO-OP
    }
}
