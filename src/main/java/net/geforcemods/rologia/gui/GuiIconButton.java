package net.geforcemods.rologia.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
    	super.drawButton(mc, mouseX, mouseY, partialTicks);

    	GlStateManager.pushMatrix();
    	GlStateManager.color(1, 1, 1, 1);
    	mc.getTextureManager().bindTexture(iconLocation);
    	GlStateManager.disableDepth();
    	int i = xTextureOffset;
    	int j = yTextureOffset;

    	this.drawTexturedModalRect((this.x + 1) + xTextureShift, (this.y + 1) + yTextureShift, i, j, this.width, this.height);
    	GlStateManager.enableDepth();
    	GlStateManager.popMatrix();
    }
}