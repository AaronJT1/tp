package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents a Patient's age in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(String)}
 */
public class Age {
    public static final String MESSAGE_CONSTRAINTS =
            "Age should only contain numbers, and it should not be negative";
    public static final String VALIDATION_REGEX = "\\d+";

    public final Integer value;
    private final String strValue;

    /**
     * Constructs a {@code Age}.
     *
     * @param age A valid age.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(age);
        strValue = age;
    }

    public Age(Birthday birthday) {
        requireNonNull(birthday);
        if (Birthday.isDefaultBirthday(birthday)) {
            this.value = 0; // or -1
            this.strValue = "-";
        } else {
            int yearOfBirth = birthday.value.getYear();
            int currentYear = LocalDate.now().getYear();
            int age = currentYear - yearOfBirth;
            this.value = age;
            this.strValue = value.toString();
        }
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidAge(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return strValue;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Age)) {
            return false;
        }

        Age otherAge = (Age) other;
        return value.equals(otherAge.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
