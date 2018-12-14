package view;

/**
 * Interface of an observer to vehicle events
 */
public interface VehicleObserver {

    /**
     * Called every time the observable broadcasts a vehicle event
     *
     * @param actionEnum    The action
     */
    void onVehicleAction(VehicleEvents actionEnum);
}