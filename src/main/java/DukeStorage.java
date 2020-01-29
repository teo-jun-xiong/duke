import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

class DukeStorage {
    static final File filePath = new File("data/myTasks.txt");

    static void writeTasks(DukeList dl, File filePath) throws IOException {
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

    static DukeList retrieveTasks(File filePath) throws FileNotFoundException, ParseException {
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
}
