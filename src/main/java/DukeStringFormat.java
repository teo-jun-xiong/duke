class DukeStringFormat {
    private static final String LOGO =
            "                  M\"\"\"\"\"\"\"`YM\"\"MMMMM\"\"MP\"\"\"\"\"\"`MM\n" +
            "                  M  mmmm.  M  MMMMM  M  mmmmm..M\n" +
            "          .d8888b.M  MMMMM  M  MMMMM  M.      `YM\n" +
            "          88'  `88M  MMMMM  M  MMMMM  MMMMMMM.  M\n" +
            "          88.  .88M  MMMMM  M  `MMM'  M. .MMM'  M\n" +
            "          `88888P'M  MMMMM  Mb       dMb.     .dM\n" +
            "                  MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n";

    static final String DIVIDER = "____________________________________________________________\n";
    static final String HELLO = DukeStringFormat.DIVIDER + DukeStringFormat.LOGO
            + "   Hello!" +
                        "\n   The oNUS is on me to help you be productive!\n"
            + DukeStringFormat.DIVIDER;

    static final String ADDED = "   Got it! I've added this task:\n";
    static final String DONE = "   Nice! I've marked this task as done:\n";
    static final String DELETED = "   Alright, I've removed this task:\n";
    static final String BYE = "   Bye. Hope to see you again soon!\n";
    static final String SAMPLE = "   For example:" +
            "\n      deadline Assignment /by 01/01/2000 0000";
}
