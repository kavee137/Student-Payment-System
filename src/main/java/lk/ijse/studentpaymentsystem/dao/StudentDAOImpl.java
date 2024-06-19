package lk.ijse.studentpaymentsystem.dao;

import lk.ijse.studentpaymentsystem.model.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl {


    public static boolean delete(String nic) throws SQLException {
        String sql = "DELETE FROM student WHERE NIC = ?";

        Connection connection = lk.ijse.tailorshopmanagementsystem.db.DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, nic);

        return pstm.executeUpdate() > 0;
    }










    public static StudentDTO nicSearch(String nic) throws SQLException {
        String sql = "SELECT name, arrived FROM student WHERE NIC = ?";

        Connection connection = lk.ijse.tailorshopmanagementsystem.db.DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, nic);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            String name = resultSet.getString(1);
            String arrived = resultSet.getString(2);

            StudentDTO studentDTO = new StudentDTO(name, arrived);

            return studentDTO;
        }
        return null;
    }


    public static boolean update(StudentDTO studentDTO) throws SQLException {
        String sql = "UPDATE student SET name = ?, arrived = ? WHERE NIC = ?";

        Connection connection = lk.ijse.tailorshopmanagementsystem.db.DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, studentDTO.getName());
        pstm.setObject(2, studentDTO.getArrived());
        pstm.setObject(3, studentDTO.getNic());

        return pstm.executeUpdate() > 0;
    }


    public static boolean save(StudentDTO studentDTO) throws SQLException {
        String sql = "INSERT INTO student VALUES(?, ?, ?)";

        Connection connection = lk.ijse.tailorshopmanagementsystem.db.DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, studentDTO.getNic());
        pstm.setObject(2, studentDTO.getName());
        pstm.setObject(3, studentDTO.getArrived());

        return pstm.executeUpdate() > 0;
    }





    public static List<StudentDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM student";

        PreparedStatement pstm = lk.ijse.tailorshopmanagementsystem.db.DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<StudentDTO> stdList = new ArrayList<>();

        while (resultSet.next()) {
            String nic = resultSet.getString(1);
            String name = resultSet.getString(2);
            String arrived = resultSet.getString(3);

            StudentDTO studentDTO = new StudentDTO(nic, name, arrived);
            stdList.add(studentDTO);
        }
        return stdList;
    }
}
