package net.geforcemods.smartwatch.rologia.os.apps;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEvent;
import net.geforcemods.smartwatch.rologia.os.apps.events.AppEventType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AppStepCounter extends App {
	
	public AppStepCounter(Rologia rologia) {
		super(rologia);
	}

	public void drawApp(Screen currentScreen) {
		GL11.glPushMatrix();
		currentScreen.drawString("Step count: " + os.getUserStats().getStepCount(), currentScreen.getXPos(), currentScreen.getYPos() + 10, 55555);
		currentScreen.drawString("Distance walked: " + getDistanceInMiles(os.getUser()), currentScreen.getXPos(), currentScreen.getYPos() + 30, 55555);
		GL11.glPopMatrix();
	}
	
	@Override
	public void updateApp() {
	}
	
	@Override
	public void onEventPosted(AppEvent event) {
		System.out.println(getDistanceInMiles(event.getPlayer()) + " | " + FMLCommonHandler.instance().getSide());
	}
	
	@Override
	public void loadInfoFromJson(JsonObject json) {}
	
	public Object replaceKeywords(String keyword) {
		if(keyword.matches("step_count"))
			return os.getUserStats().getStepCount();

		if(keyword.matches("distance_walked"))
			return getDistanceInMiles(os.getUser());

		return "unknown";
	}

	private float getDistanceInMiles(EntityPlayer player) {
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
	public String getAppID() {
		return "step_counter";
	}

}
