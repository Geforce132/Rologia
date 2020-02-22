package net.geforcemods.rologia.os.gui.animations;

public abstract class Animation<T> {
	
	public T component;
	private boolean finished = false;

	public Animation(T comp) {
		component = comp;
	}

	public abstract void update();
	
	public abstract void finished();

	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished() {
		finished = true;
	}
}
