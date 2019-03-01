package net.geforcemods.rologia.gui;

import java.util.logging.Level;

import net.geforcemods.rologia.item.ItemRologia;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiButtonExt;

@OnlyIn(Dist.CLIENT)
public class GuiRologia extends GuiScreen {
	
	private static final ResourceLocation SCREEN_EDGE_TEXTURE = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "watch_screen_background.png");
	private static final ResourceLocation DEBUG_ICONS = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "debug_icons.png");

	private EntityPlayer player;
	private RologiaOS rologia;
	private ItemRologia watch;

	private GuiButton[] debugButtons = new GuiButton[3];

	public GuiRologia(EntityPlayer playerWhoOpenedGUI, ItemRologia watchItem) {
		player = playerWhoOpenedGUI;
		watch = watchItem;
		rologia = RologiaOS.getInstance();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		mc.keyboardListener.enableRepeatEvents(true);
		rologia.openSmartwatchGUI(player, (width - Screen.WATCH_SCREEN_X_SIZE) / 2, (height - Screen.WATCH_SCREEN_Y_SIZE) / 2);

		if(RologiaOS.debugMode) {
			debugButtons[0] = new GuiButtonExt(0, this.width - 90, 10, 20, 20, "off") {
				
				@Override
				public void onClick(double mouseX, double mouseY) {
					this.enabled = false;

					for(int i = 0; i < debugButtons.length; i++) {
						if(debugButtons[i].id != this.id)
							debugButtons[i].enabled = true;
					}
				}
			};
			debugButtons[1] = new GuiIconButton(1, this.width - 65, 10, 20, 20, 0, 0, 1, 1, DEBUG_ICONS) {
				
				@Override
				public void onClick(double mouseX, double mouseY) {
					this.enabled = false;

					for(int i = 0; i < debugButtons.length; i++) {
						if(debugButtons[i].id != this.id)
							debugButtons[i].enabled = true;
					}
				}
			};

			debugButtons[2] = new GuiIconButton(2, this.width - 40, 10, 20, 20, 20, 0, 1, 1, DEBUG_ICONS) {
				
				@Override
				public void onClick(double mouseX, double mouseY) {
					this.enabled = false;

					for(int i = 0; i < debugButtons.length; i++) {
						if(debugButtons[i].id != this.id)
							debugButtons[i].enabled = true;
					}
				}
			};

			for(int i = 0; i < debugButtons.length; i++)
				buttons.add(debugButtons[i]);

			debugButtons[0].enabled = false;
		}
	}
	
	@Override
	public void tick() {
		try {
			rologia.update();
		}
		catch(Exception e) {
			RologiaOS.LOGGER.log(Level.WARNING, "A problem has occured while updating a Screen!", e);
		}
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		mc.getTextureManager().bindTexture(SCREEN_EDGE_TEXTURE);
		int startX = (width / 2) - (Screen.WATCH_BACKGROUND_X_SIZE / 2);
		int startY = (height / 2) - (Screen.WATCH_BACKGROUND_Y_SIZE / 2);
		drawTexturedModalRect(startX, startY, 0, 0, Screen.WATCH_BACKGROUND_X_SIZE, Screen.WATCH_BACKGROUND_Y_SIZE);

		try {
			rologia.renderScreen(mouseX, mouseY);
		} catch (Exception e) {
			RologiaOS.LOGGER.log(Level.WARNING, "A problem has occured while rendering a Screen!", e);
		}

		//draw debugging info here
		if(debugButtons[0] != null && rologia.getCurrentScreen() != null) {
			if(debugButtons[1].enabled == false)
				rologia.getCurrentScreen().drawScreenInfo(width - 150, height);
			else if(debugButtons[2].enabled == false)
				rologia.getCurrentScreen().drawComponentInfo(width - 150, height);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int buttonClicked) {
		rologia.getInputManager().handleMouseClick(mouseX, mouseY, buttonClicked);
		
		return super.mouseClicked(mouseX, mouseY, buttonClicked);
	}
	
	@Override
	public boolean charTyped(char character, int keyCode) {
		rologia.getInputManager().handleKeyTyped(character, keyCode);
		
		return super.charTyped(character, keyCode);
	}
	
	@Override
	public void onGuiClosed(){
		super.onGuiClosed();

		if(rologia.getCurrentScreen() != null)
			rologia.getCurrentScreen().screenClosed();

		mc.keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
