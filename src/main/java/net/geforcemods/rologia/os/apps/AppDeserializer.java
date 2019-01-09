package net.geforcemods.rologia.os.apps;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.geforcemods.rologia.os.apps.im.AppIM;

public class AppDeserializer implements JsonDeserializer<App> {
	
	@Override
    public App deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String id = json.getAsJsonObject().get("app_id").getAsString();

		if(id.matches("im"))
        		return context.deserialize(json, AppIM.class);
		else
    		return context.deserialize(json, AppStepCounter.class);

		//throw new IllegalArgumentException(json + " contains an invalid type " + id);
    }

}