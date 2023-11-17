package com.example.webprojectscience.utill;

import java.util.ArrayList;
import java.util.List;

public class PreparedStatementConditionBuilder {
    private List<String> conditions = new ArrayList<>();
    private String orderBy = null;
    private String limit = null;
    private String sql;
    private String offSet = null;

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
        String command;
        if (!conditions.isEmpty()) {
            if (sql.toLowerCase().contains("where")) {
                command = sql + " AND " + String.join(" AND ", conditions);
            } else {
                command = sql + " WHERE " + String.join(" AND ", conditions);
            }
        } else {
            command = sql;
        }

        if (orderBy != null) {
            command += orderBy;
        }

        if (limit != null) {
            command += limit;
        }

        if (offSet != null) {
            command += offSet;
        }

        return command;
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

    public void contains(String fieldName, int count) {
        if (count != 0) {
            StringBuilder command = new StringBuilder("%s IN (?".formatted(fieldName));
            for (int i = 0; i < count - 1; i++) {
                command.append(", ?");
            }
            command.append(")");
            conditions.add(command.toString());
        }
    }

    public void arrayContains(String fieldName, List<Object> values) {
        StringBuilder command = new StringBuilder("%s @> '{%s".formatted(fieldName, values.get(0)));
        for (int i = 1; i < values.size(); i++) {
            command.append(", %s".formatted(values.get(i)));
        }
        command.append("}'");
        conditions.add(command.toString());
    }

    public void isNull(String fieldName) {
        conditions.add("%s IS NULL".formatted(fieldName));
    }

    public void isNotNull(String fieldName) {
        conditions.add("%s IS NOT NULL".formatted(fieldName));
    }

    public void orderBy(String fieldName) {
        orderBy = " ORDER BY %s".formatted(fieldName);
    }

    public void setLimit(int count) {
        limit = " LIMIT %s".formatted(count);
    }

    public void setOffSet(int count) {
        offSet = " OFFSET %s".formatted(count);
    }
}
