package net.geforcemods.rologia.os;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.apps.AppInfo;
import net.geforcemods.rologia.os.apps.AppStepCounter;
import net.geforcemods.rologia.os.apps.im.AppIM;
import net.geforcemods.rologia.os.gui.screens.HomeScreen;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.gui.screens.SelectionScreen;
import net.geforcemods.rologia.os.gui.screens.SettingsScreen;
import net.geforcemods.rologia.os.gui.screens.input.InputManager;
import net.geforcemods.rologia.os.gui.utils.Theme;
import net.geforcemods.rologia.os.imc.IMCManager;
import net.geforcemods.rologia.os.misc.Position;
import net.geforcemods.rologia.os.notifications.Notification;
import net.geforcemods.rologia.os.resources.ResourceLoader;
import net.geforcemods.rologia.os.stats.UserStats;
import net.geforcemods.rologia.os.tasks.Task;
import net.geforcemods.rologia.os.tasks.TaskUpdateTime;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.loading.FMLLoader;

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

	public static final Logger LOGGER = Logger.getLogger("rOS");

	/**
	 * If this instance of rOS has been opened at least once
	 */
	private boolean hasInitialized = false;

	/**
	 * The current Screen being rendered
	 */
	private Screen currentScreen;
	private Screen homeScreen;

	private HashMap<String, Screen> activeScreens = new HashMap<String, Screen>();

	/**
	 * Used when a Screen requests a Screen change
	 */
	private Screen screenToSwitchTo;

	private LocalDateTime time = LocalDateTime.now();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<Notification> notifications = new ArrayList<Notification>();

	private InputManager inputManager = new InputManager(this);
	private IMCManager imcManager = new IMCManager();

	private ArrayList<Screen> apps = new ArrayList<Screen>();
	private HashMap<String, Theme> themes = new HashMap<String, Theme>();
	private Theme currentTheme;

	private PlayerEntity user;
	private UserStats userStats = new UserStats();
	
	/**
	 * Loads info from the component and app .json files.
	 * Only gets called the first time this instance of rOS 
	 * tries to open a Screen
	 */
	public void initOS() {
		if(hasInitialized) return;

		try {
			loadThemes();
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
	public void openSmartwatchGUI(PlayerEntity player, int screenXPos, int screenYPos) {
		user = player;

		if(currentScreen == null) {
			loadSystemScreens(screenXPos, screenYPos);
			//homeScreen = new InputTextScreen(this, new Position(screenXPos, screenYPos), "This is a test ohai there o hai testing testing");
			//setScreen(new InputYesNoScreen(this, new Position(screenXPos, screenYPos), "This is a test ohai there o hai testing testing"));
			//setScreen(new InputTextScreen(this, new Position(screenXPos, screenYPos), "enter a number or smh even longer wowowow test"));
			//currentScreen.addStartupComponents();
			//currentScreen.initializeScreen();

			// Just for testing purposes, obviously 
			addNotification(new Notification(currentScreen, "t1", "body 1"));
			addNotification(new Notification(currentScreen, "t2", "body 2"));
			addNotification(new Notification(currentScreen, "t3", "body 3"));
		}
		else {
			Position p = new Position(screenXPos, screenYPos);
			
			for(Screen screen : activeScreens.values())
				screen.setPosition(p);
		}
		
		tasks.add(new TaskUpdateTime(this));
	}
	
	/**
	 * Called every tick by GuiRologia and is used to update the Screen, Apps, and components
	 */
	public void update() {
		if(isScreenBeingSwapped()) {
			currentScreen = screenToSwitchTo;
			currentScreen.addStartupComponents();
			currentScreen.initializeScreen();

			activeScreens.put(currentScreen.getScreenName(), currentScreen);
			screenToSwitchTo = null;
		}

		if(currentScreen != null) {
			checkScrollBar();
			currentScreen.updateScreen();

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
		if(isScreenBeingSwapped() || currentScreen == null) return;

		if(currentScreen.getMousePosition() == null || currentScreen.getMousePosition().getX() != mouseX || currentScreen.getMousePosition().getY() != mouseY)
			currentScreen.setMousePos(mouseX, mouseY);
		
		currentScreen.drawBackgroundImage();
		
		currentScreen.editComponents();
		currentScreen.drawComponents();

		// Edited last because this should always be drawn over everything
		// else on the screen.
		currentScreen.drawStatusBar();
	}

	/**
	 * Loads Apps created by .json files in the /rologia/os/apps/ folder
	 */
	/*
	private void loadApps() throws IOException {		
		//ResourceLoader.loadApps(this);
		String[] appsToLoad = new String[] {AppStepCounter.class.getName(), AppIM.class.getName()};

		for(String appPath : appsToLoad) {
			try {
				Class<? extends App> clazz = (Class<? extends App>) Class.forName(appPath);
				AppInfo annotation = clazz.getDeclaredAnnotation(AppInfo.class);

				Constructor<?> ctor = clazz.getConstructor(RologiaOS.class);

				App app = (App) ctor.newInstance(new Object[] { this });

				app.setAppID(annotation.id());
				app.setAppName(annotation.name());
				app.setAppVersion(annotation.version());
				app.setAppBackgroundImage(annotation.background_image());

				addApp(app);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} 
			
		}
	}
	*/
	
	/**
	 * Loads color themes created by .json files in the /rologia/os/themes/ folder
	 */
	private void loadThemes() throws IOException {
		ResourceLoader.loadThemes(this);
		currentTheme = themes.get("light");
	}

	/**
	 * Loads the home, app selection, and system screens when the watch GUI is first opened.
	 */
	private void loadSystemScreens(int screenXPos, int screenYPos) {
		Position pos = new Position(screenXPos, screenYPos);
		activeScreens.put(HomeScreen.NAME, new HomeScreen(this, pos));
		activeScreens.put(SelectionScreen.NAME, new SelectionScreen(this, pos));
		activeScreens.put(SettingsScreen.NAME, new SettingsScreen(this, pos));

		loadApps(pos);

		setScreen(activeScreens.get(HomeScreen.NAME));
	}

	private void loadApps(Position pos) {
		apps.add(new AppStepCounter(this, pos));
		apps.add(new AppIM(this, pos));
	}

	public void checkScrollBar() {
		if(currentScreen.getScreenHeight() > Screen.WATCH_SCREEN_Y_SIZE)
			currentScreen.getScrollBar().setVisibility(true);
		else {
			if(currentScreen.getScrollBar().isVisible()) 
				currentScreen.getScrollBar().setVisibility(false);
		}
	}

	public boolean isAppOpen() {
		return currentScreen != null && currentScreen.getClass().isAnnotationPresent(AppInfo.class);
	}

	/**
	 * Sets screenToSwitchTo to the new Screen. This is used instead of directly
	 * updating currentScreen to prevent ConcurrentModificationExceptions due to
	 * components currently being rendered/updated
	 * 
	 * @param newScreen The Screen to change to
	 */
	public void setScreen(Screen newScreen) {
		screenToSwitchTo = newScreen;
	}

	/**
	 * Sets screenToSwitchTo to the new Screen. This is used instead of directly
	 * updating currentScreen to prevent ConcurrentModificationExceptions due to
	 * components currently being rendered/updated
	 * 
	 * @param screenName The Screen to change to
	 */
	public void setScreen(String screenName) {
		setScreen(activeScreens.get(screenName));
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
			System.out.println("removing " + FMLLoader.getDist());
		}
	}
	
	public ArrayList<Task> getScheduledTasks() {
		return tasks;
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

	public IMCManager getIMCManager() {
		return imcManager;
	}

	public Theme getTheme() {
		return currentTheme;
	}

	public HashMap<String, Theme> getThemes() {
		return themes;
	}

	public ArrayList<Screen> getApps() {
		return apps;
	}

	public void setTheme(String themeName) {
		if(!themes.containsKey(themeName))
			LOGGER.log(Level.WARNING, "Theme '" + themeName + "' doesn't exist!");

		currentTheme = themes.get(themeName);
	}

	public void addTheme(String themeName, Theme theme) {
		themes.put(themeName, theme);
	}

	public PlayerEntity getUser() {
		return user;
	}
	
	public UserStats getUserStats() {
		return userStats;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	public Screen getHomeScreen() {
		return homeScreen;
	}

	/**
	 * @return If the Screen currently being used to about to changed. Used to prevent
	 * ConcurrentModificationExceptions.
	 */
	public boolean isScreenBeingSwapped() {
		return screenToSwitchTo != null;
	}

}
