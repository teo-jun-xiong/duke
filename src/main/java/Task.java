abstract class Task {
    String description;
    private boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    abstract Task completeTask();

    String getStatusIcon() {
        return (isDone ? "\u2714" : "\u274C"); //return tick or X symbols
    }

    boolean getCompletionStatus() {
        return this.isDone;
    }

    boolean match(String keywords) {
        return this.description.toLowerCase().contains(keywords);
    }
}
