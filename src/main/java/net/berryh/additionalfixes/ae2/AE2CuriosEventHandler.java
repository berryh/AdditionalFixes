package net.berryh.additionalfixes.ae2;

import javax.annotation.Nonnull;

import appeng.core.definitions.AEItems;
import net.berryh.additionalfixes.client.TerminalRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class AE2CuriosEventHandler
{
	public void setup(@Nonnull final FMLCommonSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void setupCuriosRendering(@Nonnull final FMLClientSetupEvent event)
	{
		CuriosRendererRegistry.register(AEItems.WIRELESS_CRAFTING_TERMINAL.asItem(), TerminalRenderer::new);
		CuriosRendererRegistry.register(AEItems.WIRELESS_TERMINAL.asItem(), TerminalRenderer::new);
	}

	public void handleCuriosRegistration(@Nonnull final InterModEnqueueEvent event)
	{
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CURIO.getMessageBuilder().build());
	}

	@SubscribeEvent
	public void attachCapabilities(@Nonnull final AttachCapabilitiesEvent<ItemStack> event)
	{
		if (event.getObject().getItem() != AEItems.WIRELESS_CRAFTING_TERMINAL.asItem() || event.getObject().getItem() != AEItems.WIRELESS_TERMINAL.asItem())
		{
			return;
		}
		final ICurio curio = new ICurio()
		{
			@Override
			@Nonnull
			public ItemStack getStack()
			{
				return event.getObject();
			}

			@Override
			public boolean canEquipFromUse(@Nonnull final SlotContext slotContext)
			{
				return true;
			}
		};
		final ICapabilityProvider capabilityProvider = new ICapabilityProvider()
		{
			private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

			@NotNull
			@Override
			public <T> LazyOptional<T> getCapability(@NotNull final Capability<T> cap, @Nullable final Direction side)
			{
				return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
			}
		};
		event.addCapability(CuriosCapability.ID_ITEM, capabilityProvider);
	}
}
