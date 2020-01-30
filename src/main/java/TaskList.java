
import java.util.ArrayList;
import java.util.List;

class TaskList {
    private List<Task> list;

     TaskList() {
        this.list = new ArrayList<>();
     }

     private TaskList(TaskList dl) {
         this.list = dl.list;
     }

    TaskList addToList(Task task) {
         TaskList dl = new TaskList(this);
         dl.list.add(task);
         return dl;
    }

    boolean isTaskDone(int index) {
        return this.list.get(index).getCompletionStatus();
    }
    void printList() {
         if (this.list.size() == 0) {
             System.out.println("   The list is empty.");
         }
         for (int i = 1; i <= list.size(); i++) {
             System.out.println("   " + i + ". " + list.get(i - 1));
         }
    }

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

    TaskList deleteTask(int index) {
        TaskList dl = new TaskList(this);
        dl.list.remove(index);
        return dl;
    }

    TaskList find(String keywords) {
         TaskList found = new TaskList();

         for (Task task : this.list) {
             if (task.match(keywords)) {
                 found = found.addToList(task);
             }
         }

         return found;
    }
}
