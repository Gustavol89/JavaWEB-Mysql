/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categorias;
import model.bean.Produtos;

/**
 *
 * @author GUSTAVO
 */
public class ProdutosDAO {

    //METODO PARA INSERIR NA TABELA PRODUTOS DO BANCO DE DADOS
    public void create(Produtos p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO produtos (descricao,preco,quantidade,categorias_idcategorias) VALUES (?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setFloat(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setString(4, p.getCategoria().getDescricao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("erro ao inserir produtos: " + e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
        //METODO PARA LISTAR A TABELA PRODUTO
    public List<Produtos> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produtos";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos produtoss = new Produtos();
                produtoss.setId(rs.getInt("idprodutos"));
                produtoss.setDescricao(rs.getString("descricao"));
                produtoss.setPreco(rs.getFloat("preco"));
                produtoss.setQuantidade(rs.getInt("quantidade"));
                Categorias categoria = new Categorias();
                categoria.setId(rs.getInt("idprodutos"));
                categoria.setDescricao(rs.getString("descricao"));
                produtoss.setCategoria(categoria);
                produtos.add(produtoss);

            }
        } catch (SQLException e) {
            System.err.println("erro ao listar produtos: " + e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
            ConnectionFactory.CloseRS(rs);
        }
        return produtos;
    }
    
        //METODO PARA ATUALIZAR OS DADOS DA TABELA
    public void update(Produtos p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE produtos SET descricao = ? ,quantidade = ?,preco = ?, categorias_idcategorias = ? WHERE idprodutos = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getCategoria().getId());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("erro ao listar produto " + e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    //METODO PARA DELETAR DO BANCO DE DADOS
    public void delete(Produtos p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getId());

           stmt.executeQuery();

            
        } catch (SQLException e) {
            System.err.println("erro ao deletar produto :"+e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
        }

     }
    
    //METODO PARA BUSCAR PELO ID
    public int FindById(int id){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM produtos WHERE idprodutos = '"+id+"';";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produtos pr = new Produtos();
                Categorias ca = new Categorias();
                pr.setId(rs.getInt("idprodutos"));
                pr.setDescricao(rs.getString("descricao"));
                pr.setPreco((rs.getFloat("preco")));
                pr.setQuantidade(rs.getInt("quantidade"));
                ca.setId(rs.getInt("categorias_idcategorias"));
                ca.setDescricao(rs.getString("descricao"));
                pr.setCategoria(ca);
                
            }
        } catch (SQLException e) {
            System.err.println("erro: "+e);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
            ConnectionFactory.CloseRS(rs);
           }
        return id;
       
    }
      
      }

        
     
    
    
