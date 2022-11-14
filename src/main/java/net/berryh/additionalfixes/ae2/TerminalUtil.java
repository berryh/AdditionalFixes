package net.berryh.additionalfixes.ae2;

import javax.annotation.Nonnull;

import appeng.core.definitions.AEItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class TerminalUtil
{
	private TerminalUtil()
	{
	}

	@Nonnull
	public static ItemStack findTerminalCurio(@Nonnull final LivingEntity entity)
	{
		return CuriosApi.getCuriosHelper().findFirstCurio(entity, itemStack -> itemStack.getItem().equals(AEItems.WIRELESS_CRAFTING_TERMINAL.asItem()) || itemStack.getItem().equals(AEItems.WIRELESS_TERMINAL.asItem())).map(SlotResult::stack).orElse(ItemStack.EMPTY);
	}
}
