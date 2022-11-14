package net.berryh.additionalfixes.mixin;

import javax.annotation.Nonnull;

import appeng.helpers.WirelessCraftingTerminalMenuHost;
import appeng.helpers.WirelessTerminalMenuHost;
import appeng.items.tools.powered.WirelessCraftingTerminalItem;
import appeng.items.tools.powered.WirelessTerminalItem;
import net.berryh.additionalfixes.ae2.TerminalUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "appeng.menu.locator.MenuItemLocator")
public class AFMixinAE2MenuItemLocatorCurios<T>
{
	@Final
	@Shadow
	private int itemIndex;

	@Inject(at = @At("HEAD"), method = "locate", cancellable = true, remap = false)
	public void handleCurioItemStackOpen(@Nonnull final Player player, @Nonnull final Class<T> hostInterface, @Nonnull final CallbackInfoReturnable<@Nullable T> cir)
	{
		if (itemIndex == -1)
		{
			final ItemStack terminalStack = TerminalUtil.findTerminalCurio(player);
			if (terminalStack != ItemStack.EMPTY)
			{
				final Item terminalItem = terminalStack.getItem();
				if (terminalItem instanceof WirelessCraftingTerminalItem)
				{
					cir.setReturnValue((T) new WirelessCraftingTerminalMenuHost(player, null, terminalStack, (p, sm) -> ((WirelessTerminalItem) terminalStack.getItem()).openFromInventory(p, itemIndex)));
				}
				else if (terminalItem instanceof WirelessTerminalItem)
				{
					cir.setReturnValue((T) new WirelessTerminalMenuHost(player, null, terminalStack, (p, sm) -> ((WirelessTerminalItem) terminalStack.getItem()).openFromInventory(p, itemIndex)));
				}
			}
		}
	}
}
