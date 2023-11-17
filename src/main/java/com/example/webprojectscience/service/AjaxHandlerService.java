package com.example.webprojectscience.service;

import com.example.webprojectscience.models.*;
import com.example.webprojectscience.models.joined.JoinedComment;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;
import org.json.HTTP;
import org.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class AjaxHandlerService {
    public static List<Object> getBuilderAndValues(HttpServletRequest request, Map<String, String> map) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder();
        List<Object> values = new ArrayList<>();

        String from = request.getParameter("dateFrom");
        if (from.length() != 0) {
            builder.moreEquals(map.get("date"));
            values.add(Date.valueOf(from));
        }

        String to = request.getParameter("dateTo");
        if (to.length() != 0) {
            builder.lessEquals(map.get("date"));
            values.add(Date.valueOf(to));
        }

        JSONArray array = new JSONArray(request.getParameter("checkBox"));
        if (!array.isEmpty()) {
            builder.contains(map.get("theme_name"), array.length());    //theme.name"
            for (int i = 0; i < array.length(); i++) {
                values.add(array.getString(i));
            }
        }

        JSONArray tags = new JSONArray(request.getParameter("tags"));
        if (!tags.isEmpty()) {
            List<Object> tt = new ArrayList<>();
            for (int i = 0; i < tags.length(); i++) {
                tt.add(tags.getString(i));
            }
            builder.arrayContains(map.get("tags"), tt);
        }

        String page = request.getParameter("page");
        int pageSize = (int) request.getServletContext().getAttribute("limit");
        if (page.length() != 0){
            builder.setOffSet((Integer.parseInt(page) - 1) * pageSize);
            builder.setLimit(pageSize);
        }

        builder.orderBy(map.get("date") + " DESC");

        return List.of(builder, values);
    }
    public static List<JoinedPost> getPosts(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("date", "date");
        map.put("theme_name", "theme.name");
        map.put("tags", "tags");
        List<Object> obj = getBuilderAndValues(request, map);
        PreparedStatementConditionBuilder builder = (PreparedStatementConditionBuilder) obj.get(0);
        List<Object> values = (List<Object>) obj.get(1);

        return DataBaseManager.getPostDao().getJoined(builder, values);
    }

    public static List<JoinedQuestion> getQuestions(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        map.put("theme_name", "theme.name");
        map.put("date", "fq.date");
        map.put("tags", "tags");

        List<Object> obj = getBuilderAndValues(req, map);
        PreparedStatementConditionBuilder builder = (PreparedStatementConditionBuilder) obj.get(0);
        List<Object> values = (List<Object>) obj.get(1);

        return DataBaseManager.getQuestionDao().getJoinedQuestions(builder, values);
    }

    public static List<JoinedPost> getPersonalJoinedPosts(HttpServletRequest request) {
        User user = AuthorizationService.getAuthorizedUser(request);

        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder();
        builder.setOffSet(Integer.parseInt(request.getParameter("page")));
        builder.setLimit((int) request.getServletContext().getAttribute("limit"));
        builder.equals("user_id");

        return DataBaseManager.getPostDao().getJoined(builder, List.of(user.getId()));
    }

    public static void deletePost(HttpServletRequest req, HttpServletResponse res) {
        User user = AuthorizationService.getAuthorizedUser(req);
        String[] fullLink = req.getParameter("link").split("/");
        String link = fullLink[fullLink.length - 1];

        Post post = DataBaseManager.getPostDao().getByLink(link);
        if (!Objects.equals(post.getUserId(), user.getId())) {
            res.setStatus(404);
            return;
        }

        DataBaseManager.getPostDao().delete(post.getId());
    }

    public static void deleteQuestion(HttpServletRequest req, HttpServletResponse resp) {
        User user = AuthorizationService.getAuthorizedUser(req);
        String[] fullLink = req.getParameter("link").split("/");
        String link = fullLink[fullLink.length - 1];

        Question question = DataBaseManager.getQuestionDao().getByLink(link);
        if (!Objects.equals(question.getUserId(), user.getId())) {
            resp.setStatus(404);
            return;
        }

        DataBaseManager.getQuestionDao().delete(question.getId());
    }

    public static void saveComment(HttpServletRequest req) {
        User user = AuthorizationService.getAuthorizedUser(req);
        String[] fullLink = req.getParameter("link").split("/");
        String link = fullLink[fullLink.length - 1];

        Post post = DataBaseManager.getPostDao().getByLink(link);
        Comment comment = new Comment();
        comment.setDate(Date.valueOf(LocalDate.now()));
        comment.setUserId(user.getId());
        comment.setText(req.getParameter("text"));
        comment.setPostId(post.getId());

        DataBaseManager.getCommentDao().insert(comment);
    }

    public static JoinedComment getJoinedComment(Comment comment) {
        return DataBaseManager.getCommentDao().getJoinedById(comment.getId());
    }

    public static void saveForumAnswer(HttpServletRequest req) {
        User user = AuthorizationService.getAuthorizedUser(req);
        String[] fullLink = req.getParameter("link").split("/");
        String link = fullLink[fullLink.length - 1];

        Question question = DataBaseManager.getQuestionDao().getByLink(link);
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setDate(Date.valueOf(LocalDate.now()));
        questionAnswer.setText(req.getParameter("text"));
        questionAnswer.setQuestionId(question.getId());
        questionAnswer.setUserId(user.getId());

        DataBaseManager.getQuestionAnswerDao().insert(questionAnswer);
    }
}
