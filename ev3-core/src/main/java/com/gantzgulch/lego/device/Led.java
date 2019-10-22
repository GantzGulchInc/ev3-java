package com.gantzgulch.lego.device;

public interface Led {

	int getMaxBrightness();
	
	int getBrightness();
	
	void setBrightness(int brightness);

}
