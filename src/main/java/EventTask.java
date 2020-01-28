import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventTask extends Task {
    private Date deadline;

    private EventTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    static EventTask createEventTask(String description, String text) throws ParseException, ArrayIndexOutOfBoundsException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        String[] arr = text.split(" ");
        String hour = arr[2].substring(0, 2);
        String minute = arr[2].substring(2);
        String[] date = arr[1].split("/");
        String day = date[0];
        String month = date[1];
        String year = date[2];
        Date deadline = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        return new EventTask(description, false, deadline);
    }

    private String deadlineToString() {
        SimpleDateFormat format = new SimpleDateFormat("E HHmm, MMM d, YYYY");
        String string = format.format(deadline);
        String[] arr = string.split(",");

        int day = deadline.getDate();
        if (day == 1) {
            arr[1] += "st";
        } else if (day == 2) {
            arr[1] += "nd";
        } else if (day == 3) {
            arr[1] += "rd";
        } else {
            arr[1] += "th";
        }

        return arr[0] + "," + arr[1] + "," + arr[2];
    }

    @Override
    EventTask completeTask() {
        return new EventTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[D] [" + super.getStatusIcon() + "] " + this.description
                + " (at: " + this.deadlineToString() + ")";
    }
}