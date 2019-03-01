package net.geforcemods.rologia.os.apps;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface AppInfo {
	
	String id();
	String name();
	String version();
	String icon_path() default "";
	String background_image() default "rologia:textures/gui/watch/boot_screen_dark.png";

}
