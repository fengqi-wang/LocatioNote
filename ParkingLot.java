package com.fengqi.plms;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
public static void main(char[] args) {
	System.out.println("HHH");
}

public class Car {
	public String plate;
}

private static final int MAX_SPACES = 100;

private static final int NUM_OF_ENTRENCES = 5;
private static final int NUM_OF_EXITS = 4;

private Car[] parkings;

private Entrence[] entrences = new Entrence[NUM_OF_ENTRENCES];
private Exit[] exits = new Exit[NUM_OF_EXITS];

public class Entrence {
	private int number;
	
	public void open() {
		
	}
	public void close() {
		
	}
}

public class Exit {
	private int number;
	
	public void open() {
		
	}
	public void close() {
		
	}
}

void initialize() {
	parkings = new Car[MAX_SPACES];
		
	for (Entrence entr : entrences) {
		entr.close();
	}
	
	for (Exit ex : exits) {
		ex.close();
	}
}

private int findAvailableSpace() {
	int result = -1;
	for(int i = 0; i < parkings.length; i++) {
		if (parkings[i] == null) {
			result = i;
			break;
		}
	}
	return result;
}

private void issueTicket(Car car) {
	
}

private void charge(Car car) {
	
}

void handleArrival(Car car, Entrence entr) {
	int found = findAvailableSpace();
	if (found != -1) {
		entr.open();
		issueTicket(car);
		entr.close();
	}
}

void handleLeaving(Car car, Exit ex) {
	charge(car);
	ex.open();
	ex.close();
}
}
