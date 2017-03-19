/*
 * Created by Alexey Tugunov on 18.03.17.
 */
package io.khasang.javaweb20170319.tgnv.TrafficLight;


import java.util.Observable;
import java.util.Observer;

public class TrafficLight implements Observer {

    private TrafficLightModel model;
    private TrafficLightView view;

    public static void main(String[] args) {
        new TrafficLight();
    }

    public TrafficLight() {
        model = new TrafficLightModel();
        model.addObserver(this);
        view = new TrafficLightView(
                model.getState(),
                model.getRedSignalDuration(),
                model.getYellowSignalDuration(),
                model.getGreenSignalDuration());
        view.addObserver(this);
    }

    public void update(Observable obs, Object obj) {
        if (obs.equals(model)) {
            view.setState(model.getState());
        }
        else if (obs.equals(view)) {
            model.setRedSignalDuration(view.getRedSignalDuration());
            model.setYellowSignalDuration(view.getYellowSignalDuration());
            model.setGreenSignalDuration(view.getGreenSignalDuration());
        }
    }
}


