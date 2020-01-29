import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Duke {
    private static void run() {
        DukeList dl;

        try {
            dl = DukeStorage.retrieveTasks(DukeStorage.filePath);
        } catch (FileNotFoundException | ParseException e) {
            dl = new DukeList();
        }

        InputParser.parse(dl);
    }



    public static void main(String[] args) {
        System.out.println(DukeStringFormat.HELLO);
        run();
    }
}