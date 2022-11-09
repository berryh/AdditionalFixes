package net.berryh.additionalfixes.mixin;

import javax.annotation.Nonnull;

import dev.ftb.mods.ftbultimine.FTBUltiminePlayerData;
import dev.ftb.mods.ftbultimine.utils.PlatformMethods;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FTBUltiminePlayerData.class)
public class AFMixinFTBUltimineReach
{
	private AFMixinFTBUltimineReach()
	{
	}

	/**
	 * @author berryh
	 * @reason Easiest way to fix the reach distance calculation.
	 */
	@Overwrite(remap = false)
	public static HitResult rayTrace(@Nonnull final ServerPlayer player)
	{
		double distance = PlatformMethods.reach(player);
		return player.pick(player.isCreative() ? distance + 0.5D : distance, 1.0F, false);
	}
}
