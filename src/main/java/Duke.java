import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Main driver class for Duke.
 */
public class Duke {
    static TaskList dl;

    /**
     * Driver method for Duke.
     * Retrieves list from stored data if available, else start a new list.
     * The TaskList is then parsed with using input.
     */
    void init() {
        try {
            dl = Storage.retrieveTasks();
        } catch (FileNotFoundException | ParseException e) {
            dl = new TaskList();
        }
    }

    private String executeCommand(String input) {
        return Parser.parse(input);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return executeCommand(input);
    }
}