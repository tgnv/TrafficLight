/*
 * Created by Alexey Tugunov on 19.03.17.
 */
package io.khasang.javaweb20170319.tgnv.TrafficLight;

import java.util.Observable;

import static java.lang.Thread.sleep;

public class TrafficLightModel extends Observable implements Runnable {
    private volatile TrafficLightSignal state;

    private volatile int redSignalDuration;
    private volatile int yellowSignalDuration;
    private volatile int greenSignalDuration;


    public TrafficLightModel() {
        state = TrafficLightSignal.GREEN;

        redSignalDuration = 20;
        yellowSignalDuration = 2;
        greenSignalDuration = 20;

        Thread modelTread = new Thread(this, "TrafficLightModel");
        modelTread.start();
    }

    public void run() {
        try {
            while (true) {
                switch (state) {
                    case GREEN:
                        if (greenSignalDuration >= 2) {
                            sleep((greenSignalDuration - 2) * 1000);
                            state = TrafficLightSignal.BLINKING_GREEN;
                        }
                        else {
                            sleep(greenSignalDuration * 1000);
                            state = TrafficLightSignal.YELLOW;
                        }
                        break;
                    case BLINKING_GREEN:
                        sleep(2 * 1000);
                        state = TrafficLightSignal.YELLOW;
                        break;
                    case YELLOW:
                        sleep(yellowSignalDuration * 1000);
                        state = TrafficLightSignal.RED;
                        break;
                    case RED:
                        if (redSignalDuration >= 2) {
                            sleep((redSignalDuration - 2) * 1000);
                            state = TrafficLightSignal.RED_YELLOW;
                        }
                        else {
                            sleep(redSignalDuration * 1000);
                            state = TrafficLightSignal.GREEN;
                        }
                        break;
                    case RED_YELLOW:
                        sleep(2 * 1000);
                        state = TrafficLightSignal.GREEN;
                        break;
                    default:
                        state = TrafficLightSignal.GREEN;
                        break;
                }
                notifyObservers();
            }
        }
        catch (InterruptedException ex1) {
            ex1.printStackTrace();
        }
    }

    public TrafficLightSignal getState() {
        return state;
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
