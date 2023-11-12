package com.example.webprojectscience.utill;

import java.util.ArrayList;
import java.util.List;

public class PreparedStatementConditionBuilder {
    private List<String> conditions = new ArrayList<>();
    private String sql;

    public PreparedStatementConditionBuilder(String sql) {
        this.sql = sql;
    }

    public PreparedStatementConditionBuilder() {
        sql = "";
    }

    public void setSql(String sql1) {
        sql = sql1;
    }

    public String get() {
        if (sql.toLowerCase().contains("where")) {
            return sql + " AND " + String.join(" AND ", conditions);
        }
        return sql + " WHERE " + String.join(" AND ", conditions);
    }

    public void equals(String fieldName) {
        conditions.add("%s = ?".formatted(fieldName));
    }

    public void more(String fieldName) {
        conditions.add("%s > ?".formatted(fieldName));
    }

    public void moreEquals(String fieldName) {
        conditions.add("%s >= ?".formatted(fieldName));
    }

    public void less(String fileName) {
        conditions.add("%s < ?".formatted(fileName));
    }

    public void lessEquals(String fileName) {
        conditions.add("%s <= ?".formatted(fileName));
    }

    public void contains(String fieldName) {
        conditions.add("%s IN ?".formatted(fieldName));
    }

    public void isNull(String fieldName) {
        conditions.add("%s IS NULL".formatted(fieldName));
    }

    public void isNotNull(String fieldName) {
        conditions.add("%s IS NOT NULL".formatted(fieldName));
    }
}
