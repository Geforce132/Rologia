package net.geforcemods.rologia.events;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.apps.events.AppEvent;
import net.geforcemods.rologia.os.apps.events.AppEventPlayerStep;
import net.geforcemods.rologia.os.stats.UserStats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RologiaEventHandler {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onSoundPlayed(PlaySoundAtEntityEvent event) {
		if(event.getEntity() == null) return;

		EntityPlayer player = Minecraft.getMinecraft().player;
		if(event.getEntity() == player && event.getSound().getSoundName().getPath().endsWith(".step")) {
			RologiaOS os = RologiaOS.getInstance();
			UserStats stats = os.getUserStats();
			
			RologiaEventHandler.postRologiaEvent(new AppEventPlayerStep(stats.getStepCount(), stats.increaseStepCount(1)));
		}
	}

	public static void postRologiaEvent(AppEvent event) {
		for(App app : Rologia.instance.serverProxy.getRologiaInstance().getApps()) {
			if(app.isSubscribedTo(event.getEventType()))
				app.onEventPosted(event);
		}
	}
	
	/*@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onHandRendered(RenderSpecificHandEvent event) {
		if(event.getHand() == EnumHand.MAIN_HAND && PlayerUtils.isHoldingItem(Minecraft.getMinecraft().player, MineWatch.mineWatch)) {
			event.setCanceled(true);
			//Minecraft.getMinecraft().getItemRenderer().renderItemInFirstPerson(Minecraft.getMinecraft().getRenderPartialTicks());
			//Minecraft.getMinecraft().getItemRenderer().renderItem(Minecraft.getMinecraft().player, new ItemStack(Items.DIAMOND_SWORD, 1), TransformType.FIRST_PERSON_RIGHT_HAND);
			//this.renderArm(EnumHandSide.RIGHT);
			
			GlStateManager.pushMatrix();
			GlStateManager.translate(.4F, -.35F, -.65F);
			GlStateManager.rotate(90, -35F, 45F, 145F);
			GlStateManager.scale(1.2F, 1.2F, 1.2F);
			Minecraft.getMinecraft().getItemRenderer().renderItem(Minecraft.getMinecraft().player, new ItemStack(MineWatch.mineWatch, 1), TransformType.FIRST_PERSON_RIGHT_HAND);
			GlStateManager.popMatrix();
			
			renderArm(Minecraft.getMinecraft().getRenderPartialTicks());
			
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void renderArm(float partialTicks) {
		Minecraft mc = Minecraft.getMinecraft();
		AbstractClientPlayer abstractclientplayer = mc.player;
        float f = abstractclientplayer.getSwingProgress(partialTicks);
        EnumHand enumhand = (EnumHand)MoreObjects.firstNonNull(abstractclientplayer.swingingHand, EnumHand.MAIN_HAND);
        float f1 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
        float f2 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
        boolean flag = true;
        boolean flag1 = true;

        this.rotateArroundXAndY(f1, f2);
        this.setLightmap();
        this.rotateArm(partialTicks);
        GlStateManager.enableRescaleNormal();

        if (flag)
        {
            float f3 = enumhand == EnumHand.MAIN_HAND ? f : 0.0F;
            Minecraft.getMinecraft().getItemRenderer().renderItemInFirstPerson(abstractclientplayer, partialTicks, f1, EnumHand.MAIN_HAND, f3, ItemStack.EMPTY, 0F);
        }

        if (flag1)
        {
            float f4 = enumhand == EnumHand.OFF_HAND ? f : 0.0F;
            Minecraft.getMinecraft().getItemRenderer().renderItemInFirstPerson(abstractclientplayer, partialTicks, f1, EnumHand.OFF_HAND, f4, ItemStack.EMPTY, 1F);
        }

        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
	}
	
	private void rotateArroundXAndY(float angle, float angleY)
    {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(angle, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(angleY, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }
	
	private void setLightmap()
    {
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().player;
        int i = Minecraft.getMinecraft().world.getCombinedLight(new BlockPos(abstractclientplayer.posX, abstractclientplayer.posY + (double)abstractclientplayer.getEyeHeight(), abstractclientplayer.posZ), 0);
        float f = (float)(i & 65535);
        float f1 = (float)(i >> 16);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f, f1);
    }

    private void rotateArm(float p_187458_1_)
    {
        EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
        float f = entityplayersp.prevRenderArmPitch + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * p_187458_1_;
        float f1 = entityplayersp.prevRenderArmYaw + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * p_187458_1_;
        GlStateManager.rotate((entityplayersp.rotationPitch - f) * 0.1F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate((entityplayersp.rotationYaw - f1) * 0.1F, 0.0F, 1.0F, 0.0F);
    }
    */
}
