package net.berryh.additionalfixes.mixin;

import java.lang.invoke.MethodHandles;

import javax.annotation.Nonnull;

import appeng.core.definitions.AEItems;
import appeng.hotkeys.InventoryHotkeyAction;
import appeng.items.tools.powered.WirelessCraftingTerminalItem;
import appeng.items.tools.powered.WirelessTerminalItem;
import net.berryh.additionalfixes.ae2.TerminalUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InventoryHotkeyAction.class)
public class AFMixinAE2HotkeyActionCurios
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject(at = @At(value = "RETURN", ordinal = 1), method = "run", cancellable = true, remap = false)
	public void handleCurioInventorySearch(@Nonnull final Player player, @Nonnull final CallbackInfoReturnable<Boolean> cir)
	{
		final ItemStack terminalItem = TerminalUtil.findTerminalCurio(player);
		if (terminalItem != ItemStack.EMPTY)
		{
			final Item item = terminalItem.getItem();
			if (item instanceof WirelessCraftingTerminalItem)
			{
				AEItems.WIRELESS_CRAFTING_TERMINAL.asItem().openFromInventory(player, -1);
			}
			else if (item instanceof WirelessTerminalItem)
			{
				AEItems.WIRELESS_TERMINAL.asItem().openFromInventory(player, -1);
			}
			else
			{
				LOGGER.warn("AFMixinAE2HotkeyActionCurios encountered an unexpected situation! Item was neither of the two known AE terminals!");
			}
			cir.setReturnValue(true);
		}
	}
}
