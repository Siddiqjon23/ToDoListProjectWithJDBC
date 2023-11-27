import java.sql.*;
import java.time.LocalDateTime;

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
}
