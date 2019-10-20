package com.gantzgulch.ev3.device;

public interface Led {

	int getMaxBrightness();
	
	int getBrightness();
	
	void setBrightness(int brightness);

}
