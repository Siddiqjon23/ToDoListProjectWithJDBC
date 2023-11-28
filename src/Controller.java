import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Controller {
    TaskRepository taskRepository = new TaskRepository();
    public void start(){
        DatabaseUtil.createTable();
        boolean t = true;
        while (t){
            printMenu();
            int switchCommand = getAction();

            switch (switchCommand){
                case 1:
                    AddTask();
                    break;
                case 2 :
                    getAllTasks();
                    break;
                case 3:
                    finishedTasks();
                    break;
                case 4:
                    break;
                case 5:
                    markAsDone();
                    break;

                }
            }
        }

    private void markAsDone() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id :");
        int id = scanner.nextInt();

        var markAsdone = taskRepository.markAsdone(id);
    }

    private void finishedTasks() {
        var tasks = taskRepository.getAllFinishedTasks();

        System.out.println("+-----------------+-----------------------------------+-----------------------------------------------------+");
        System.out.printf("| %-15s | %-30s | %-10s | %-26s| %-10s|\n", "Title", "Content", "Status","CreatedAt","FinishedAt");
        System.out.println("+-----------------+-----------------------------------+-----------------------------------------------------+");


        for (TaskDTO taskDTO : tasks) {
            System.out.printf("| %-15s | %-30s | %-10s | %-10s| %-10s|\n", taskDTO.getTitle(), taskDTO.getContent(), taskDTO.getStatus(),taskDTO.getCreatedAt(),taskDTO.getFinishedAt());
        }

        System.out.println("+-----------------+-----------------------------------+-----------------------------------------------------+");
    }


    private void AddTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the title : ");
        String title = scanner.nextLine();

        System.out.println("enter the content :");
        String content = scanner.nextLine();

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setTitle(title);
        taskDTO.setContent(content);
        taskDTO.setStatus(TaskStatus.Active);
        taskDTO.setCreatedAt(LocalDateTime.now());
        taskDTO.setFinishedAt(null);

        taskRepository.saveTask(taskDTO);

    }
//    public void getAllTasks(){
//        var tasks = taskRepository.getAllActiveTasks();
//        for (TaskDTO taskDTO : tasks){
//            System.out.println(taskDTO);
//        }
//    }
public void getAllTasks() {
    var tasks = taskRepository.getAllActiveTasks();

    System.out.println("+-----------------+-----------------------------------+-----------------------------------------------------+");
    System.out.printf("| %-15s | %-30s | %-10s | %-26s| %-10s|\n", "Title", "Content", "Status","CreatedAt","FinishedAt");
    System.out.println("+-----------------+-----------------------------------+-----------------------------------------------------+");

    for (TaskDTO taskDTO : tasks) {
        System.out.printf("| %-15s | %-30s | %-10s | %-10s| %-10s|\n", taskDTO.getTitle(), taskDTO.getContent(), taskDTO.getStatus(),taskDTO.getCreatedAt(),taskDTO.getFinishedAt());
    }

    System.out.println("+-----------------+-----------------------------------+------------------------------------------------------+");
}


    public  void printMenu(){
        System.out.println("***Assalomu alaykum****");
        System.out.println("1=>Add task");
        System.out.println("2=>Active task lists");
        System.out.println("3=>Finished task lists");
        System.out.println("4=>Update");
        System.out.println("5=>Marks as done");
        System.out.println();

    }
    public Integer getAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("choose one of them :");
        return scanner.nextInt();

    }

}
