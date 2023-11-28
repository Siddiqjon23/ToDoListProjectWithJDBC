import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
  public void saveTask(TaskDTO dto){
      try {
          Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ToDoListProject", "user_db", "123456");
          String sql = "insert into task(title,content,status,createdAt,finishedAt)values(?,?,?,?,?)";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setString(1,dto.getTitle());
          preparedStatement.setString(2,dto.getContent());
          preparedStatement.setString(3,dto.getStatus().toString());
          preparedStatement.setTimestamp(4,Timestamp.valueOf(dto.getCreatedAt()));
          preparedStatement.setTimestamp(5,null);

          preparedStatement.executeUpdate();
          preparedStatement.close();
          connection.close();

      } catch (SQLException e) {
          throw new RuntimeException(e);
      }

  }
  public List<TaskDTO> getAllActiveTasks(){
      try {
          List<TaskDTO> taskDTOS = new ArrayList<>();
          Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ToDoListProject", "user_db", "123456");
          String sql = "select * from task where status = 'Active' ";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);

          ResultSet values = preparedStatement.executeQuery();

//          while (values.next()){
//              TaskDTO taskDTO = new TaskDTO();
//              taskDTO.setTitle(values.getString("title"));
//              taskDTO.setContent(values.getString("content"));
//              taskDTO.setStatus(TaskStatus.valueOf(values.getString("status")));
//              taskDTO.setCreatedAt(values.getTimestamp("createdAt").toLocalDateTime());
//
//
//              Timestamp finishedAtTimestamp = values.getTimestamp("finishedAt");
//              taskDTO.setFinishedAt(finishedAtTimestamp != null ? finishedAtTimestamp.toLocalDateTime()
//
//              taskDTOS.add(taskDTO)
//          }
          while (values.next()) {
              TaskDTO taskDTO = new TaskDTO();
              taskDTO.setTitle(values.getString("title"));
              taskDTO.setContent(values.getString("content"));
              taskDTO.setStatus(TaskStatus.valueOf(values.getString("status")));

              taskDTO.setCreatedAt(values.getTimestamp("createdAt").toLocalDateTime());

              Timestamp finishedAtTimestamp = values.getTimestamp("finishedAt");
              taskDTO.setFinishedAt(finishedAtTimestamp != null ? finishedAtTimestamp.toLocalDateTime() : null);
              taskDTOS.add(taskDTO);
          }
          preparedStatement.close();
          connection.close();
          return taskDTOS;
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }

    public List<TaskDTO> getAllFinishedTasks() {
      
    }

}
