package com.test;

public class PKMS {

  public class Car {

    public String plate;
  }

  public class Entrence {

    private int number; 

    public void open() {
      // TBD
    }

    public void close() {
      // TBD, delay some seconds for safety;
    }
  }

  public class Exit {

    private int number;

    public void open() {
      // TBD
    }

    public void close() {
      // TBD, assuming the hardware will delay some seconds for safety;
    }
  }

  public class Display {

    private int freeSpaces;

    public synchronized void update(int spaces) {
      freeSpaces = spaces;
    }

    public synchronized void addSpaces() {
      freeSpaces++;
    }

    public synchronized void minusSpaces() {
      freeSpaces--;
    }
  }
  
  public static enum Direction {    
    ARRIVAL,
    LEAVING
  }
  
  public class Event { 
    public Direction dir;
    public Car getCar() {
     return null; //TBD 
    }
    public Entrence getEntrence() {
      return null; //TBD
    }
    public Exit getExit() {
      return null; //TBD
    }
    public int number;  //could be for an entrence or an exit.
  }
  

  private static final int MAX_SPACES = 100;

  private static final int NUM_OF_ENTRENCES = 5;

  private static final int NUM_OF_EXITS = 4;

  private Car[] parkings = new Car[MAX_SPACES];;

  private Entrence[] entrences = new Entrence[NUM_OF_ENTRENCES];

  private Exit[] exits = new Exit[NUM_OF_EXITS];

  private Display display = new Display();

  public void initialize() {
    for (int i = 0; i < parkings.length; i++) {
      parkings[i] = null;
    }

    for (int i = 0; i < entrences.length; i++) {
      entrences[i].number = i;
      entrences[i].close();
    }

    for (int i = 0; i < exits.length; i++) {
      exits[i].number = i;
      exits[i].close();
    }

    display.update(MAX_SPACES);
  }
  
  public void run() {
    while(true) {
      Event evt = nextEvent();
      if(evt.dir.equals(Direction.ARRIVAL)) {
        handleArrival(evt.getCar(), evt.getEntrence());
      }
      else if (evt.dir.equals(Direction.LEAVING)) {
        handleLeaving(evt.getCar(), evt.getExit());
      }
    }
  }

  public void handleArrival(Car car, Entrence entr) {
    int found = findAvailableSpace();
    if (found != -1) {
      entr.open();
      issueTicket(car);
      entr.close();
      display.minusSpaces();
    }
  }

  public void handleLeaving(Car car, Exit ex) {
    if(verifyPayment(car)) {
      updateAvailableSpace(car);
      display.addSpaces();
      ex.open();
      ex.close();
    }    
  }
  
  private Event nextEvent() {
    return null; //TBD, should involve the sensors system.
  }

  private synchronized int findAvailableSpace() {
    int result = -1;
    for (int i = 0; i < parkings.length; i++) {
      if (parkings[i] == null) {
        result = i;
        break;
      }
    }
    return result;
  }

  private synchronized void updateAvailableSpace(Car car) {
    for (int i = 0; i < parkings.length; i++) {
      if (parkings[i].equals(car)) {
        parkings[i] = null;
      }
    }
  }

  private void issueTicket(Car car) {
     //TBD, to track the parking time, etc.
  }

  private boolean verifyPayment(Car car) {
    //TBD, handling the payment here.
    return true;
  }
  
  public static void main(char[] args) {
    PKMS parkingLot = new PKMS();
    parkingLot.initialize();    
    parkingLot.run();
  }
}
