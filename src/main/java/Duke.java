import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Main driver class for Duke.
 */
public class Duke {
    /**
     * Driver method for Duke.
     * Retrieves list from stored data if available, else start a new list.
     * The TaskList is then parsed with using input.
     */
    private static void run() {
        TaskList dl;

        try {
            dl = Storage.retrieveTasks();
        } catch (FileNotFoundException | ParseException e) {
            dl = new TaskList();
        }

        Parser.parse(dl);
    }

    public static void main(String[] args) {
        System.out.println(DukeStringFormat.HELLO);
        run();
    }
}