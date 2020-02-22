package net.geforcemods.rologia.gui;

import java.util.function.Consumer;

import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

public class GuiButtonClick extends ExtendedButton
{
	private Consumer<GuiButtonClick> onClick;
	public int id;

	public GuiButtonClick(int id, int xPos, int yPos, int width, int height, String displayString, Consumer<GuiButtonClick> onClick)
	{
		super(xPos, yPos, width, height, displayString, b -> {});

		this.id = id;
		this.onClick = onClick;
	}

	@Override
	public void onClick(double mouseX, double mouseY)
	{
		if(onClick != null)
			onClick.accept(this);
	}
}
