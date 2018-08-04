package net.geforcemods.smartwatch.resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.MineWatch;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenRotatingImage;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.App;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ResourceLoader {
	
	public static final String TEXTURE_FOLDER_PATH = MineWatch.MOD_ID + ":textures/gui/watch/";
	public static final String APPS_FOLDER_PATH = MineWatch.MOD_ID + ":os/apps/";
	public static final String OS_FOLDER_PATH = MineWatch.MOD_ID + ":os/";

	public static void loadComponents(Rologia os) {
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

	public static void loadApps(Rologia os) {
		Gson gson = new Gson();

		try {
			for(App app : os.getApps()) {
				IResource appJson = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(APPS_FOLDER_PATH + app.getAppID() + ".json"));
				InputStream in = appJson.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				JsonElement je = gson.fromJson(reader, JsonElement.class);
				JsonObject json = je.getAsJsonObject();

				String appName = getAppNameFromJson(appJson);
				os.getApp(appName).setBaseAppInformation(json);
				os.getApp(appName).loadInfoFromJson(json);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static String getAppNameFromJson(IResource rl) {
		String location = rl.getResourceLocation().toString();

		return location.replace(APPS_FOLDER_PATH, "").replace(".json", "");
	}

}
