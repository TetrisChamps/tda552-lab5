package gui;

/**
 * Interface of an observer to vehicle events
 */
public interface VehicleObserver {

    /**
     * Called every time the observable broadcasts a vehicle event
     */
    void onVehicleAction(VehicleEvents actionEnum);
}