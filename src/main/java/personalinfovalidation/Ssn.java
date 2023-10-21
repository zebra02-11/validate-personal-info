package personalinfovalidation;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class used to validate Swedish personal identity numbers.
 *
 * @author Daria Kolesnik
 */
public class Ssn  {
    private final int realDay;
    private final String fullYear;
    private final String century;
    private final String year;
    private final String month;
    private final String day;
    private final String numbers;
    private final String controlNumber;


    public Integer getRealDay() {
        return realDay;
    }

    public String separator() {
        return this.getAge() >= 100 ? "+" : "-";
    }

    public String getFullYear() {
        return fullYear;
    }

    public String getYear() {
        return year;
    }

    public String getCentury() {
        return century;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public int getAge() {
        return (LocalDate.of(Integer.parseInt(fullYear), Integer.parseInt(month), realDay).until(LocalDate.now())).getYears();
    }

    /**
     * Create a new Personnummber object from a string.
     * In case options is not passed, they will default to accept any personal and coordination numbers.
     *
     */
    public Ssn(int realDay,
               String century,
               String decade,
               String fullYear,
               String month,
               String day,
               String numbers,
               String controlNumber) {
        this.realDay = realDay;
        this.century = century;
        this.year = decade;
        this.fullYear = fullYear;
        this.month = month;
        this.day = day;
        this.numbers = numbers;
        this.controlNumber = controlNumber;
    }


    /**
     * Format the personal identity number into a valid string (YYMMDD-/+XXXX)
     * If longFormat is true, it will include the century (YYYYMMDD-/+XXXX)
     *
     * @param longFormat If century should be included.
     * @return Formatted personal identity number.
     */
    public String format(boolean longFormat) {
        return (longFormat ? fullYear : year) + month + day + (longFormat ? "" : separator()) + numbers;
    }

    @Override
    public int hashCode() {
        return format(true).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ssn other = (Ssn) obj;
        return Objects.equals(format(true), other.format(true));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
