package net.geforcemods.rologia.os.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.apps.App;
import net.geforcemods.rologia.os.apps.AppDeserializer;
import net.geforcemods.rologia.os.gui.components.ComponentDeserializer;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ResourceLoader {
	
	/**
	 * Minecraft's root folder. MC_DIR gets set during {@link Rologia}.preInit()
	 */
	public static File MC_DIR;
	
	public static final String TEXTURE_FOLDER_PATH = Rologia.MOD_ID + ":textures/gui/watch/";
	public static final String APPS_FOLDER_PATH = Rologia.MOD_ID + ":os/apps/";
	public static final String OS_FOLDER_PATH = Rologia.MOD_ID + ":os/";

	/*
	public static void loadComponents(RologiaOS os) {
		Gson gson = new Gson();

		try {
			IResource appJson = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(OS_FOLDER_PATH + "components.json"));
			InputStream in = appJson.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			JsonElement je = gson.fromJson(reader, JsonElement.class);
			JsonObject json = je.getAsJsonObject();

			JsonArray components = json.get("components").getAsJsonArray();

			for(int i = 0; i < components.size(); i++)
			{
				JsonObject object = components.get(i).getAsJsonObject();

				String name = object.get("name").getAsString();
				String type = object.get("type").getAsString();
				String texturePath = object.get("texture_path").getAsString();
				int width = object.get("width").getAsInt();
				int height = object.get("height").getAsInt();

				if(type.matches("image"))
				{
					ScreenImage image = new ScreenImage(os, texturePath, width, height);
					os.components.put(name, image);
				}
				else if(type.matches("rotating_image"))
				{
					float rotationSpeed = object.get("rotation_speed").getAsFloat();

					ScreenRotatingImage image = new ScreenRotatingImage(os, texturePath, width, height, rotationSpeed);
					os.components.put(name, image);
				}

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public static File getRologiaFolder() {
		File folder = new File(MC_DIR, "rologia/");
		
		if(!folder.exists())
			folder.mkdir();
		
		return folder;
	}
	
	public static void loadApps(RologiaOS os) throws IOException {
		IResource appsJson = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(APPS_FOLDER_PATH + "apps.json"));
		JsonElement je = new Gson().fromJson(new BufferedReader(new InputStreamReader(appsJson.getInputStream())), JsonElement.class);
		JsonObject json = je.getAsJsonObject();

		ArrayList<String> appsToLoad = new ArrayList<String>();
		for(int i = 0; i < json.get("apps").getAsJsonArray().size(); i++)
			appsToLoad.add(json.get("apps").getAsJsonArray().get(i).getAsString());

		for(String appToLoad : appsToLoad) {
			IResource appJson = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(APPS_FOLDER_PATH + appToLoad));
			InputStream in = appJson.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(App.class, new AppDeserializer()).registerTypeAdapter(ScreenComponent.class, new ComponentDeserializer()).create();

			Type type = new TypeToken<App>(){}.getType();

			// Create a base App instance to work with
			App app = gson.fromJson(reader, type);

			app.setOS(os);	
			os.addApp(app);
		}

	}

	/*
	public static void loadApps(RologiaOS os) {
		File appsFolder = new File(getRologiaFolder(), "apps/");
		
		if(!appsFolder.exists())
			appsFolder.mkdir();
		
		try {
			for(File appJson : appsFolder.listFiles()) {
				if(!FilenameUtils.getExtension(appJson.getName()).matches("json")) return;
				
		        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(App.class, new AppDeserializer()).registerTypeAdapter(ScreenComponent.class, new ComponentDeserializer()).create();

		        Type type = new TypeToken<App>(){}.getType();

		        // Create a base App instance to work with
	        	BufferedReader reader = new BufferedReader(new FileReader(appJson));
	        	App app = gson.fromJson(reader, type);

				app.setOS(os);
				
				//System.out.printf("\n%s %s %s", app.getAppName(), app.getAppID(), app.getAppVersion());

				os.addApp(app);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/

}
