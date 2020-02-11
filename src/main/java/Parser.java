import java.io.IOException;
import java.text.ParseException;

/**
 * A class to accept and execute a user's commands.
 */
class Parser {
    /**
     * Parses and then executes a user's commands.
     */
    static String parse(String input) {
        assert input != null;
        String command = Ui.readCommand(input);
        StringBuilder sb = new StringBuilder();

        switch (command) {
            // Prints current list of tasks
            case DukeConstant.LIST_COMMAND:
                sb.append(Ui.printList(Duke.dl));
                break;

            case DukeConstant.CLEAR_COMMAND:
                Duke.dl = new TaskList();
                sb.append(Ui.printClear());
                break;

            case DukeConstant.BYE_COMMAND:
                try {
                    Storage.writeTasks(Duke.dl);
                } catch (IOException e) {
                    sb.append(Ui.printWriteErrorMessage());
                }

                sb.append(Ui.printBye());
                break;

            case DukeConstant.FIND_COMMAND:
                String keywords = Ui.readKeyword(input.substring(command.length()));
                TaskList found = Duke.dl.find(keywords);
                sb.append(Ui.printFind(keywords, found));
                break;

            case DukeConstant.DONE_COMMAND:
                String str = Ui.readTaskIndex(input.substring(command.length()));

                try {
                    if (str.length() == 0) {
                        throw new TaskListIndexOutOfBoundsException("   No index entered. Please try again.");
                    }
                    int index = Integer.parseInt(str) - 1;

                    if (index < 0) {
                        throw new TaskListIndexOutOfBoundsException("   Invalid index entered. Please try again.");
                    }

                    if (index >= Duke.dl.listSize()) {
                        throw new TaskListIndexOutOfBoundsException(
                                "   There are only " + Duke.dl.listSize() + " items in the list!");
                    }

                    Duke.dl = Duke.dl.setDone(index);
                    sb.append(Ui.printDone(Duke.dl, index));
                } catch (TaskListIndexOutOfBoundsException e) {
                    sb.append(Ui.printErrorMessage(e));
                }

                break;

            case DukeConstant.TODO_COMMAND:
            case DukeConstant.DEADLINE_COMMAND:
            case DukeConstant.EVENT_COMMAND:
                String description = Ui.readTaskDescription(input.substring(command.length()));

                try {
                    if (description.replace("\n", "").replace(" ", "").length() == 0) {
                        throw new MissingDescriptionException("The description of a " + command
                                + " cannot be empty.\n   Please try again!");
                    } else {
                        Task task;
                        if (command.equals(DukeConstant.TODO_COMMAND)) {
                            task = new ToDoTask(description, false);
                        } else {
                            if (!description.contains(DukeConstant.DELIMITER_BY)
                                    && !description.contains(DukeConstant.DELIMITER_AT)) {
                                throw new MissingDateTimeException("   Missing \"/by\" or \"/at\" in task description.\n   Please try again.");
                            }

                            String[] arr = description.split("/by|/at");

                            if (arr.length == 0 || arr[1].split(DukeConstant.DELIMITER_WHITESPACE).length != 3) {
                                throw new MissingDateTimeException("   The date and time of the "
                                        + command + " is missing.\n   Please try again!\n\n"
                                        + DukeString.SAMPLE);
                            }

                            String[] splitting = arr[1].trim().split(DukeConstant.DELIMITER_WHITESPACE);
                            String parameter = splitting[0] + " " + splitting[1];


                            task = command.equals(DukeConstant.DEADLINE_COMMAND)
                                    ? DeadlineTask.createDeadlineTask(arr[0], parameter)
                                    : EventTask.createEventTask(arr[0], parameter);
                        }

                        Duke.dl = Duke.dl.addToList(task);
                        sb.append(Ui.printTaskAdded(Duke.dl, task));
                    }
                } catch (MissingDescriptionException | MissingDateTimeException | InvalidTaskDateTimeException e) {
                    sb.append(Ui.printErrorMessage(e));
                } catch (ParseException e) {
                    sb.append(Ui.printDateTimeErrorMessage());
                }

                break;

            case DukeConstant.DELETE_COMMAND:
                String delete = Ui.readTaskIndex(input.substring(command.length()));

                try {
                    if (delete.length() == 0) {
                        throw new TaskListIndexOutOfBoundsException("   No index entered. Please try again.");
                    }
                    int index = Integer.parseInt(delete) - 1;

                    if (index < 0) {
                        throw new TaskListIndexOutOfBoundsException("   Invalid index entered. Please try again.");
                    }

                    if (index >= Duke.dl.listSize()) {
                        throw new TaskListIndexOutOfBoundsException(
                                "   There are only " + Duke.dl.listSize() + " items in the list!");
                    }

                    Duke.dl = Duke.dl.deleteTask(index);
                    sb.append(Ui.printDelete(Duke.dl, index));
                } catch (TaskListIndexOutOfBoundsException e) {
                    sb.append(Ui.printErrorMessage(e));
                }

                break;

            default:
                try {
                    throw new InvalidCommandArgumentException("   Hey, I can't do that for you. " +
                            "\n   I don't know " + input + "...");
                } catch (InvalidCommandArgumentException e) {
                    sb.append(Ui.printErrorMessage(e));
                }

                break;
        }

        System.out.println(sb.toString());
        return sb.toString();
    }
}
