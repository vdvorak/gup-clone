package ua.com.itproekt.gup.service.reservationSchedule;


import ua.com.itproekt.gup.model.reservationSchedule.ReservationSchedule;

public interface ReservationScheduleService {

    /**
     * @param id
     * @param time
     */
    void add(String id, Long time);

    /**
     *
     */
    void removeOverdue();


    /**
     *
     */
    ReservationSchedule findOne();
}
