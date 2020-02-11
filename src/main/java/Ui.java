/**
 * A class to handle interactions with the user.
 * It prints responses to a user's command or input.
 */
class Ui {
    static String readCommand(String input) {
        assert input != null;
        return input.split(" ")[0].toLowerCase();
    }

    static String printList(TaskList dl) {
        assert dl != null;
        return DukeString.DIVIDER
            + dl.printList()
            + "\n" + DukeString.DIVIDER;
    }

    static String printBye() {
        return DukeString.UI_BYE;
    }

    static String readTaskIndex(String input) {
        assert input != null;
        return input.trim();
    }

    static String printDone(TaskList dl, int index) {
        assert dl != null;

        return DukeString.DIVIDER
                + DukeString.DONE
                + "      " + dl.printTask(index)
                + DukeString.DIVIDER;
    }

    static String printErrorMessage(Exception e) {
        return DukeString.DIVIDER
                + e.getMessage() + "\n"
                + DukeString.DIVIDER;
    }

    static String readTaskDescription(String input) {
        assert input != null;
        return input.trim();
    }

    static String readKeyword(String input) {
        assert input != null;
        return input.trim().toLowerCase();
    }

    static String printTaskAdded(TaskList dl, Task task) {
        assert dl != null;
        assert task != null;

        return DukeString.DIVIDER
                + DukeString.ADDED
                + "      " + task.toString()
                + "\n\n   Now you have " + dl.listSize()
                + (dl.listSize() == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeString.DIVIDER;
    }

    static String printDateTimeErrorMessage() {
        return DukeString.DATE_TIME_ERROR_MESSAGE;
    }

    static String printDelete(TaskList dl, int index) {
        assert dl != null;

        return DukeString.DIVIDER
                + DukeString.DELETED + "      " + dl.printTask(index)
                + "\n   Now you have " + dl.listSize()
                + (dl.listSize() - 1 == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeString.DIVIDER;
    }

    static String printWriteErrorMessage() {
        return DukeString.WRITE_ERROR_MESSAGE;
    }

    static String printFind(String keywords, TaskList found) {
        assert keywords != null;
        assert found != null;

        return DukeString.DIVIDER
                + "   Here are the tasks with " + keywords + " in them:\n\n"
                + found.printList()
                + "\n" + DukeString.DIVIDER;
    }

    static String printClear() {
        return DukeString.UI_CLEAR;
    }
}