<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        //  加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取连接
        String url = "jdbc:mysql://localhost:3306/java_wardrobe?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "025911";

        conn = DriverManager.getConnection(url, user, password);

        //  定义 SQL
        String strSQL1 = "select * from user";
        String strSQL3 = "insert into t_user values(null, 2, 2, 2, 2, 2)";


        // ================= 执行区域 =================

        stmt = conn.createStatement();
        rs = stmt.executeQuery(strSQL1);

        out.println("<table border='1'><tr><th>结果集</th></tr>");
        while (rs.next()) {
            out.println("<tr><td>");
            out.print("id：" + rs.getInt(1) + "; ");
            out.print("username：" + rs.getString(2) + "; ");
            out.print("password：" + rs.getString(3));
            out.println("</td></tr>");
        }
        out.println("</table>");

    } catch (Exception e) {
        e.printStackTrace(); //错误堆栈
        out.println("发生错误：" + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>