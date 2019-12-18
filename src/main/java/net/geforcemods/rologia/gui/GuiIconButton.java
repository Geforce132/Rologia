package net.geforcemods.rologia.gui;

import java.util.function.Consumer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiButtonExt;

@OnlyIn(Dist.CLIENT)
public class GuiIconButton extends GuiButtonExt {
	
    private final ResourceLocation iconLocation;
	private Consumer<GuiIconButton> onClick;

    private int xTextureOffset;
    private int yTextureOffset;
    
    private int xTextureShift;
    private int yTextureShift;

	public GuiIconButton(int xPos, int yPos, int width, int height, int texXOffset, int texYOffset, int texXShift, int texYShift, ResourceLocation resource, Consumer<GuiIconButton> onClick) {
		super(xPos, yPos, width, height, "", b -> {});
		this.onClick = onClick;
        xTextureOffset = texXOffset;
        yTextureOffset = texYOffset;
        
        xTextureShift = texXShift;
        yTextureShift = texYShift;

        iconLocation = resource;
    }
	
	public GuiIconButton(int xPos, int yPos, int width, int height, String displayString, Consumer<GuiIconButton> onClick)
    {
        super(xPos, yPos, width, height, displayString, b -> {});
        iconLocation = null;
		this.onClick = onClick;
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
    	super.render(mouseX, mouseY, partialTicks);
    	
    	if(iconLocation != null) {
	    	GlStateManager.func_227626_N_();
	    	GlStateManager.func_227702_d_(1, 1, 1, 1);
	    	Minecraft.getInstance().getTextureManager().bindTexture(iconLocation);
	    	RenderSystem.disableDepthTest();
	    	int i = xTextureOffset;
	    	int j = yTextureOffset;
	
	    	GuiUtils.drawTexturedModalRect((this.x + 1) + xTextureShift, (this.y + 1) + yTextureShift, i, j, this.width, this.height, this.getBlitOffset());
	    	RenderSystem.enableDepthTest();
	    	GlStateManager.func_227627_O_(); 
    	}
    }
    
    @Override
	public void onClick(double mouseX, double mouseY)
	{
		if(onClick != null)
			onClick.accept(this);
	}
}