package ParkingLot.services;

import ParkingLot.exceptions.GateNotFoundException;
import ParkingLot.factory.SpotAssignmentStrategyFactory;
import ParkingLot.models.*;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;
    public TicketService(GateRepository gateRepository,VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository){
        this.gateRepository=gateRepository;
        this.vehicleRepository=vehicleRepository;
        this.parkingLotRepository=parkingLotRepository;
        this.ticketRepository=ticketRepository;
    }
    public Ticket issueTicket(Long gateId, String vehicleNumber , String ownerName , VehicleType vehicleType)
            throws GateNotFoundException {
        Ticket ticket=new Ticket();
        ticket.setCreatedAt(new Date());

        // get the GATE object from the database using the GATE ID ,
        // since we are not using the Database we use Hash map to store in memory details.
        Optional<Gate> optionalgate=gateRepository.findByGateId(gateId);
        if(optionalgate.isEmpty()){
            throw new GateNotFoundException("GATE Not Found");
        }
        Gate gate=optionalgate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        //Get the vehicle with the vehicle number , if present, use it otherwise create a new vehicle object
        Vehicle savedVehicle=null;
        Optional<Vehicle> optionalVehicle=vehicleRepository.findVehicleByNumber(vehicleNumber);
        if(optionalVehicle.isEmpty()){
            //This vehicle is coming for the very first time in Parking Lot,
            //Create a Vehicle and save it to the DB.

            Vehicle vehicle=new Vehicle();
            vehicle.setVehicleNo(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(ownerName);
            vehicleRepository.save(vehicle);
            savedVehicle=vehicle;
        }
        else{
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);
        //Assign the Parking Spot.

        ParkingLot parkingLot= parkingLotRepository.getParkingLot();
        SpotAssignmentStrategyType spotAssignmentStrategyType=parkingLot.getSpotAssignmentStrategy();

        SpotAssignmentStrategy spotAssignmentStrategy= SpotAssignmentStrategyFactory
                .getSpotAssignmentStrategy(spotAssignmentStrategyType);


        ParkingSpot parkingSpot=spotAssignmentStrategy.assignSpot(vehicleType,gate);

        ticket.setParkingSpot(parkingSpot);
        ticket.setTicketNo("TICKET_" + gateId + "_" + ticket.getGeneratedAt());
        //Save the ticket to DB.
        ticketRepository.save(ticket);


        return ticket;
    }
}


 /* STEPS :
        1. Get the Vehicle details from the DB.
        2. Get the gate details from the DB.
        3. Create a Ticket object.
        4. Assign the parking spot.
        5. return the ticket object.
         */
