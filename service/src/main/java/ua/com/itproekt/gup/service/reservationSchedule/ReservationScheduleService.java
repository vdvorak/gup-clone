package ua.com.itproekt.gup.service.reservationSchedule;


import ua.com.itproekt.gup.model.reservationSchedule.ReservationSchedule;

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
