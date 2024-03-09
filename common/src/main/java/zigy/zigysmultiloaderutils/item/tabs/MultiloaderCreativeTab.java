package zigy.zigysmultiloaderutils.item.tabs;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.ApiStatus;
import zigy.zigysmultiloaderutils.registry.MultiloaderRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "1.21")
public class MultiloaderCreativeTab {
    public final ResourceLocation id;
    public Supplier<ItemStack> icon;
    public boolean hideScrollBar;
    public boolean hideTitle;

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.21")
    public final List<MultiloaderRegistry<ItemLike>> registries = new ArrayList<>();
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.21")
    public final List<Supplier<ItemStack>> stacks = new ArrayList<>();

    public final List<Supplier<Stream<ItemStack>>> contents = new ArrayList<>();

    public MultiloaderCreativeTab(ResourceLocation id) {
        this.id = id;
    }

    public MultiloaderCreativeTab setItemIcon(Supplier<? extends ItemLike> icon) {
        return setStackIcon(() -> new ItemStack(icon.get()));
    }

    public MultiloaderCreativeTab setStackIcon(Supplier<ItemStack> icon) {
        this.icon = icon;
        return this;
    }

    public MultiloaderCreativeTab hideTitle() {
        this.hideTitle = true;
        return this;
    }

    public MultiloaderCreativeTab hideScrollBar() {
        this.hideScrollBar = true;
        return this;
    }

    public <I extends ItemLike, T extends MultiloaderRegistry<I>> MultiloaderCreativeTab addRegistry(T registry) {
        return addContent(() -> registry.boundStream().map(ItemStack::new));
    }

    public MultiloaderCreativeTab addStack(Supplier<ItemStack> stack) {
        return addContent(() -> Stream.of(stack.get()));
    }

    public MultiloaderCreativeTab addStack(ItemStack stack) {
        return addStack(() -> stack);
    }

    public MultiloaderCreativeTab addStack(ItemLike item) {
        return addStack(new ItemStack(item));
    }

    public MultiloaderCreativeTab addContent(Supplier<Stream<ItemStack>> content) {
        this.contents.add(content);
        return this;
    }

    public Supplier<CreativeModeTab> build() {
        return create(this);
    }

    @ExpectPlatform
    private static Supplier<CreativeModeTab> create(MultiloaderCreativeTab tab) {
        throw new NotImplementedException();
    }
}
