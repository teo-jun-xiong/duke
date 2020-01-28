import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Duke {
    private static void parseInput() {
        File filePath = new File("data/myTasks.txt");
        Scanner sc = new Scanner(System.in);
        DukeList dl;

        try {
            dl = retrieveTasks(filePath);
        } catch (FileNotFoundException | ParseException e) {
            dl = new DukeList();
        }

        while (sc.hasNext()) {
            String command = sc.next();

            switch (command) {
                // Prints current list of tasks
                case "list":
                    sc.nextLine();
                    System.out.println(DukeStringFormat.DIVIDER);
                    dl.printList();
                    System.out.println(DukeStringFormat.DIVIDER);
                    break;

                case "bye":
                    sc.nextLine();
                    System.out.println(DukeStringFormat.DIVIDER + DukeStringFormat.BYE
                            + DukeStringFormat.DIVIDER);
                    break;

                case "done":
                    String str = sc.nextLine().trim();

                    try {
                        int index = Integer.parseInt(str) - 1;

                        if (index >= dl.listSize()) {
                            throw new DukeListIndexOutOfBoundsException(
                                    "   (✖╭╮✖) There are only " + dl.listSize() + " items in the list!");
                        }

                        dl = dl.setDone(index);
                        System.out.println(DukeStringFormat.DIVIDER);
                        System.out.println(DukeStringFormat.DONE);
                        System.out.println("      " + dl.printTask(index));
                        System.out.println(DukeStringFormat.DIVIDER);
                    } catch (DukeListIndexOutOfBoundsException e) {
                        System.out.println(DukeStringFormat.DIVIDER
                                + e.getMessage() + "\n"
                                + DukeStringFormat.DIVIDER);
                    }

                    break;

                case "todo":
                case "deadline":
                case "event":
                    String description = sc.nextLine();
                    try {
                        if (description.replace("\n", "").replace(" ", "").length() == 0) {
                            throw new MissingDescriptionException("༼ つ◕_◕ ༽つ The description of a " + command
                                    + " cannot be empty.\n   Please try again!");
                        } else {
                            Task task;
                            if (command.equals("todo")) {
                                task = new ToDoTask(description, false);
                            } else {
                                String[] arr = description.split(" /");

                                if (arr.length == 1 || (arr.length > 1 && arr[1].split(" ").length <= 1)) {
                                    throw new MissingDateTimeException("(''⊙＿⊙) The date and time of the "
                                            + command + " is missing.\n   Please try again!");
                                }

                                String[] splitting = arr[1].split(" ");
                                String parameter = splitting[0] + ": " + splitting[1] + " " + splitting[2];


                                task = command.equals("deadline")
                                        ? DeadlineTask.createDeadlineTask(arr[0], parameter)
                                        : EventTask.createEventTask(arr[0], parameter);
                            }

                            dl = dl.addToList(task);
                            System.out.println(DukeStringFormat.DIVIDER
                                    + DukeStringFormat.ADDED
                                    + "      " + task.toString()
                                    + "\n\n   Now you have " + dl.listSize()
                                    + (dl.listSize() == 1 ? " task" : " tasks")
                                    + " in the list.\n"
                                    + DukeStringFormat.DIVIDER);
                        }

                    } catch (MissingDescriptionException | MissingDateTimeException e) {
                        System.out.println(DukeStringFormat.DIVIDER
                                + "   " + e.getMessage() + "\n"
                                + DukeStringFormat.DIVIDER);
                    } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                        System.out.println(DukeStringFormat.DIVIDER
                                + "   (•̀ ᴗ •́ )و̑ The date and/or time format is invalid.\n   Please format your input as: DD/MM/YYYY HHmm.\n"
                                + DukeStringFormat.DIVIDER);
                    }
                    break;

                case "delete":
                    String delete = sc.nextLine().trim();

                    try {
                        int index = Integer.parseInt(delete) - 1;

                        if (index >= dl.listSize()) {
                            throw new DukeListIndexOutOfBoundsException(
                                    "   (✖╭╮✖) There are only " + dl.listSize() + " items in the list!");
                        }

                        System.out.println(DukeStringFormat.DIVIDER
                                + DukeStringFormat.DELETED + "      " + dl.printTask(index)
                                + "\n   Now you have " + (dl.listSize() - 1)
                                + (dl.listSize() - 1 == 1 ? " task" : " tasks")
                                + " in the list.\n"
                                + DukeStringFormat.DIVIDER);
                        dl = dl.deleteTask(index);

                    } catch (DukeListIndexOutOfBoundsException e) {
                        System.out.println(DukeStringFormat.DIVIDER
                                + e.getMessage() + "\n"
                                + DukeStringFormat.DIVIDER);
                    }

                    break;

                default:
                    try {
                        command = command + sc.nextLine();
                        throw new InvalidCommandArgumentException("(つ╥﹏╥)つ Hey, I can't do that for you. " +
                                "\n   I don't know " + command + "...");
                    } catch (InvalidCommandArgumentException e) {
                        System.out.println(DukeStringFormat.DIVIDER
                                + "   " + e.getMessage() + "\n"
                                + DukeStringFormat.DIVIDER);
                    }
                    break;
            }

            if (command.equals("bye")) {
                try {
                    writeTasks(dl, filePath);
                } catch (IOException e) {
                    System.err.println(DukeStringFormat.DIVIDER
                        + "   (・_・;) Something went wrong!\n"
                        + DukeStringFormat.DIVIDER);
                }

                break;
            }
        }

        sc.close();
    }

    private static void writeTasks(DukeList dl, File filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dl.listSize(); i++) {
            String string = dl.printTask(i);
            char taskType = string.charAt(1);
            int isComplete = dl.isTaskDone(i) ? 1 : 0;
            String[] arr = string.split("\\(");
            String taskDescription = arr[0].substring(8).trim();
            String taskInString = taskType + " | " + isComplete + " | " + taskDescription;

            if (Character.toUpperCase(taskType) != 'T') {
                String time = arr[1].split(":")[1];
                time = time.trim().substring(0, time.length() - 2);
                taskInString += " | " + time;
            }

            taskInString += "\n";
            sb.append(taskInString);
        }

        System.out.println(sb.toString());
        fw.write(sb.toString());
        fw.close();
    }

    private static DukeList retrieveTasks(File filePath) throws FileNotFoundException, ParseException {
        DukeList dl = new DukeList();
        Scanner sc = new Scanner(filePath);

        while (sc.hasNextLine()) {
            String[] details = sc.nextLine().split("\\|");
            boolean isTaskDone = Integer.parseInt(details[1].trim()) == 1;

            if (details[0].trim().equals("T")) {
                dl.addToList(new ToDoTask(details[2], isTaskDone));
            } else if (details[0].trim().equals("E")) {
                dl.addToList(EventTask.createEventTask(details[2], details[3]));
            } else {
                dl.addToList(DeadlineTask.createDeadlineTask(details[2], details[3]));
            }
        }

        return dl;
    }

    public static void main(String[] args) {
        System.out.println(DukeStringFormat.HELLO);
        parseInput();
    }
}