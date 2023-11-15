package com.example.webprojectscience;

import com.example.webprojectscience.utill.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    protected static final String SQL_GET_JOINED = "SELECT * FROM forum_question as fq, \"User\" as us, theme," +
            " forum_answer as fa WHERE fa.question_id = fq.id AND fq.user_id = us.id" +
            " AND theme.id = fq.theme_id";
    public static void main(String[] args) throws SQLException {
//        Connection connection = ConnectionSingleton.getConnection();
//
//        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_JOINED);
//        ResultSet rs = preparedStatement.executeQuery();
//        System.out.println(rs);
//
//        while (rs.next()) {
//            System.out.println(rs);
//        }
        List<String> t = List.of("a");
        System.out.println(String.join(" AND ", t));
    }
}
