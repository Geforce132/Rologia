package net.geforcemods.rologia.gui;

import java.util.function.Consumer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

@OnlyIn(Dist.CLIENT)
public class GuiIconButton extends ExtendedButton {
	
    private final ResourceLocation iconLocation;
	private Consumer<GuiIconButton> onClick;

    private int xTextureOffset;
    private int yTextureOffset;
    
    private int xTextureShift;
    private int yTextureShift;

	public GuiIconButton(int xPos, int yPos, int width, int height, int texXOffset, int texYOffset, int texXShift, int texYShift, ResourceLocation resource, Consumer<GuiIconButton> onClick) {
		super(xPos, yPos, width, height, new StringTextComponent(""), b -> {});
		this.onClick = onClick;
        xTextureOffset = texXOffset;
        yTextureOffset = texYOffset;
        
        xTextureShift = texXShift;
        yTextureShift = texYShift;

        iconLocation = resource;
    }
	
	public GuiIconButton(int id, int xPos, int yPos, int width, int height, String displayString, Consumer<GuiIconButton> onClick)
    {
        super(xPos, yPos, width, height, new StringTextComponent(displayString), b -> {});
        iconLocation = null;
		this.onClick = onClick;
    }

	@Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
    	super.render(stack, mouseX, mouseY, partialTicks);
    	
    	if(iconLocation != null) {
    		RenderSystem.pushMatrix();
    		RenderSystem.color4f(1, 1, 1, 1);
	    	Minecraft.getInstance().getTextureManager().bindTexture(iconLocation);
	    	GlStateManager.disableDepthTest();
	    	int i = xTextureOffset;
	    	int j = yTextureOffset;
	
	    	GuiUtils.drawTexturedModalRect((this.x + 1) + xTextureShift, (this.y + 1) + yTextureShift, i, j, this.width, this.height, this.getBlitOffset());
	    	RenderSystem.enableDepthTest();
	    	RenderSystem.popMatrix(); 
    	}
    }
    
    @Override
	public void onClick(double mouseX, double mouseY)
	{
		if(onClick != null)
			onClick.accept(this);
	}
}