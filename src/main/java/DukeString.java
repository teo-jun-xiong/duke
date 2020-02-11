/**
 * A class to abstract the frequently used Strings in Duke.
 */
class DukeString {
    private static final String LOGO =
            "                 .-\"\"\"\"-.        .-\"\"\"\"-.\n"
            + "                /        \\      /        \\\n"
            + "               /_        _\\    /_        _\\\n"
            + "              // \\      / \\\\  // \\      / \\\\\n"
            + "              |\\__\\    /__/|  |\\__\\    /__/|\n"
            + "               \\    ||    /    \\    ||    /\n"
            + "                \\        /      \\        /\n"
            + "                 \\ \\__/ /        \\ \\__/ /\n"
            + "                  '.__.'          '.__.'\n"
            + "                   |  |            |  |\n"
            + "                   |  |    oNUS    |  |\n\n";

    static final String HELLO = DukeString.DIVIDER + DukeString.LOGO
            + "   Hello!"
            + "\n   The oNUS is on me to help you be productive!\n"
            + DukeString.DIVIDER;

    static final String ADDED = "   Got it! I've added this task:\n\n";
    static final String DIVIDER = "____________________________________________________________\n\n";
    static final String DONE = "   Nice! I've marked this task as done:\n";
    static final String DELETED = "   Alright, I've removed this task:\n";
    static final String BYE = "   Your current task list has been saved!\n   Bye, hope to see you again soon!\n";
    static final String SAMPLE = "   For example:\n      deadline Assignment /by 01/01/2000 1234\n";

    static final String INVALID_DATE_TIME_FORMAT = "   The date and/or time format is invalid.\n";
    static final String DATE_TIME_FORMAT_EXAMPLE = "   Please format your input as: DD/MM/YYYY HHmm.\n";

    static final String UI_BYE = DukeString.DIVIDER + DukeString.BYE + DukeString.DIVIDER;
    static final String WRITE_ERROR_MESSAGE = DukeString.DIVIDER
            + "   Something went wrong with saving the file!\n" + DukeString.DIVIDER;

    static final String DATE_TIME_ERROR_MESSAGE = DukeString.DIVIDER + DukeString.INVALID_DATE_TIME_FORMAT
            + DukeString.DATE_TIME_FORMAT_EXAMPLE + DukeString.SAMPLE + DukeString.DIVIDER;

    static final String UI_CLEAR = DukeString.DIVIDER + "   Your tasks have been cleared!\n"
            + "   The list is now empty.\n\n" + DukeString.DIVIDER;
}