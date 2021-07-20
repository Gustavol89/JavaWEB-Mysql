<%-- 
    Document   : NovosProdutos
    Created on : 23/01/2019, 00:32:52
    Author     : GUSTAVO
--%>

<%@page import="model.bean.Categorias"%>
<%@page import="model.bean.Produtos"%>
<%@page import="model.dao.ProdutosDAO"%>
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
            ProdutosDAO dao = new ProdutosDAO();
            Produtos pr = new Produtos();
            Categorias cat = new Categorias();
            
            pr.setDescricao(request.getParameter("descricao"));
            pr.setPreco(Float.parseFloat(request.getParameter("preco")));
            pr.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            cat.setId(Integer.parseInt(request.getParameter("categoriadesc")));
            pr.setCategoria(cat);
            dao.create(pr);
            
            String listadepr = "TabelaProdutos.jsp";
            response.sendRedirect(listadepr);
            
            } catch (Exception e) {
                System.err.println("erro: "+e);
            } 
        %>
    </body>
</html>
