package iii_conventions

import com.sun.org.apache.regexp.internal.RE
import javax.swing.ImageIcon

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int {
        // println("this=" + this.toString() + ", that=" + other.toString())
        if (this.year < other.year) return -1
        else if (this.year > other.year) return 1
        if (this.month < other.month) return -1
        else if (this.month > other.month) return 1
        if (this.dayOfMonth < other.dayOfMonth) return -1
        else if (this.dayOfMonth > other.dayOfMonth) return 1
        return 0
    }

    operator fun  inc(): MyDate = nextDay()

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateIterator(var current: MyDate, val endInclusive: MyDate): Iterator<MyDate> {
    override fun hasNext(): Boolean {
        return current <= endInclusive;
    }

    override fun next(): MyDate {
        val returnDate: MyDate = current
        current++
        return returnDate
    }
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : Iterable<MyDate>, ClosedRange<MyDate> {
    override operator fun iterator(): Iterator<MyDate> {
        var iterator: Iterator<MyDate> = DateIterator(start, endInclusive)
        return iterator;
    }
}


// operator fun DateRange.contains(date: MyDate): Boolean = ((start.compareTo(date) <= 0) && (endInclusive.compareTo(date) >= 0))

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(ti: RepeatedTimeIntervals): MyDate = this.addTimeIntervals(ti.timeInterval, ti.times)

class RepeatedTimeIntervals(val timeInterval: TimeInterval, val times:Int)

operator fun TimeInterval.times(times: Int): RepeatedTimeIntervals = RepeatedTimeIntervals(this, times)