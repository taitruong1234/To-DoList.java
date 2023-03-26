// project members: Tai Truong, Max Nguyen, Quang Nguyen
// project name: To-do list

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class ToDoList {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to your To-Do List!");
        System.out.println("=============================");
        int choice;

        do {
            System.out.println();
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Sort Tasks");
            System.out.println("6. Search Tasks");
            System.out.println("0. Exit");
            System.out.print("Choose an option (0-8): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1 -> addTask();
                case 2 -> listTasks();
                case 3 -> markTaskCompleted();
                case 4 -> deleteTask();
                case 5 -> sortTasks();
                case 6 -> searchTasks();
                case 0 -> System.out.println("Thank you for using your To-Do List!");
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    /* The addTask() method prompts the user to enter the description, due date, and priority of a new task,
    creates a Task object with these values, and adds it to the ArrayList.*/
    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Task task = new Task(description, dueDate, priority);
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    /*The listTasks() method prints a numbered list of all tasks in the ArrayList,
    along with their completion status, description, and priority.*/
    private static void listTasks() {
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. [%s] %s (Priority %d)\n", i + 1, task.isCompleted() ? "X" : " ", task.getDescription(), task.getPriority());
        }
    }

    /*The markTaskCompleted() method prompts the user to enter the number of a task to mark as completed,
    sets the completed flag of the corresponding Task object to true, and prints a confirmation message.*/
    private static void markTaskCompleted() {
        System.out.print("Enter task number to mark as completed: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number, please try again.");
        } else {
            Task task = tasks.get(index - 1);
            task.setCompleted(true);
            System.out.printf("Task '%s' marked as completed.\n", task.getDescription());
        }
    }

    /*The deleteTask() method prompts the user to enter the number of a task to delete,
    removes the corresponding Task object from the ArrayList, and prints a confirmation message.*/
    private static void deleteTask() {
        System.out.print("Enter task number to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number, please try again.");
        } else {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.printf("Task '%s' deleted.\n", task.getDescription());
        }
    }

    /*The sortTasks() method presents the user with a menu of options for sorting tasks by due date or priority,
    sorts the ArrayList of tasks accordingly using a Comparator, and prints a confirmation message.*/
    private static void sortTasks() {
        System.out.println("Sort by:");
        System.out.println("1. Due date");
        System.out.println("2. Priority");
        System.out.print("Choose an option (1-2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (choice == 1) {
            tasks.sort(new Comparator<Task>() {

                public int compare(Task t1, Task t2) {
                    return t1.getDueDate().compareTo(t2.getDueDate());
                }
            });
            System.out.println("Tasks sorted by due date.");
        } else if (choice == 2) {
            tasks.sort(new Comparator<Task>() {

                public int compare(Task t1, Task t2) {
                    return Integer.compare(t2.getPriority(), t1.getPriority());
                }
            });
            System.out.println("Tasks sorted by priority.");
        } else {
            System.out.println("Invalid choice, please try again.");
        }
    }

    /*The searchTasks() method prompts the user to enter a search term, searches the ArrayList of tasks
    for tasks whose description contains the search term, and prints a numbered list of all matching tasks.*/
    private static void searchTasks() {
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine();
        int count = 0;

        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                System.out.printf("[%s] %s (Priority %d)\n", task.isCompleted() ? "X" : " ", task.getDescription(), task.getPriority());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.printf("%d matching tasks found.\n", count);
        }
    }

    private static class Task {
        private String description;
        private String dueDate;
        private int priority;
        private boolean completed;
        public Task(String description, String dueDate, int priority) {
            this.description = description;
            this.dueDate = dueDate;
            this.priority = priority;
            this.completed = false;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getDueDate() {
            return dueDate;
        }
        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }
        public int getPriority() {
            return priority;
        }
        public void setPriority(int priority) {
            this.priority = priority;
        }
        public boolean isCompleted() {
            return completed;
        }
        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}

