package net.geforcemods.smartwatch.resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.geforcemods.smartwatch.MineWatch;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.geforcemods.smartwatch.rologia.os.apps.App;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ResourceLoader {
	
	public static void loadApps(Rologia os) {
		Gson gson = new Gson();

		try {
			for(App app : os.getApps()) {
				IResource appJson = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(MineWatch.MOD_ID + ":apps/" + app.getAppID() + ".json"));
				InputStream in = appJson.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				JsonElement je = gson.fromJson(reader, JsonElement.class);
				JsonObject json = je.getAsJsonObject();

				String appName = getAppNameFromJson(appJson);
				System.out.println(appName);
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

		return location.replace(MineWatch.MOD_ID + ":apps/", "").replace(".json", "");
	}

}
