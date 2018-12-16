package net.geforcemods.rologia.os.tasks;

import java.time.LocalDateTime;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.screens.Screen;
import net.geforcemods.rologia.os.time.Task;

public class TaskUpdateTime extends Task {

	public TaskUpdateTime(Rologia rologia) {
		super(rologia);
	}

	@Override
	public void execute(Rologia os, Screen currentScreen) {
		LocalDateTime localTime = LocalDateTime.now();
		
		if(!os.getTime().isEqual(localTime)) {
			os.setTime(localTime);
		}
	}

	@Override
	public boolean shouldRepeat() {
		return true;
	}

	@Override
	public int getTickDelay() {
		return 10;
	}

}