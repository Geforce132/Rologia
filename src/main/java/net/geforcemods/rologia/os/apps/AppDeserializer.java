package net.geforcemods.rologia.os.apps;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class AppDeserializer implements JsonDeserializer<App> {
	
	@Override
    public App deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // String id = json.getAsJsonObject().get("components").getAsString();

        //for(App app : Rologia.apps) {
        	//if(app.getAppID().matches(id)) {
        		return context.deserialize(json, AppStepCounter.class);
        //	}
        //}
        
		//throw new IllegalArgumentException(json + " contains an invalid type " + id);
    }

}