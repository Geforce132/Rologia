package net.geforcemods.smartwatch.rologia.os;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import net.geforcemods.smartwatch.MineWatch;
import net.geforcemods.smartwatch.resources.ResourceLoader;
import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.screens.BootScreen;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.apps.App;
import net.geforcemods.smartwatch.rologia.os.stats.UserStats;
import net.geforcemods.smartwatch.rologia.os.tasks.TaskUpdateTime;
import net.geforcemods.smartwatch.rologia.os.time.Task;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Rologia {
	
	public static final String VERSION = "0.0.1";
	public static final boolean debugMode = true;
	private boolean hasInitialized = false;
	
	private Screen currentScreen;
	private App currentApp;
	private LocalDateTime time = LocalDateTime.now();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	public static ArrayList<App> apps = new ArrayList<App>();
	public static HashMap<String, App> appTypes = new HashMap<String, App>();

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
		setScreen(new BootScreen(this, screenXPos, screenYPos));
		currentScreen.addStartupComponents();
		currentScreen.initializeScreen();
		
		tasks.add(new TaskUpdateTime(this));
	}
	
	public void update() {
		if(currentScreen != null)
		{
			currentScreen.updateScreen();
			currentApp.updateApp();

			for(Task task : tasks) {
				task.decreaseSleepCounter();
			}
		}
	}

	public void renderScreen(int mouseX, int mouseY) {
		if(currentScreen.getMouseX() != mouseX || currentScreen.getMouseY() != mouseY)
			currentScreen.setMousePos(mouseX, mouseY);
		
		currentScreen.editImages();
		currentScreen.drawImages();
		
		currentScreen.editComponents();
		currentScreen.drawComponents();

		currentApp.drawComponents(currentScreen);
		currentApp.drawApp(currentScreen);

		// Edited last because this should always be drawn over everything
		// else on the screen.
		currentScreen.editStatusBar();
		currentScreen.drawStatusBar();
		
		if(debugMode) {
			currentScreen.drawDebuggingTools(mouseX, mouseY);
		}
	}

	private void loadComponents() {
		ResourceLoader.loadComponents(this);
	}
	
	private void loadApps() throws IOException {		
		ResourceLoader.loadApps(this);
		setApp("step_counter");
	}
	
	public void setScreen(Screen newScreen) {
		currentScreen = newScreen;
	}
	
	public void setApp(String appID) {
		currentApp = getApp(appID);
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

	public static Rologia getInstanceForPlayer(EntityPlayer player) {
		if(player == null || player.getGameProfile().getId() == null)
			return null;

		String uuid = player.getGameProfile().getId().toString();

		if(!MineWatch.instance.rologiaInstances.containsKey(uuid))
		{
			System.out.println("loading new");
			Rologia newOS = new Rologia();
			newOS.initOS();
			MineWatch.instance.rologiaInstances.put(uuid, newOS);
		}

		return MineWatch.instance.rologiaInstances.get(uuid);
	}
	
	public static void removeInstanceForPlayer(EntityPlayer player) {
		if(MineWatch.instance.rologiaInstances.containsKey(player.getGameProfile().getId().toString())) {
			MineWatch.instance.rologiaInstances.remove(player.getGameProfile().getId().toString());
			Rologia.apps.clear();
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
