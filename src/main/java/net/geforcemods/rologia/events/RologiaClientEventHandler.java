package net.geforcemods.rologia.events;

import com.mojang.blaze3d.platform.GlStateManager;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=Rologia.MOD_ID, value=Dist.CLIENT)
public class RologiaClientEventHandler {

	@SubscribeEvent
	public static void renderGameOverlay(RenderGameOverlayEvent event) {
		if(event.getType() == ElementType.HOTBAR) {
			RologiaOS os = RologiaOS.getInstance();
			Minecraft mc = Minecraft.getInstance();

			if(os != null && os.notificationPopupTimer > 0) {
				os.notificationPopupTimer--;

				GlStateManager.pushMatrix();
				GlStateManager.enableBlend();
				double alpha = 0.0D;

				if(os.notificationPopupTimer >= 240) {
					alpha = (RologiaOS.NOTIFICATION_TICK_LENGTH - os.notificationPopupTimer) * .00833;
				}
				else if(os.notificationPopupTimer > 120 && os.notificationPopupTimer < 240) {
					alpha = 1.0D;
				}
				else if(os.notificationPopupTimer >= 0 && os.notificationPopupTimer <= 120) {
					alpha = ((os.notificationPopupTimer + 120) * .00833) - 1;
				}

				int x = Minecraft.getInstance().mainWindow.getScaledWidth() / 2 - 112;
				int y = Minecraft.getInstance().mainWindow.getScaledHeight() - 18 - ((int) (alpha * 17));
				GuiUtils.drawFilledRect(x - 2, y - 2, 9, 11, Colors.DARK_GRAY.color, (float) alpha);
				mc.fontRenderer.drawStringWithShadow(os.getNotifications().size() + "", x, y, Colors.RED.hexValue | ((int) (alpha * 255) << 24));

				GlStateManager.disableBlend();
				GlStateManager.popMatrix();
			}
		}
	}

}
