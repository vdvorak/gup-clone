package ua.com.gup.service.reservationSchedule;


import ua.com.gup.model.reservationSchedule.ReservationSchedule;

public interface ReservationScheduleService {

    /**
     * @param id
     */
    void add(String id);

    /**
     *
     */
    void removeOverdue();


    /**
     *
     */
    ReservationSchedule findOne();
}
