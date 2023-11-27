import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Controller {
    public void start(){
        DatabaseUtil.createTable();
        boolean t = true;
        while (t){
            printMenu();
            int switchCommand = getAction();

            switch (switchCommand){
                case 1:
                    AddTask();

                }
            }
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

        TaskRepository taskRepository = new TaskRepository();
        taskRepository.saveTask(taskDTO);


    }

    public static void printMenu(){
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
