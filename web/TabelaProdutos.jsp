<%-- 
    Document   : principal
    Created on : 21/01/2019, 23:15:06
    Author     : GUSTAVO
--%>

<%@page import="java.util.List"%>
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
        <h1> Tabela de Produtos </h1>
        <table align="center" border="2px" widht="50%"
               <tr>
                <th>Descricao</th>
                <th>Preco</th>
                <th>Quantidade</th>
                <th>Categoria ID</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            
            <%
             ProdutosDAO dao = new ProdutosDAO();
             List<Produtos> listap = dao.read();
             for(Produtos p: listap){
            %>
            <tr>
                <th><%=p.getDescricao() %></th>
                <th><%=p.getPreco()%></th> 
                <th><%=p.getQuantidade()%></th>
                <th><%=p.getCategoria()%></th>
                <th><a href="InserirProduto.jsp">Editar</a></th>
                <th><a href="DeletarProdutos.jsp?idprodutos=<%=p.getId()%>">Excluir</a></th>
            </tr>
            <%
              }  
            %>
            <a href="InserirProduto.jsp" type="submit" >Inserir</a>
        </table>
    </body>
</html>
