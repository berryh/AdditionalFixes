package net.berryh.additionalfixes.mixin;

import java.util.Comparator;

import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.api.stacks.AEKey;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "appeng.client.gui.me.common.KeySorters")
public class AFMixinAE2TerminalSort
{
	private static final Comparator<AEKey> NAME_ASC = Comparator.comparing(
			key -> {
				final ItemStack stack = key.wrapForDisplayOrFilter();
				return stack.getDisplayName().getString() + (stack.getTag() != null ? stack.getTag().getAsString() : null);
			},
			String::compareToIgnoreCase);
	private static final Comparator<AEKey> NAME_DESC = NAME_ASC.reversed();
	private static final Comparator<AEKey> MOD_ASC = Comparator.comparing(AEKey::getModId, String::compareToIgnoreCase).thenComparing(NAME_ASC);
	private static final Comparator<AEKey> MOD_DESC = MOD_ASC.reversed();

	/**
	 * @author berryh
	 * @reason Unfortunately, these fields are statics and lambda's. Seems like the best way to alter them.
	 */
	@Overwrite(remap = false)
	public static Comparator<AEKey> getComparator(SortOrder order, SortDir dir)
	{
		return switch (order)
				{
					case NAME -> dir == SortDir.ASCENDING ? NAME_ASC : NAME_DESC;
					case MOD -> dir == SortDir.ASCENDING ? MOD_ASC : MOD_DESC;
					case AMOUNT -> throw new UnsupportedOperationException();
				};
	}
}
