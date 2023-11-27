import java.sql.*;

public class DatabaseUtil {
    public static void createTable() {
//        String sql = "create table if not exists task (" +
//                "id serial primary key," +
//                "title varchar(250) not null," +
//                "content text," +
//                "status varchar(250) not null," +
//                "CreatedAt TIMESTAMP not null," +
//                "FinishedAt TIMESTAMP "+
//                ")";
        String sql = "CREATE TABLE IF NOT EXISTS task (" +
                "id SERIAL PRIMARY KEY, " +
                "title VARCHAR(250) NOT NULL, " +
                "content TEXT, " +
                "status VARCHAR(250) NOT NULL, " +
                "created_at TIMESTAMP NOT NULL, " +
                "finished_at TIMESTAMP " +
                ")";

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ToDoListProject", "user_db", "123456");
            Statement statement = connection.createStatement();
            statement.executeLargeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
