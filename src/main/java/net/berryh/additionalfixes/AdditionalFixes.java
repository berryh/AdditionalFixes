package net.berryh.additionalfixes;

import net.berryh.additionalfixes.ae2.AE2CuriosEventHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AdditionalFixes.MOD_ID)
public class AdditionalFixes
{
	public static final String MOD_ID = "additionalfixes";

	public AdditionalFixes()
	{
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		final AE2CuriosEventHandler ae2CuriosEventHandler = new AE2CuriosEventHandler();
		modEventBus.addListener(ae2CuriosEventHandler::setup);
		modEventBus.addListener(ae2CuriosEventHandler::setupCuriosRendering);
		modEventBus.addListener(ae2CuriosEventHandler::handleCuriosRegistration);
	}
}
