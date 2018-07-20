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
import net.minecraft.util.ResourceLocation;

public class ResourceLoader {
	
	public static void loadAppJson(Rologia os, App app) {
		Gson gson = new Gson();
		ResourceLocation loc = new ResourceLocation(MineWatch.MOD_ID + ":apps/" + app.getAppID() + "/info.json");
		
		try {
			InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			JsonElement je = gson.fromJson(reader, JsonElement.class);
			JsonObject json = je.getAsJsonObject();
			
			app.loadInfoFromJson(json);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
