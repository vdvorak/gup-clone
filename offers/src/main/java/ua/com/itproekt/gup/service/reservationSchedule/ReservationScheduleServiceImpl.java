package ua.com.itproekt.gup.service.reservationSchedule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.reservationSchedule.ReservationScheduleRepository;
import ua.com.itproekt.gup.model.reservationSchedule.ReservationSchedule;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class ReservationScheduleServiceImpl implements ReservationScheduleService {

    @Autowired
    private ReservationScheduleRepository reservationScheduleRepository;

    private static final String RES_KEY = "reservationSchedule";


    @Override
    public ReservationSchedule findOne() {
        return reservationScheduleRepository.findById(RES_KEY);
    }


    @Override
    public void add(String id, Long time) {
        ReservationSchedule reservationSchedule = findOne();
        reservationSchedule.getPoints().put(id, time);
        reservationScheduleRepository.update(reservationSchedule);
    }

    @Override
    public void removeOverdue() {

        System.err.println("Задание выполнелось в: " + LocalDateTime.now().toInstant(ZoneOffset.UTC));


        ReservationSchedule reservationSchedule = findOne();

        LinkedHashMap<String, Long> reservationSchedulePoints = reservationSchedule.getPoints();

        Long timeNow = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        Iterator<Map.Entry<String, Long>> iterator = reservationSchedulePoints.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();

            if (timeNow > entry.getValue()) {
                iterator.remove();
            } else {
                break;
            }
//            System.err.println(entry.getKey());
        }

        reservationScheduleRepository.update(reservationSchedule);
    }
}