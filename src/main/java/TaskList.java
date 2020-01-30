import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Tasks.
 */
class TaskList {
    private List<Task> list;

    /**
     * Creates an empty TaskList with no tasks.
     */
    TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a copy of the TaskList with the same list of Tasks.
     *
     * @param dl A TaskList.
     */
    private TaskList(TaskList dl) {
        this.list = dl.list;
    }

    /**
     * Adds a Task to a TaskList.
     *
     * @param task The Task to be added.
     * @return A new TaskList with the added Task.
     */
    TaskList addToList(Task task) {
        TaskList dl = new TaskList(this);
        dl.list.add(task);
        return dl;
    }

    /**
     * Checks whether a Task is complete.
     *
     * @param index The index of the Task to be checked.
     * @return A boolean of whether the Task has been completed.
     */
    boolean isTaskDone(int index) {
        return this.list.get(index).getCompletionStatus();
    }

    /**
     * Prints all the Tasks in the TaskList.
     */
    void printList() {
        if (this.list.size() == 0) {
            System.out.println("   The list is empty.");
        }
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("   " + i + ". " + list.get(i - 1));
        }
    }

    /**
     * Sets a Task to be completed.
     *
     * @param index The index of the Task to be completed.
     * @return A new TaskList with the updated Task.
     */
    TaskList setDone(int index) {
        TaskList dl = new TaskList(this);
        dl.list.set(index, this.list.get(index).completeTask());
        return dl;
    }

    String printTask(int index) {
        return this.list.get(index).toString();
    }

    int listSize() {
        return this.list.size();
    }

    /**
     * Deletes a Task.
     *
     * @param index The index of the Task to be deleted.
     * @return A new TaskList without the deleted Task.
     */
    TaskList deleteTask(int index) {
        TaskList dl = new TaskList(this);
        dl.list.remove(index);
        return dl;
    }
}