package net.geforcemods.rologia.os.gui.components;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.geforcemods.rologia.os.gui.components.text.Text;

public class ComponentDeserializer implements JsonDeserializer<ScreenComponent> {
	
	@Override
    public ScreenComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json.getAsJsonObject().get("type").getAsString().matches("ScreenText")) {
    		return context.deserialize(json, Text.class);
        }
        if(json.getAsJsonObject().get("type").getAsString().matches("ScreenButton")) {
        	return context.deserialize(json, Button.class);
        }
        
		return context.deserialize(json, ScreenComponent.class);
    }

}