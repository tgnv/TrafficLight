/*
 * Created by Alexey Tugunov on 18.03.17.
 */
package io.khasang.javaweb20170319.tgnv.TrafficLight;


public class TrafficLight {

    private final String CSI = "\u001b["; // define ANSI Terminal Control Sequence Introducer

    private TrafficLightSignal state;

    private volatile int redSignalDuration;
    private volatile int yellowSignalDuration;
    private volatile int greenSignalDuration;


    private TrafficLight() {
        state = TrafficLightSignal.GREEN;

        redSignalDuration = 20;
        yellowSignalDuration = 2;
        greenSignalDuration = 20;

    }

    public static void main(String[] args) {
        new TrafficLight();
    }

    public int getGreenSignalDuration() {
        return greenSignalDuration;
    }

    public void setGreenSignalDuration(int greenSignalDuration) {
        this.greenSignalDuration = greenSignalDuration;
    }

    public int getYellowSignalDuration() {
        return yellowSignalDuration;
    }

    public void setYellowSignalDuration(int yellowSignalDuration) {
        this.yellowSignalDuration = yellowSignalDuration;
    }

    public int getRedSignalDuration() {
        return redSignalDuration;
    }

    public void setRedSignalDuration(int redSignalDuration) {
        this.redSignalDuration = redSignalDuration;
    }
}

