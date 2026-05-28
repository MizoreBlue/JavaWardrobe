package com.mizore.servlet.admin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizore.entity.Category;
import com.mizore.service.CategoryService;
import com.mizore.service.impl.CategoryServiceImpl;
import com.mizore.utils.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/backend/category/*")
public class CategoryManageServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    private ObjectMapper objectMapper = new ObjectMapper();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        获取请求路径
        String uri = request.getRequestURI();

//        截取请求路径
        uri = uri.replace("/backend/category/", "");

        if (uri.equals("list")) {
//            获取分类列表
         List<Category> categoryList = categoryService.getCategoryList();
         request.setAttribute("categoryList",categoryList);
         request.getRequestDispatcher("/WEB-INF/views/admin/category_manage.jsp").forward(request,response);
        }



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8"); //设置响应类型为JSON

        String uri = request.getRequestURI();
        uri = uri.replace("/backend/category/", "");

        //        对分类的修改
        if (uri.equals("modify")) {

            // 1. 从请求体中读出字符穿
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            String jsonString = builder.toString();

            // 2. 将 JSON 字符串反序列化为 Category对象
            Category category = objectMapper.readValue(jsonString, Category.class);

//            修改分类
            Result<String> result;

            // 3. 调用业务层执行修改
            boolean flag = categoryService.updateCategory(category);
            if (flag) {
                result = Result.success("分类修改成功");
            } else {
                result = Result.error("修改失败，未知错误");
            }

            // 4. 将结果对象转换为JSON 返回给前端
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        }
    }

}
