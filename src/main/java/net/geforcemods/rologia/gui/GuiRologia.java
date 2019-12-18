package net.geforcemods.rologia.gui;

import java.util.logging.Level;

import net.geforcemods.rologia.item.ItemRologia;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.gui.utils.GuiUtils;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiRologia extends net.minecraft.client.gui.screen.Screen {
	
	private static final ResourceLocation SCREEN_EDGE_TEXTURE = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "watch_screen_background.png");
	private static final ResourceLocation DEBUG_ICONS = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "debug_icons.png");

	private PlayerEntity player;
	private RologiaOS rologia;
	private ItemRologia watch;

	private GuiIconButton[] debugButtons = new GuiIconButton[3];

	public GuiRologia(PlayerEntity playerWhoOpenedGUI, ItemRologia watchItem) {
		super(new TranslationTextComponent("rologia.gui"));
		player = playerWhoOpenedGUI;
		watch = watchItem;
		rologia = RologiaOS.getInstance();
	}
	
	@Override
	public void init() {
		super.init();
		minecraft.keyboardListener.enableRepeatEvents(true);
		rologia.openSmartwatchGUI(player, (width - Screen.WATCH_SCREEN_X_SIZE) / 2, (height - Screen.WATCH_SCREEN_Y_SIZE) / 2);

		if(RologiaOS.debugMode) {
			debugButtons[0] = new GuiIconButton(this.width - 115, 10, 20, 20, "off", this::actionPerformed) {
				
				@Override
				public void onClick(double mouseX, double mouseY) {
					this.active = false;

					for(int i = 0; i < debugButtons.length; i++) {
						if(debugButtons[i] != this)
							debugButtons[i].active = true;
					}
				}
			};
			debugButtons[1] = new GuiIconButton(this.width - 90, 10, 20, 20, 0, 0, 1, 1, DEBUG_ICONS, this::actionPerformed) {
				
				@Override
				public void onClick(double mouseX, double mouseY) {
					this.active = false;

					for(int i = 0; i < debugButtons.length; i++) {
						if(debugButtons[i] != this)
							debugButtons[i].active = true;
					}
				}
			};

			debugButtons[2] = new GuiIconButton(this.width - 65, 10, 20, 20, 20, 0, 1, 1, DEBUG_ICONS, this::actionPerformed) {
				
				@Override
				public void onClick(double mouseX, double mouseY) {
					this.active = false;

					for(int i = 0; i < debugButtons.length; i++) {
						if(debugButtons[i] != this)
							debugButtons[i].active = true;
					}
				}
			};

			for(int i = 0; i < debugButtons.length; i++)
				addButton(debugButtons[i]);

			debugButtons[0].active = false;
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
		if(debugButtons[0].active)
			GuiUtils.drawFilledRect(width - 160, 0, this.width - (this.width - 160), this.height, Colors.BLACK.color, 0.5F);

		super.render(mouseX, mouseY, partialTicks);
		minecraft.getTextureManager().bindTexture(SCREEN_EDGE_TEXTURE);
		int startX = (width / 2) - (Screen.WATCH_BACKGROUND_X_SIZE / 2);
		int startY = (height / 2) - (Screen.WATCH_BACKGROUND_Y_SIZE / 2);
		GuiUtils.drawTexturedModalRect(startX, startY, 0, 0, Screen.WATCH_BACKGROUND_X_SIZE, Screen.WATCH_BACKGROUND_Y_SIZE, this.blitOffset);

		try {
			rologia.renderScreen(mouseX, mouseY);
		} catch (Exception e) {
			RologiaOS.LOGGER.log(Level.WARNING, "A problem has occured while rendering a Screen!", e);
		}

		//draw debugging info here
		if(debugButtons[0] != null && rologia.getCurrentScreen() != null) {

			if(debugButtons[1].active == false)
				rologia.getCurrentScreen().drawScreenInfo(width - 150, height);
			else if(debugButtons[2].active == false)
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
	
	protected void actionPerformed(GuiIconButton button) {
		
	}
	
	@Override
	public void onClose(){
		super.onClose();

		if(rologia.getCurrentScreen() != null)
			rologia.getCurrentScreen().screenClosed();

		minecraft.keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
}
