package com.gantzgulch.ev3.device;

public interface LedPair {

	int getMaxBrightness0();

	int getMaxBrightness1();
	
	int getBrightness0();

	int getBrightness1();
	
	void setBrightness(int brightness0, int brightness1);

}
