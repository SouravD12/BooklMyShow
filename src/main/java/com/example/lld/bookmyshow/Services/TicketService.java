package com.example.lld.bookmyshow.Services;


import com.example.lld.bookmyshow.Repositories.ShowSeatRepository;
import com.example.lld.bookmyshow.Repositories.TicketRepository;
import com.example.lld.bookmyshow.Repositories.UserRepository;
import com.example.lld.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.lld.bookmyshow.exceptions.UserNotFoundException;
import com.example.lld.bookmyshow.models.Seat;
import com.example.lld.bookmyshow.models.SeatInShow;
import com.example.lld.bookmyshow.models.Ticket;
import com.example.lld.bookmyshow.models.User;
import com.example.lld.bookmyshow.models.enums.SeatStatus;
import com.zaxxer.hikari.util.IsolationLevel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;


import java.io.Serializable;
import java.util.*;

@Service
public class TicketService {

    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    @Autowired
    public TicketService(ShowSeatRepository showSeatRepository, UserRepository userRepository, TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
        this.showSeatRepository=showSeatRepository;
        this.userRepository=userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List <Long> showSeatIds, Long userId) throws ShowSeatNotAvailableException, UserNotFoundException {
        // 1. Get showSeats of selected IDs
        // 2. Check if they are available or locked 10 mins ago.
        // 3. If not available, send back with an exception
        // 4. else Lock them
        // 5. Prepare dummy ticket
        // 6. Return Ticket

        List<SeatInShow> selectedSeats= showSeatRepository.findAllById(showSeatIds);

        for (SeatInShow seat: selectedSeats){
            if (IsSeatAvailable(seat)==false){
                // problem and throw the exception
                throw new ShowSeatNotAvailableException();
            }
        }

        Optional<User> bookedBy= userRepository.findById(userId);

        if (bookedBy.isEmpty()){
            throw new UserNotFoundException();
        }

        List <SeatInShow> selectedUpdatedSeats = new ArrayList<>();
        for (SeatInShow seat: selectedSeats){
            seat.setSeatStatus(SeatStatus.LOCKED);
            seat.setStatusUpdatedAt(new Date());
            seat=showSeatRepository.save(seat);
            selectedUpdatedSeats.add(seat);
        }

        Ticket ticket= new Ticket();
        ticket.setBookedBy(bookedBy.get());
        ticket.setBookingTime(new Date());
        ticket.setSeats(selectedUpdatedSeats);
        ticket=ticketRepository.save(ticket);

        return ticket;

    }

    private boolean IsSeatAvailable(SeatInShow showSeat){
        if (showSeat.getSeatStatus()== SeatStatus.AVAILABLE){
            return true;
        }
        else if (showSeat.getSeatStatus()==SeatStatus.LOCKED){
            Long LockedAt= showSeat.getStatusUpdatedAt().getTime();
            Long currentTime= System.currentTimeMillis();
            Long duration=currentTime-LockedAt;
            Long durationInSeconds= duration/1000;
            Long durationInMinutes=duration/60;

            if (durationInMinutes>=10){
                return true;
            }
        }
        return  false;
    }
}
