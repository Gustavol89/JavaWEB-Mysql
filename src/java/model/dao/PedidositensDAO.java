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
import model.bean.PedidoItens;
import model.bean.Pedidos;
import model.bean.Produtos;



/**
 *
 * @author GUSTAVO
 */
public class PedidositensDAO {
    
     //METODO PARA INSERIR NA TABELA PEDIDOSITENS DO BANCO DE DADOS
    public void create(PedidoItens p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO produtos (quantidade,preco,total,pedidos_idpedidos,produtos_idprodutos) VALUES (?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getQuantidade());
            stmt.setFloat(2, p.getPreco());
            stmt.setFloat(3, p.getTotal());
            stmt.setInt(4, p.getPedidos().getId());
            stmt.setInt(5, p.getProdutos().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("erro ao inserir PedidoItens: " + e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    
    //METODO PARA  LISTAR NA TABELA PEDIDOS ITENS
    
    public List<PedidoItens> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<PedidoItens> pedidositens = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM pedidoitens ";
            stmt = con.prepareStatement(sql);
            
            while(rs.next()){
            PedidoItens ped = new PedidoItens();
            ped.setId(rs.getInt("idpedidoitens"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setPreco(rs.getFloat("preco"));
            ped.setTotal(rs.getFloat("total"));
            Pedidos p = new Pedidos();
            p.setId(rs.getInt("idpedidos"));
            p.setData(rs.getDate("data"));
            p.setStatus(rs.getString("status"));
            p.setSessao(rs.getString("sessao"));
            ped.setPedidos(p);
            Produtos po = new Produtos();
            po.setId(rs.getInt("idprodutos"));
            po.setDescricao(rs.getString("descricao"));
            po.setPreco(rs.getFloat("preco"));
            po.setQuantidade(rs.getInt("quantidade"));
            Categorias ca = new Categorias();
            po.setCategoria(ca);
            pedidositens.add(ped);
            }
        } catch (SQLException e) {
            System.err.println("erro ao listar pedidos itens: "+e);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
            ConnectionFactory.CloseRS(rs);
        }
        return pedidositens;
    }
    
    
        //METODO PARA ATUALIZAR OS DADOS DA TABELA
    public void update(PedidoItens p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedidoitens SET quantidade = ?,preco = ?, totao = ? ,pedidos_idpedidos,produtos_idprodutos = ? WHERE idpedidoitens = ?");
            stmt.setInt(1, p.getQuantidade());
            stmt.setDouble(2, p.getPreco());
            stmt.setFloat(3, p.getTotal());
            stmt.setInt(4, p.getPedidos().getId());
            stmt.setInt(5, p.getProdutos().getId());
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("erro ao listar pedido iten " + e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    
     //METODO PARA DELETAR
     public void delete(PedidoItens p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pedidoitens WHERE id = ?");
            stmt.setInt(1, p.getId());
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("erro ao deletar produto :"+e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
        }

     }
      
}
