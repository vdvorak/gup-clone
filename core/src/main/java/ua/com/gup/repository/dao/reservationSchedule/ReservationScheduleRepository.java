package ua.com.itproekt.gup.dao.reservationSchedule;

import ua.com.itproekt.gup.model.reservationSchedule.ReservationSchedule;

public interface ReservationScheduleRepository {

    ReservationSchedule findById(String id);

    ReservationSchedule update(ReservationSchedule reservationSchedule);
}
