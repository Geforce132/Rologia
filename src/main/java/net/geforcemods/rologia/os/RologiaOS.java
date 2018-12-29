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
import net.geforcemods.rologia.os.gui.screens.input.InputManager;
import net.geforcemods.rologia.os.gui.screens.input.InputTextScreen;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.geforcemods.rologia.os.stats.UserStats;
import net.geforcemods.rologia.os.tasks.TaskUpdateTime;
import net.geforcemods.rologia.os.time.Task;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * RologiaOS, or rOS, is the main Rologia "operating system" class which handles all of 
 * the watch's functions. Sets up screens to draw and keeps track of running apps, 
 * notifications, tasks, and other info such as the time and the owner of the watches
 * 
 * @author Geforce
 */
public class RologiaOS {
	
	/**
	 * The current version of rOS
	 */
	public static final String VERSION = "0.0.1";

	/**
	 * Whether rOS is currently running in debug mode. Allows access to the debug GUI
	 * when viewing a Screen or App
	 */
	public static final boolean debugMode = true;

	/**
	 * If this instance of rOS has been opened at least once
	 */
	private boolean hasInitialized = false;

	/**
	 * The current Screen being rendered
	 */
	private Screen currentScreen;

	/**
	 * The App currently running (null if none)
	 */
	private App currentApp;
	private LocalDateTime time = LocalDateTime.now();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<App> apps = new ArrayList<App>();
	private ArrayList<Notification> notifications = new ArrayList<Notification>();

	public HashMap<String, ScreenComponent> components = new HashMap<String, ScreenComponent>();

	private InputManager inputManager = new InputManager();

	private EntityPlayer user;
	private UserStats userStats = new UserStats();
	
	/**
	 * Loads info from the component and app .json files.
	 * Only gets called the first time this instance of rOS 
	 * tries to open a Screen
	 */
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
	
	/**
	 * Opens the watch GUI. Loads an initial Screen and starts important tasks. This
	 * is also called when the Minecraft window is resized and adjusts the Screen's position
	 */
	public void openSmartwatchGUI(EntityPlayer player, int screenXPos, int screenYPos) {
		user = player;

		if(currentScreen == null) {
			//setScreen(new BootScreen(this, new Position(screenXPos, screenYPos)));
			//setScreen(new InputYesNoScreen(this, new Position(screenXPos, screenYPos), "This is a test ohai there o hai testing testing"));
			setScreen(new InputTextScreen(this, new Position(screenXPos, screenYPos), "enter a number or smh even longer wowowow test"));
			currentScreen.addStartupComponents();
			currentScreen.initializeScreen();

			// Just for testing purposes, obviously 
			addNotification(new Notification(currentScreen, null, "t1", "body 1"));
			addNotification(new Notification(currentScreen, null, "t2", "body 2"));
			addNotification(new Notification(currentScreen, null, "t3", "body 3"));
			//notifications.add(new Notification(currentScreen, null, "t4", "body 4", 3));
			//notifications.add(new Notification(currentScreen, null, "t5", "body 5", 4));
		}
		else {
			Position p = new Position(screenXPos, screenYPos);
			if(!currentScreen.getPosition().matches(p)) {
				currentScreen.setPosition(p);
			}
		}
		
		tasks.add(new TaskUpdateTime(this));
	}
	
	/**
	 * Called every tick by GuiRologia and is used to update the Screen, Apps, and components
	 */
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

	/**
	 * Renders the screen by calling all the "draw" functions of all the parts of a Screen,
	 * including ScreenImages, ScreenComponents, and Apps. Also keeps track of the mouse
	 * cursor's position.
	 */
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

	/**
	 * Loads all default ScreenComponents created in the components .json file
	 */
	private void loadComponents() {
		ResourceLoader.loadComponents(this);
	}
	
	/**
	 * Loads Apps created by .json files in the /rologia/os/apps/ folder
	 */
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

	/**
	 * Gets the rOS instance for this Minecraft client
	 */
	public static RologiaOS getInstance() {
		if(Rologia.instance.serverProxy.getRologiaInstance() == null)
		{
			System.out.println("loading new");
			RologiaOS newOS = new RologiaOS();
			newOS.initOS();
			Rologia.instance.serverProxy.setRologiaInstance(newOS);
		}

		return Rologia.instance.serverProxy.getRologiaInstance();
	}
	
	/**
	 * Removes the rOS instance for this Minecraft client, useful for debugging
	 */
	public static void removeInstance() {
		if(Rologia.instance.serverProxy.getRologiaInstance() != null) {
			Rologia.instance.serverProxy.setRologiaInstance(null);
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

	/**
	 * Adds a Notification to the screen
	 */
	public void addNotification(Notification notification) {
		notification.setSlotNumber(notifications.size());
		notifications.add(notification);
	}

	/**
	 * Deletes a tracked Notification.
	 */
	public void removeNotification(Notification notification) {
		int slotNumber = notification.getSlotNumber();
		notifications.remove(notification);

		for(int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getSlotNumber() > slotNumber) {
				int number = notifications.get(i).getSlotNumber();
				notifications.get(i).setSlotNumber(--number);
			}
		}
	}

	public boolean hasNotifications() {
		return notifications.size() > 0;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public InputManager getInputManager() {
		return inputManager;
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
