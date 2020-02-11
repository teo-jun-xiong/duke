import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task with a deadline when it is due.
 * A deadline task has a description, a deadline date, and a true or false on whether it is completed.
 */
public class DeadlineTask extends Task {
    private Date deadline;

    /**
     * Creates a DeadlineTask object.
     *
     * @param description The String description of the deadline task.
     * @param isDone      The boolean variable on whether the deadline task is completed.
     * @param deadline    The due Date of the deadline task.
     */
    private DeadlineTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Static method to create a DeadlineTask object.
     * Throws an exception if the input text is not in the required format.
     *
     * @param description The String description of the deadline task.
     * @param text        The String of the date and time of the deadline.
     * @return A DeadlineTask.
     * @throws ParseException if parsing of String into Date fails
     */
    static DeadlineTask createDeadlineTask(String description, String text) throws ParseException {
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
        return new DeadlineTask(description, false, deadline);
    }

    /**
     * Converts a DeadlineTask's deadline to a String.
     *
     * @return A String of the DeadlineTask's deadline.
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
     * Sets this DeadlineTask's complete status to true.
     * Overrides the abstract method in Task.
     *
     * @return A new complete DeadlineTask with the same description, deadline as the DeadlineTask instance.
     */
    @Override
    DeadlineTask completeTask() {
        return new DeadlineTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[D] [" + super.getStatusIcon() + "] " + this.description
                + " (by: " + this.dateToString() + ")";
    }
}