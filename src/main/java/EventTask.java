import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event.
 * An event has a description, a date, and a true or false on whether it is completed.
 */
public class EventTask extends Task {
    private Date deadline;

    /**
     * Creates a EventTask object.
     *
     * @param description The String description of the event.
     * @param isDone      The boolean variable on whether the event is completed.
     * @param deadline    The due Date of the event.
     */
    private EventTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Static method to create an EventTask object.
     * Throws an exception if the input text is not in the required format.
     *
     * @param description The String description of the event.
     * @param text        The String of the date and time of the event.
     * @return An Event.
     * @throws ParseException if parsing of String into Date fails
     */
    static EventTask createEventTask(String description, String text) throws
            ParseException, ArrayIndexOutOfBoundsException {
        assert description != null;
        assert text != null;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        String[] arr = text.split(DukeConstant.DELIMITER_WHITESPACE);
        String hour = arr[1].substring(DukeConstant.TASK_HOUR_START_INDEX, DukeConstant.TASK_HOUR_END_INDEX);
        String minute = arr[1].substring(DukeConstant.TASK_HOUR_END_INDEX);
        String[] date = arr[0].split(DukeConstant.DELIMITER_DATE);

        // Input date is too short/long
        if (date.length != 3) {
            throw new InvalidTaskDateTimeException("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.");
        }

        String day = date[DukeConstant.DATE_DAY_INDEX];
        String month = date[DukeConstant.DATE_MONTH_INDEX];
        String year = date[DukeConstant.DATE_YEAR_INDEX];
        Date deadline = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        return new EventTask(description.trim(), false, deadline);
    }

    /**
     * Converts an EventTask's date to a String.
     *
     * @return A String of the EventTask's deadline.
     */
    private String dateToString() {
        SimpleDateFormat format = new SimpleDateFormat("E HHmm, MMM d, YYYY");
        String string = format.format(deadline);
        String[] arr = string.split(DukeConstant.DELIMITER_COMMA);

        int day = deadline.getDate();
        if (day == DukeConstant.DAY_ONE) {
            arr[1] += DukeConstant.DAY_FIRST;
        } else if (day == DukeConstant.DAY_TWO) {
            arr[1] += DukeConstant.DAY_SECOND;
        } else if (day == DukeConstant.DAY_THREE) {
            arr[1] += DukeConstant.DAY_THIRD;
        } else {
            arr[1] += DukeConstant.DAY_NTH;
        }

        return arr[0] + "," + arr[1] + "," + arr[2];
    }

    /**
     * Sets this EventTask's complete status to true.
     * Overrides the abstract method in Task.
     *
     * @return A new complete EventTask with the same description, deadline as the EventTask instance.
     */
    @Override
    EventTask completeTask() {
        return new EventTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[E] [" + super.getStatusIcon() + "] " + this.description
                + " (at: " + this.dateToString() + ")";
    }
}