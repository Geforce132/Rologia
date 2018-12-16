package net.geforcemods.rologia.events;

import net.geforcemods.rologia.MineWatch;
import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.apps.events.AppEventPlayerStep;
import net.geforcemods.rologia.os.stats.UserStats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RologiaEventHandler {

	@SubscribeEvent
	public void onSoundPlayed(PlaySoundAtEntityEvent event) {
		if(event.getEntity() == null) return;

		EntityPlayer player = Minecraft.getMinecraft().player;
		if(event.getEntity() == player && event.getSound().getSoundName().getResourcePath().endsWith(".step")) {
			Rologia os = Rologia.getInstanceForPlayer(player);
			UserStats stats = os.getUserStats();
			
			MineWatch.instance.postRologiaEvent(new AppEventPlayerStep(Minecraft.getMinecraft().player, stats.getStepCount(), stats.increaseStepCount(1)));
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
