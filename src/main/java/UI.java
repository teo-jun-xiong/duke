import java.util.Scanner;

class UI {
    String readCommand(Scanner sc) {
        return sc.next();
    }

    void printList(Scanner sc, DukeList dl) {
        sc.nextLine();
        System.out.println(DukeStringFormat.DIVIDER);
        dl.printList();
        System.out.println(DukeStringFormat.DIVIDER);
    }

    void printBye(Scanner sc) {
        sc.nextLine();
        System.out.println(DukeStringFormat.DIVIDER + DukeStringFormat.BYE
                + DukeStringFormat.DIVIDER);
    }

    String readTaskIndex(Scanner sc) {
        return sc.nextLine().trim();
    }

    void printDone(DukeList dl, int index) {
        System.out.println(DukeStringFormat.DIVIDER);
        System.out.println(DukeStringFormat.DONE);
        System.out.println("      " + dl.printTask(index));
        System.out.println(DukeStringFormat.DIVIDER);
    }

    void printErrorMessage(Exception e) {
        System.out.println(DukeStringFormat.DIVIDER
                + e.getMessage() + "\n"
                + DukeStringFormat.DIVIDER);
    }

    String readTaskDescription(Scanner sc) {
        return sc.nextLine();
    }

    void printTaskAdded(DukeList dl, Task task) {
        System.out.println(DukeStringFormat.DIVIDER
                + DukeStringFormat.ADDED
                + "      " + task.toString()
                + "\n\n   Now you have " + dl.listSize()
                + (dl.listSize() == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeStringFormat.DIVIDER);
    }

    void printDateTimeErrorMessage() {
        System.out.println(DukeStringFormat.DIVIDER
                + "   (•̀ ᴗ •́ )و̑ The date and/or time format is invalid.\n   Please format your input as: DD/MM/YYYY HHmm.\n"
                + DukeStringFormat.DIVIDER);
    }

    void printDelete(DukeList dl, int index) {
        System.out.println(DukeStringFormat.DIVIDER
                + DukeStringFormat.DELETED + "      " + dl.printTask(index)
                + "\n   Now you have " + (dl.listSize() - 1)
                + (dl.listSize() - 1 == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeStringFormat.DIVIDER);
    }

    void printWriteErrorMessage() {
        System.err.println(DukeStringFormat.DIVIDER
                + "   (・_・;) Something went wrong!\n"
                + DukeStringFormat.DIVIDER);
    }
}
