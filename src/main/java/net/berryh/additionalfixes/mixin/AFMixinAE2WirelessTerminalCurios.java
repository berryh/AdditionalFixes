package net.berryh.additionalfixes.mixin;

import javax.annotation.Nonnull;

import appeng.items.tools.powered.WirelessTerminalItem;
import appeng.menu.MenuOpener;
import appeng.menu.locator.MenuLocators;
import net.berryh.additionalfixes.ae2.TerminalUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WirelessTerminalItem.class)
public class AFMixinAE2WirelessTerminalCurios
{
	/**
	 * @author berryh
	 * @reason Easiest way to check for Curio slots, can most likely be done cleaner!
	 */
	@Overwrite(remap = false)
	public boolean openFromInventory(@Nonnull final Player player, final int inventorySlot)
	{
		final ItemStack terminalItemStack = inventorySlot == -1 ? TerminalUtil.findTerminalCurio(player) : player.getInventory().getItem(inventorySlot);
		return this.checkPreconditions(terminalItemStack, player) && MenuOpener.open(this.getMenuType(), player, MenuLocators.forInventorySlot(inventorySlot));
	}

	@Shadow
	@Nonnull
	public MenuType<?> getMenuType()
	{
		throw new IllegalStateException("This shouldn't be possible!");
	}

	@Shadow
	protected boolean checkPreconditions(@Nonnull final ItemStack item, @Nonnull final Player player)
	{
		throw new IllegalStateException("This shouldn't be possible!");
	}
}