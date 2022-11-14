package net.berryh.additionalfixes.client;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class TerminalRenderer implements ICurioRenderer
{
	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(@Nonnull final ItemStack stack, @Nonnull final SlotContext slotContext, @Nonnull final PoseStack matrixStack, @Nonnull final RenderLayerParent<T, M> renderLayerParent, @Nonnull final MultiBufferSource renderTypeBuffer, final int light, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch)
	{
		final LivingEntity livingEntity = slotContext.entity();
		ICurioRenderer.translateIfSneaking(matrixStack, livingEntity);
		ICurioRenderer.rotateIfSneaking(matrixStack, livingEntity);
		matrixStack.scale(0.35F, 0.35F, 0.35F);
		matrixStack.translate(0.0F, 0.5F, -0.4F);
		matrixStack.mulPose(Direction.DOWN.getRotation());
		Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.NONE, light, OverlayTexture.NO_OVERLAY, matrixStack, renderTypeBuffer, 0);
	}
}
