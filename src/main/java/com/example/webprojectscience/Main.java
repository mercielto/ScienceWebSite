package com.example.webprojectscience;

import com.example.webprojectscience.utill.ConnectionSingleton;
import com.example.webprojectscience.utill.FileBuilder;

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
        FileBuilder builder = new FileBuilder();
        System.out.println(builder.getPostText("personality11700248593076.txt"));
    }
}
