package net.geforcemods.rologia.os.apps;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.events.AppEvent;
import net.geforcemods.rologia.os.apps.events.AppEventType;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.components.images.Image;
import net.geforcemods.rologia.os.gui.components.text.Text;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.utils.Colors;
import net.geforcemods.rologia.os.misc.Position;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.loading.FMLLoader;

@AppInfo(id=AppStepCounter.ID, name = AppStepCounter.NAME, version = AppStepCounter.VERSION)
public class AppStepCounter extends Screen {

	public static final String ID = "step_counter";
	public static final String NAME = "Step Counter";
	public static final String VERSION = "1.0.0";
	
	private Text distance = new Text(getOS(), "Distance travelled:", null, Colors.GREEN.hexValue);

	public AppStepCounter(RologiaOS rologia, Position pos) {
		super(rologia, pos);
	}

	@Override
	public void initializeScreen() {
		distance.centerPosition(0, -20);
		addComponent(distance);
	}

	@Override
	public void updateScreen() {
		String newText = "Distance travelled: %s";

		if(!distance.getText().equals(newText)) {
			distance.setText(newText, getDistanceInMiles(getOS().getUser()));
		}
	}

	@Override
	public void onComponentClicked(ScreenComponent component, Position mousePos, int mouseButtonClicked) {}
	
	@Override
	public void onEventPosted(AppEvent event) {
		System.out.println(getDistanceInMiles(event.getPlayer()) + " | " + FMLLoader.getDist());
	}
	
	@Override
	public Object replaceKeywords(String keyword) {
		if(keyword.matches("step_count"))
			return getOS().getUserStats().getStepCount();
		
		if(keyword.matches("distance_walked"))
			return getDistanceInMiles(getOS().getUser());

		return "unknown";
	}
	
	private float getDistanceInMiles(PlayerEntity player) {
		float feet = (player.distanceWalkedModified * 3);

		DecimalFormat df = new DecimalFormat("###.##");
		df.setRoundingMode(RoundingMode.DOWN);

		return Float.parseFloat(df.format(feet / 5280F));
	}

	@Override
	public AppEventType[] subscribeToEvents() {
		return new AppEventType[] {AppEventType.PLAYER_STEP};
	}

	@Override
	public String getScreenName() {
		return NAME;
	}

	@Override
	public Image getBackgroundImage() {
		return new Image(getOS(), "rologia:textures/gui/watch/boot_screen_light.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);
	}

}
