package net.geforcemods.rologia.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiIconButton extends GuiButton {
	
    private final ResourceLocation iconLocation;
    
    private int xTextureOffset;
    private int yTextureOffset;
    
    private int xTextureShift;
    private int yTextureShift;

    public GuiIconButton(int buttonId, int x, int y, int width, int height, int texXOffset, int texYOffset, int texXShift, int texYShift, ResourceLocation resource) {
        super(buttonId, x, y, width, height, "");
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