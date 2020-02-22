package net.geforcemods.rologia.os.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum Sounds {

	BEEP_1("rologia:beep_1", 20),
	BEEP_2("rologia:beep_2", 10),
	NOTIFICATION("rologia:notification", 10);

	public final String path;
	public final ResourceLocation location;
	public final SoundEvent event;
	public final int tickLength;

	private Sounds(String path, int tickLength){
		this.path = path;
		location = new ResourceLocation(path);
		event = new SoundEvent(new ResourceLocation(path));
		event.setRegistryName(path);
		this.tickLength = tickLength;
	}
}
