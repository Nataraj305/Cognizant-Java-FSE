public class Main {
    public static void main(String[] args) {
        TaskLinkedList taskList =
                new TaskLinkedList();
        taskList.addTask(
                new Task(
                        101,
                        "Design UI",
                        "Pending"));
        taskList.addTask(
                new Task(
                        102,
                        "Develop Backend",
                        "In Progress"));
        taskList.addTask(
                new Task(
                        103,
                        "Testing",
                        "Pending"));
        System.out.println(
                "----- Task List -----");
        taskList.displayTasks();
        System.out.println(
                "\n----- Search Task -----");
        Task task =
                taskList.searchTask(102);
        if(task != null) {
            System.out.println(task);
        }
        System.out.println(
                "\n----- Delete Task -----");

        taskList.deleteTask(102);

        System.out.println(
                "\n----- Updated Task List -----");

        taskList.displayTasks();
    }
}