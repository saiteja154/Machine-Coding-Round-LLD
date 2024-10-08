package ParkingLot.controllers;

import ParkingLot.dtos.IssueTicketRequestDTO;
import ParkingLot.dtos.IssueTicketResponseDTO;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.exceptions.GateNotFoundException;
import ParkingLot.models.Ticket;
import ParkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO) {
        IssueTicketResponseDTO responseDTO=new IssueTicketResponseDTO();

        try {
            Ticket ticket = ticketService.issueTicket(requestDTO.getGateId(), requestDTO.getVehicleNumber(),
                    requestDTO.getVehicleOwner(), requestDTO.getVehicleType());

            responseDTO.setTicket(ticket);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return  responseDTO;
        }
        catch (Exception ex){
            ex.getMessage();
            responseDTO.setResponseStatus(ResponseStatus.FAILED);
           // throw new GateNotFoundException("Gate ");
        }
        return responseDTO;
    }
}
