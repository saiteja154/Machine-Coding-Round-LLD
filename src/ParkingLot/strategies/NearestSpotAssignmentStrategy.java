package ParkingLot.strategies;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingSpot;
import ParkingLot.models.VehicleType;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
        return null;
    }
        /*
         Go to every floor and spot check if it's available .
         return the first avilable spot.
         */
}
