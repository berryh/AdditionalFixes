package net.berryh.additionalfixes.mixin;

import appeng.api.stacks.AEKey;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "appeng.api.stacks.FuzzySearch$KeyComparator")
public class AFMixinAE2StorageBus
{
	@Inject(at = @At("RETURN"), method = "compare", remap = false, cancellable = true)
	public void compareItemTags(final Object a, final Object b, final CallbackInfoReturnable<Integer> cir)
	{
		// Compare ItemStack Tags
		final CompoundTag stackATag = ((AEKey) a).wrapForDisplayOrFilter().getTag();
		final CompoundTag stackBTag = ((AEKey) b).wrapForDisplayOrFilter().getTag();
		if (stackATag != null && stackBTag != null)
		{
			cir.setReturnValue(stackATag.getAsString().compareTo(stackBTag.getAsString()));
		}
		else if (stackATag != null)
		{
			cir.setReturnValue(-1);
		}
		else if (stackBTag != null)
		{
			cir.setReturnValue(1);
		}
	}
}
