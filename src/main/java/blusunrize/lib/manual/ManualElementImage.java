/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.lib.manual;

import blusunrize.lib.manual.gui.ManualScreen;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ManualElementImage extends SpecialManualElements
{
	private ManualImage[] images;
	int size;

	public ManualElementImage(ManualInstance helper, ManualImage... images)
	{
		super(helper);
		this.images = images;
		for(ManualImage image : images)
			size += image.vSize+5;
	}

	@Override
	public void onOpened(ManualScreen gui, int x, int y, List<Button> pageButtons)
	{
		super.onOpened(gui, x, y, pageButtons);
	}

	@Override
	public void render(ManualScreen gui, int x, int y, int mx, int my)
	{
		int yOff = 0;
		for(ManualImage image1 : images)
		{
			int xOff = 60-image1.uSize/2;
			gui.fillGradient(x+xOff-2, y+yOff-2, x+xOff+image1.uSize+2, y+yOff+image1.vSize+2,
					0xffeaa74c, 0xfff6b059);
			gui.fillGradient(x+xOff-1, y+yOff-1, x+xOff+image1.uSize+1, y+yOff+image1.vSize+1,
					0xffc68e46, 0xffbe8844);
			yOff += image1.vSize+5;
		}
		String lastResource = "";
		yOff = 0;
		GlStateManager.color3f(1, 1, 1);
		for(ManualImage image : images)
		{
			if(!image.resource.equals(lastResource))
				ManualUtils.bindTexture(image.resource);
			int xOff = 60-image.uSize/2;
			ManualUtils.drawTexturedRect(x+xOff, y+yOff, image.uSize, image.vSize, (image.uMin)/256f,
					(image.uMin+image.uSize)/256f, (image.vMin)/256f, (image.vMin+image.vSize)/256f);
			yOff += image.vSize+5;
			lastResource = image.resource;
		}
	}

	@Override
	public boolean listForSearch(String searchTag)
	{
		return false;
	}

	@Override
	public int getPixelsTaken()
	{
		return size;
	}

	public static class ManualImage
	{
		String resource;
		int uMin;
		int uSize;
		int vMin;
		int vSize;

		public ManualImage(ResourceLocation resource, int uMin, int uSize, int vMin, int vSize)
		{
			this.resource = resource.toString();
			this.uMin = uMin;
			this.uSize = uSize;
			this.vMin = vMin;
			this.vSize = vSize;
		}
	}
}
