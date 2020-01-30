import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

class Parser {
    static void parse(TaskList dl) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();

        while (sc.hasNext()) {
            String command = ui.readCommand(sc);

            switch (command) {
                // Prints current list of tasks
                case "list":
                    ui.printList(sc, dl);
                    break;

                case "bye":
                    ui.printBye(sc);
                    break;

                case "done":
                    String str = ui.readTaskIndex(sc);

                    try {
                        if (str.length() == 0) {
                            throw new DukeListIndexOutOfBoundsException("   No index entered. Please try again.");
                        }
                        int index = Integer.parseInt(str) - 1;

                        if (index < 1) {
                            throw new DukeListIndexOutOfBoundsException("   Invalid index entered. Please try again.");
                        }

                        if (index >= dl.listSize()) {
                            throw new DukeListIndexOutOfBoundsException(
                                    "   There are only " + dl.listSize() + " items in the list!");
                        }

                        dl = dl.setDone(index);
                       ui.printDone(dl, index);
                    } catch (DukeListIndexOutOfBoundsException e) {
                        ui.printErrorMessage(e);
                    }

                    break;

                case "todo":
                case "deadline":
                case "event":
                    String description = ui.readTaskDescription(sc);
                    try {
                        if (description.replace("\n", "").replace(" ", "").length() == 0) {
                            throw new MissingDescriptionException("The description of a " + command
                                    + " cannot be empty.\n   Please try again!");
                        } else {
                            Task task;
                            if (command.equals("todo")) {
                                task = new ToDoTask(description, false);
                            } else {
                                String[] arr = description.split(" /by| /at");

                                if (arr.length == 0 || arr[0].split(" ").length <= 1) {
                                    throw new MissingDateTimeException("The date and time of the "
                                            + command + " is missing.\n   Please try again!");
                                }

                                String[] splitting = arr[1].split(" ");
                                String parameter = splitting[0] + splitting[1] + " " + splitting[2];


                                task = command.equals("deadline")
                                        ? DeadlineTask.createDeadlineTask(arr[0], parameter)
                                        : EventTask.createEventTask(arr[0], parameter);
                            }

                            dl = dl.addToList(task);
                            ui.printTaskAdded(dl, task);
                        }
                    } catch (MissingDescriptionException | MissingDateTimeException e) {
                        ui.printErrorMessage(e);
                    } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                        ui.printDateTimeErrorMessage();
                    }

                    break;

                case "delete":
                    String delete = ui.readTaskIndex(sc);

                    try {
                        if (delete.length() == 0) {
                            throw new DukeListIndexOutOfBoundsException("   No index entered. Please try again.");
                        }
                        int index = Integer.parseInt(delete) - 1;

                        if (index < 1) {
                            throw new DukeListIndexOutOfBoundsException("   Invalid index entered. Please try again.");
                        }

                        if (index >= dl.listSize()) {
                            throw new DukeListIndexOutOfBoundsException(
                                    "   There are only " + dl.listSize() + " items in the list!");
                        }

                        ui.printDelete(dl, index);
                        dl = dl.deleteTask(index);

                    } catch (DukeListIndexOutOfBoundsException e) {
                        ui.printErrorMessage(e);
                    }

                    break;

                default:
                    try {
                        command = command + sc.nextLine();
                        throw new InvalidCommandArgumentException("Hey, I can't do that for you. " +
                                "\n   I don't know " + command + "...");
                    } catch (InvalidCommandArgumentException e) {
                        ui.printErrorMessage(e);
                    }

                    break;
            }

            if (command.equals("bye")) {
                try {
                    Storage.writeTasks(dl);
                } catch (IOException e) {
                    ui.printWriteErrorMessage();
                }

                break;
            }
        }

        sc.close();
    }
}
