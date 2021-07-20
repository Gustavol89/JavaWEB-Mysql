<%-- 
    Document   : InserirProduto
    Created on : 22/01/2019, 23:03:09
    Author     : GUSTAVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MercadinhoWeb</title>
    </head>
    <body>
        <form action="NovosProdutos.jsp" method="post"
              <div 
                <label>Descrição</label>
                  <input type="text" class="" name="descricao" placeholder="Digite a Descrição">
            </div>
              <div 
                <label>Preço</label>
                  <input type="text" class="" name="preco" placeholder="Digite o Preço">
            </div>
              <div 
                <label>Quantidade</label>
                  <input type="text" class="" name="quantidade" placeholder="Digite a Quantidade">
            </div>
              <div 
                <label>Categoria Comidas = 1, Bebidas = 2,Eletronicos = 3</label>
                  <input type="text" class="" name="categoriadesc" placeholder="Digite a Categoria">
            </div>
              <input type="submit" value="Salvar">
        </form>
    </body>
</html>
