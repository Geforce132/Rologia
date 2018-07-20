package net.geforcemods.smartwatch.rologia.os.apps;

public enum AppType {
	
	SYSTEM(3),
	PREINSTALLED(2),
	DOWNLOADED(1);
	
	private int priority;
	
	private AppType(int type) {
		priority = type;
	}
	
	public int getPriority() {
		return priority;
	}

}
