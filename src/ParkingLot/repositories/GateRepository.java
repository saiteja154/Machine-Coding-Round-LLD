package ParkingLot.repositories;

import ParkingLot.models.Gate;

import java.util.HashMap;
import java.util.Optional;

public class GateRepository {
    private HashMap<Long, Gate> gateMap=new HashMap<>();

    public Optional<Gate> findByGateId(Long id){
        if(gateMap.containsKey(id)){
            return Optional.of(gateMap.get(id));
        }
        return Optional.empty();
    }
}
