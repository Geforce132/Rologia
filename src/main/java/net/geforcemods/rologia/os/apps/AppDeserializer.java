package net.geforcemods.rologia.os.apps;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.geforcemods.rologia.os.apps.im.AppIM;
import net.geforcemods.rologia.os.gui.screens.Screen;

public class AppDeserializer implements JsonDeserializer<Screen> {
	
	@Override
    public Screen deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String id = json.getAsJsonObject().get("app_id").getAsString();

		if(id.matches("im"))
        		return context.deserialize(json, AppIM.class);
		else
    		return context.deserialize(json, AppStepCounter.class);

		//throw new IllegalArgumentException(json + " contains an invalid type " + id);
    }

}