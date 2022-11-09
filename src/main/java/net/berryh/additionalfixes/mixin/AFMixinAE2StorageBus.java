package net.berryh.additionalfixes.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "appeng.api.stacks.FuzzySearch$KeyComparator")
public class AFMixinAE2StorageBus
{
	@Inject(at = @At(value = "RETURN", ordinal = 3), method = "compare", remap = false, cancellable = true)
	public void compareItemTags(@Nonnull final Object a, @Nonnull final Object b, @Nonnull final CallbackInfoReturnable<Integer> cir)
	{
		cir.setReturnValue(Long.compare(a.hashCode(), b.hashCode()));
	}
}
