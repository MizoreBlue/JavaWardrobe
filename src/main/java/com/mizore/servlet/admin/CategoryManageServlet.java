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

import java.io.IOException;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        uri = uri.replace("/backend/category/", "");

        //        对分类的修改
        if (uri.equals("modify")) {
//            设置修改修改参数
            Category category = new Category();
            category.setId(Long.parseLong(request.getParameter("id")));
            category.setStatus(Integer.parseInt(request.getParameter("status")));
            category.setSort(Integer.parseInt(request.getParameter("sort")));
            category.setName(request.getParameter("name"));

//            修改分类
            Result<String> result;
            boolean flag = categoryService.updateCategory(category);
            if (flag) {
                result = Result.success("分类修改成功");
            } else {
                result = Result.error("修改失败，未知错误");
            }

//            返回JSON
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);

        }
    }

}
