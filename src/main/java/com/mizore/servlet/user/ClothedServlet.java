package com.mizore.servlet.user;

import com.mizore.entity.Clothes;
import com.mizore.service.ClothesService;
import com.mizore.service.impl.ClothesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/clothes/*")
public class ClothedServlet extends HttpServlet {

    private ClothesService clothesService = new ClothesServiceImpl();



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 查看商品列表
        String uri = req.getRequestURI();
        if (uri.contains("/list")) {
            listClothes(req, resp);
        }
        // 这里可以扩展 details, search 等操作

//        获取单个商品详细信息
        if (uri.contains("/clothesDetail")) {
//            获取路径参数
            String string = req.getParameter("id");
            int id = Integer.parseInt(string);

//           插叙单个商品
            Clothes clothes = clothesService.getClothesDetails(id);

//            返回值
            req.setAttribute("clothes", clothes);

            req.getRequestDispatcher("/WEB-INF/views/client/product_detail.jsp").forward(req, resp);
        }
    }


    /**
     * 查询所有图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void listClothes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Book> books = bookService.findAllBooks();
//        设置请求体
//        req.setAttribute("productList", books);
        // 转发到首页显示 携带数据
        req.getRequestDispatcher("/WEB-INF/views/client/product_list.jsp").forward(req, resp);
    }
}