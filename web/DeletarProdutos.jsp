<%-- 
    Document   : DeletarProdutos
    Created on : 23/01/2019, 23:11:23
    Author     : GUSTAVO
--%>

<%@page import="model.dao.ProdutosDAO"%>
<%@page import="model.bean.Produtos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MercadinhoWeb</title>
    </head>
    <body>
       
        <% 
        try {
            Produtos pr = new Produtos();
            ProdutosDAO dao = new ProdutosDAO();
            
            pr = dao.FindById((request.getParameter("idprodutos")));
            dao.delete(pr);
            
            } catch (Exception e) {
                System.err.println("erro: "+e);
            }
        %>
    </body>
</html>
