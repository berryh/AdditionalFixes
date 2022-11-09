package net.berryh.additionalfixes.mixin;

import javax.annotation.Nonnull;

import mcjty.deepresonance.ForgeEventHandlers;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeEventHandlers.class)
public class AFMixinDRClientBreakEvent
{
	@Inject(at = @At(value = "HEAD"), method = "onBlockBreakEvent", cancellable = true, remap = false)
	public void checkServerSideOnly(@Nonnull final BlockEvent.BreakEvent event, @Nonnull final CallbackInfo ci)
	{
		if (event.getWorld().isClientSide())
		{
			ci.cancel();
		}
	}
}
