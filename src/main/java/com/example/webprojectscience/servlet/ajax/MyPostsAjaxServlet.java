package com.example.webprojectscience.servlet.ajax;

import com.example.webprojectscience.service.AjaxHandlerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyPostsAjaxServlet", value = "/ajax/myPosts")
public class MyPostsAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AjaxHandlerService.deletePost(req, resp);
    }
}
