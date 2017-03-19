/*
 * Created by Alexey Tugunov on 19.03.17.
 */
package io.khasang.javaweb20170319.tgnv.TrafficLight;

import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;
import static java.lang.Thread.sleep;


public class TrafficLightView extends Observable implements Runnable {

    private volatile TrafficLightSignal state;

    private volatile int redSignalDuration;
    private volatile int yellowSignalDuration;
    private volatile int greenSignalDuration;


    public TrafficLightView(TrafficLightSignal state, int redSignalDuration, int yellowSignalDuration, int greenSignalDuration) {
        this.state = state;
        this.redSignalDuration = redSignalDuration;
        this.yellowSignalDuration = yellowSignalDuration;
        this.greenSignalDuration = greenSignalDuration;
        Thread viewThread = new Thread(this, "TrafficLightView");
        viewThread.start();
    }

    public void run() {
        showGreetings();
        System.out.println("");
        System.out.println("Please enter the Traffic Light parameters:");
        if (showSettingsDialog()) {
            setChanged();
            notifyObservers();
        }
        while (true) {
            clearScreen();
            System.out.println("\u250f\u2501\u2501\u2501\u2513");

            System.out.print("\u2503 ");
            if (state == TrafficLightSignal.RED || state == TrafficLightSignal.RED_YELLOW) {
                System.out.print("\u001b[1m\u001b[31m");
            }
            System.out.println("\u25cf\u001b[0m \u2503");

            System.out.println("\u2523\u2501\u2501\u2501\u252b");

            System.out.print("\u2503 ");
            if (state == TrafficLightSignal.YELLOW || state == TrafficLightSignal.RED_YELLOW) {
                System.out.print("\u001b[1m\u001b[33m");            }
            System.out.println("\u25cf\u001b[0m \u2503");

            System.out.println("\u2523\u2501\u2501\u2501\u252b");

            System.out.print("\u2503 ");
            if (state == TrafficLightSignal.GREEN) {
                System.out.print("\u001b[1m\u001b[32m");
            } else if (state == TrafficLightSignal.BLINKING_GREEN) {
                System.out.print("\u001b[1;5;32m");
            }
            System.out.println("\u25cf\u001b[0m \u2503");

            System.out.println("\u2517\u2501\u2501\u2501\u251b");

            try {
                sleep(1000);
            }
            catch (InterruptedException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public void setState(TrafficLightSignal state) {
        this.state = state;
    }

    public int getGreenSignalDuration() {
        return greenSignalDuration;
    }

    public int getYellowSignalDuration() {
        return yellowSignalDuration;
    }

    public int getRedSignalDuration() {
        return redSignalDuration;
    }

    public void setYellowSignalDuration(int yellowSignalDuration) {
        this.yellowSignalDuration = yellowSignalDuration;
    }

    public void setRedSignalDuration(int redSignalDuration) {
        this.redSignalDuration = redSignalDuration;
    }

    public void setGreenSignalDuration(int greenSignalDuration) {
        this.greenSignalDuration = greenSignalDuration;
    }

    private void clearScreen() {
        System.out.print("\u001b[1;1H\u001b[2J");
    }

    private void showGreetings() {
        clearScreen();
        System.out.println("Welcome to Traffic Light application");
        System.out.println("This simple application developed by Alexey Tugunov as member of Khasang.geekteams JavaWeb20170319 educational group");
    }

    private boolean showSettingsDialog() {
        System.out.println("");
        int tmpRedSignalDuration = requestIntegerWithPrompt("Red signal duration", redSignalDuration);
        int tmpYellowSignalDuration = requestIntegerWithPrompt("Yellow signal duration", yellowSignalDuration);
        int tmpGreenSignalDuration = requestIntegerWithPrompt("Green signal duration", greenSignalDuration);
        if (requestAcceptanceWithPrompt("Save settings")) {
            redSignalDuration = tmpRedSignalDuration;
            yellowSignalDuration = tmpYellowSignalDuration;
            greenSignalDuration = tmpGreenSignalDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean requestAcceptanceWithPrompt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String s;
        boolean result;

        while (true) {
            System.out.print(prompt + " (yes):");
            try {
                s = scanner.nextLine();
            } catch (NoSuchElementException ex1) {
                System.out.println("Choose yes or no");
                continue;
            } catch (IllegalStateException ex2) {
                System.out.println("Choose yes or no");
                continue;
            }
            if (s.length() == 0) {
                result = true;
                break;
            }
            if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y") || s.equalsIgnoreCase("1")) {
                result = true;
                break;
            } else if (s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n") || s.equalsIgnoreCase("not") || s.equalsIgnoreCase("0")) {
                result = false;
                break;
            } else {
                System.out.println("Choose yes or no");
                continue;
            }
        }
        return result;
    }


    private int requestIntegerWithPrompt(String prompt, int defaultValue) {
        Scanner scanner = new Scanner(System.in);
        String s;
        int result;

        while (true) {
            System.out.print(prompt + " (" + defaultValue + "): ");
            try {
                s = scanner.nextLine();
            } catch (NoSuchElementException ex1) {
                System.out.println("Signal duration must be entered as positive integer in seconds");
                continue;
            } catch (IllegalStateException ex2) {
                System.out.println("Signal duration must be entered as positive integer in seconds");
                continue;
            }
            if (s.length() == 0) {
                result = defaultValue;
                break;
            }
            try {
                result = Integer.parseInt(s);
            } catch (NumberFormatException ex1) {
                System.out.println("Signal duration must be entered as positive integer in seconds");
                continue;
            }
            if (result < 0) {
                System.out.println("Signal duration must be entered as positive integer in seconds");
                continue;
            }
            break;
        }
        return result;
    }

}
