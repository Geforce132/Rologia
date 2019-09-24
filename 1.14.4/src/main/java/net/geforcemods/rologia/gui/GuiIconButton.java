package net.geforcemods.rologia.gui;

import java.util.function.Consumer;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiButtonExt;

@OnlyIn(Dist.CLIENT)
public class GuiIconButton extends GuiButtonExt {
	
    private final ResourceLocation iconLocation;
    
    private int xTextureOffset;
    private int yTextureOffset;
    
    private int xTextureShift;
    private int yTextureShift;

	public GuiIconButton(int id, int xPos, int yPos, int width, int height, int texXOffset, int texYOffset, int texXShift, int texYShift, String displayString, ResourceLocation resource, Consumer<GuiIconButton> onClick) {
		super(xPos, yPos, width, height, displayString, b -> {});
        xTextureOffset = texXOffset;
        yTextureOffset = texYOffset;
        
        xTextureShift = texXShift;
        yTextureShift = texYShift;

        iconLocation = resource;
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
    	super.render(mouseX, mouseY, partialTicks);

    	GlStateManager.pushMatrix();
    	GlStateManager.color4f(1, 1, 1, 1);
    	Minecraft.getInstance().getTextureManager().bindTexture(iconLocation);
    	GlStateManager.disableDepthTest();
    	int i = xTextureOffset;
    	int j = yTextureOffset;

    	this.drawTexturedModalRect((this.x + 1) + xTextureShift, (this.y + 1) + yTextureShift, i, j, this.width, this.height);
    	GlStateManager.enableDepthTest();
    	GlStateManager.popMatrix();
    }
}