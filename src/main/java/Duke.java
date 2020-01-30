import java.io.FileNotFoundException;
import java.text.ParseException;

public class Duke {
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