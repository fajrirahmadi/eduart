package arloji.life.smarthumanity.utils.time

import android.annotation.SuppressLint
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Instant
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        fun getDateTimeByInstantParse(dateTime: String): DateTime {
            return DateTime(Instant.parse(dateTime).getMillis())
        }

        fun getDateFormated(format: String, time: Long): String {
            return getDateFormated("in", format, time)
        }

        fun getDateFormatedWithMultiply(format: String, time: Long): String {
            return getDateFormated("in", format, time * 1000L)
        }

        fun getDateFormated(language: String, format: String, time: Long): String {
            val id = Locale(language, "ID")
            return SimpleDateFormat(format, id).format(Date(time))
        }

        fun compareBetween(start: Int, end: Int): Boolean {
            val dateTimeNow = DateTime.now()
            return dateTimeNow.getDayOfMonth() >= start && dateTimeNow.getDayOfMonth() <= end
        }

        fun compareBetween(endDate: Long, rangeTime: Int): Boolean {
            val timeNow =
                DateTime(Calendar.getInstance().timeInMillis).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                    .withMillisOfSecond(0)
            val timeExpired = DateTime(endDate * 1000).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0)
            return Days.daysBetween(timeNow, timeExpired).getDays() <= rangeTime
        }

        fun getDateTime(): String {
            val dateTimeNow = DateTime.now()
            return getDateFormated("yyyy-MM-dd HH:mm:ss", dateTimeNow.getMillis())
        }

        @SuppressLint("SimpleDateFormat")
        fun getSimpleDateFormat(format: String): SimpleDateFormat {
            return SimpleDateFormat(format)
        }

        fun isAfterNowWithMultiply(expiredDate: Long?): Boolean? {
            val timeNow =
                DateTime(Calendar.getInstance().timeInMillis).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                    .withMillisOfSecond(0)
            val timeExpired = DateTime(expiredDate!! * 1000).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0).plusDays(1)
            return timeNow.isAfter(timeExpired)
        }

        fun getTodayFormatted(format: String): String {
            val dateTimeNow = DateTime.now()
            return getDateFormated(format, dateTimeNow.getMillis())
        }

        fun isThisMonth(date: Long?): Boolean {
            val dateNow = DateTime.now()
            val dateCompare = DateTime(date)
            return dateNow.monthOfYear() == dateCompare.monthOfYear() && dateNow.year() == dateCompare.year()
        }

        fun getStartedMonth(additionalMonth: Int): Long {
            var dateNow = DateTime.now().withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0)
            dateNow = dateNow.plusMonths(additionalMonth)
            return dateNow.millis
        }

        fun getDateFromCalendar(calendarDay: CalendarDay, additionalMonth: Int): Long {
            var dateNow = DateTime.now().withYear(calendarDay.year).withMonthOfYear(calendarDay.month).withDayOfMonth(1)
                .withMillisOfDay(0)
            dateNow = dateNow.plusMonths(additionalMonth)
            return dateNow.millis
        }

        fun getBirthDateLimit(): Long {
            var dateNow = DateTime.now().withMillisOfDay(0)
            dateNow = dateNow.minusYears(10)

            return dateNow.millis
        }
    }
}