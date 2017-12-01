package ua.com.gup.rent.config.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public final class RentJSR310DateConverters {
    private RentJSR310DateConverters() {
    }

    public static class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
        public static final RentJSR310DateConverters.DateToLocalDateTimeConverter INSTANCE = new RentJSR310DateConverters.DateToLocalDateTimeConverter();

        public DateToLocalDateTimeConverter() {
        }

        public LocalDateTime convert(Date source) {
            return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
        public static final RentJSR310DateConverters.LocalDateTimeToDateConverter INSTANCE = new RentJSR310DateConverters.LocalDateTimeToDateConverter();

        public LocalDateTimeToDateConverter() {
        }

        public Date convert(LocalDateTime source) {
            return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        public static final RentJSR310DateConverters.DateToZonedDateTimeConverter INSTANCE = new RentJSR310DateConverters.DateToZonedDateTimeConverter();

        public DateToZonedDateTimeConverter() {
        }

        public ZonedDateTime convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        public static final RentJSR310DateConverters.ZonedDateTimeToDateConverter INSTANCE = new RentJSR310DateConverters.ZonedDateTimeToDateConverter();

        public ZonedDateTimeToDateConverter() {
        }

        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    public static class DateToLocalDateConverter implements Converter<Date, LocalDate> {
        public static final RentJSR310DateConverters.DateToLocalDateConverter INSTANCE = new RentJSR310DateConverters.DateToLocalDateConverter();

        public DateToLocalDateConverter() {
        }

        public LocalDate convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()).toLocalDate();
        }
    }

    public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {
        public static final RentJSR310DateConverters.LocalDateToDateConverter INSTANCE = new RentJSR310DateConverters.LocalDateToDateConverter();

        public LocalDateToDateConverter() {
        }

        public Date convert(LocalDate source) {
            return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
}
