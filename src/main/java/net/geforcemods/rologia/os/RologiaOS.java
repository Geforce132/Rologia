package net.geforcemods.rologia.os;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.screens.input.InputYesNoScreen;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.geforcemods.rologia.os.stats.UserStats;
import net.geforcemods.rologia.os.tasks.TaskUpdateTime;
import net.geforcemods.rologia.os.time.Task;
import net.geforcemods.rologia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class RologiaOS {
	
	public static final String VERSION = "0.0.1";
	public static final boolean debugMode = true;
	private boolean hasInitialized = false;
	
	private Screen currentScreen;
	private App currentApp;
	private LocalDateTime time = LocalDateTime.now();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<App> apps = new ArrayList<App>();
	private ArrayList<Notification> notifications = new ArrayList<Notification>();

	public HashMap<String, ScreenComponent> components = new HashMap<String, ScreenComponent>();

	private EntityPlayer user;
	private UserStats userStats = new UserStats();
	
	public void initOS() {
		if(hasInitialized) return;

		loadComponents();
		
		try {
			loadApps();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		hasInitialized = true;
	}
	
	public void openSmartwatchGUI(EntityPlayer player, int screenXPos, int screenYPos) {
		user = player;

		if(currentScreen == null) {
			//setScreen(new BootScreen(this, new Position(screenXPos, screenYPos)));
			setScreen(new InputYesNoScreen(this, new Position(screenXPos, screenYPos)));
			//setScreen(new InputTextScreen(this, new Position(screenXPos, screenYPos)));
			currentScreen.addStartupComponents();
			currentScreen.initializeScreen();

			// Just for testing purposes, obviously 
			notifications.add(new Notification(currentScreen, null, "t1", "body 1", 1));
			notifications.add(new Notification(currentScreen, null, "t2", "body 2", 2));
			notifications.add(new Notification(currentScreen, null, "t3", "body 3", 3));
			notifications.add(new Notification(currentScreen, null, "t4", "body 4", 4));
			notifications.add(new Notification(currentScreen, null, "t5", "body 5", 5));
		}
		else {
			Position p = new Position(screenXPos, screenYPos);
			if(!currentScreen.getPosition().matches(p)) {
				currentScreen.setPosition(p);
			}
		}
		
		tasks.add(new TaskUpdateTime(this));
	}
	
	public void update() {
		if(currentScreen != null)
		{
			currentScreen.updateScreen();

			if(isAppOpen()) {
				currentApp.updateApp();
			}

			for(Task task : tasks) {
				task.decreaseSleepCounter();
			}
		}
	}

	public void renderScreen(int mouseX, int mouseY) {
		if(currentScreen.getMousePosition() == null || currentScreen.getMousePosition().getX() != mouseX || currentScreen.getMousePosition().getY() != mouseY)
			currentScreen.setMousePos(mouseX, mouseY);
		
		currentScreen.editImages();
		currentScreen.drawImages();
		
		currentScreen.editComponents();
		currentScreen.drawComponents();

		if(isAppOpen()) {
			currentApp.drawComponents(currentScreen);
			currentApp.drawApp(currentScreen);
		}

		// Edited last because this should always be drawn over everything
		// else on the screen.
		currentScreen.editStatusBar();
		currentScreen.drawStatusBar();
	}

	private void loadComponents() {
		ResourceLoader.loadComponents(this);
	}
	
	private void loadApps() throws IOException {		
		ResourceLoader.loadApps(this);
	}
	
	public void setScreen(Screen newScreen) {
		currentScreen = newScreen;
	}
	
	public void setApp(String appID) {
		if(currentApp != getApp(appID)) {
			currentApp = getApp(appID);
			currentScreen.addComponents(currentApp);
		}
	}

	public void setTime(LocalDateTime newTime) {
		time = newTime;
	}

	public LocalDateTime getTime() {
		return time;
	}
	
	public String getTime(String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

		return time.format(formatter);
	}

	public static RologiaOS getInstanceForPlayer(EntityPlayer player) {
		if(player == null || player.getGameProfile().getId() == null)
			return null;

		String uuid = player.getGameProfile().getId().toString();

		if(!Rologia.instance.rologiaInstances.containsKey(uuid))
		{
			System.out.println("loading new");
			RologiaOS newOS = new RologiaOS();
			newOS.initOS();
			Rologia.instance.rologiaInstances.put(uuid, newOS);
		}

		return Rologia.instance.rologiaInstances.get(uuid);
	}
	
	public static void removeInstanceForPlayer(EntityPlayer player) {
		String uuid = PlayerUtils.getPlayerUUID(player);

		if(Rologia.instance.rologiaInstances.containsKey(uuid)) {
			Rologia.instance.rologiaInstances.get(uuid).apps.clear();
			Rologia.instance.rologiaInstances.remove(uuid);
			System.out.println("removing " + FMLCommonHandler.instance().getSide());
		}
	}
	
	public ArrayList<Task> getScheduledTasks() {
		return tasks;
	}
	
	public void addApp(App app) {
		apps.add(app);
	}
	
	public ArrayList<App> getApps() {
		return apps;
	}
	
	public App getApp(String appID) {
		for(App app : apps)
		{
			if(app.getAppID().matches(appID))
				return app;
		}

		return null;
	}

	public App getCurrentApp() {
		return currentApp;
	}

	public boolean isAppOpen() {
		return currentApp != null;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public EntityPlayer getUser() {
		return user;
	}
	
	public UserStats getUserStats() {
		return userStats;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

}
