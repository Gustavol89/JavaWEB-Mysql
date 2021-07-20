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
import model.bean.Pedidos;

/**
 *
 * @author GUSTAVO
 */
public class PedidosDAO {
    
    //METODO PARA INSERIR NO BANCO DE DADOS
    public void create(Pedidos pe){
        
     Connection con = ConnectionFactory.getConnection();
     PreparedStatement stmt = null;
     
        try {
          String sql = "INSERT INTO pedidos (data,status,sessao) VALUES (?,?,?)";
          stmt = con.prepareStatement(sql);
          stmt.setDate(1, pe.getData());
          stmt.setString(2, pe.getStatus());
          stmt.setString(3, pe.getSessao());
          stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("errp ao inserir pedido" +e);
        }finally{
           ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    
    //METODO PARA LISTAR A TABELA
    public List<Pedidos> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedidos> ped = new ArrayList<>();

        try {
            String sql = "SELECT * FROM pedidos";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pedidos pe = new Pedidos();
                pe.setId(rs.getInt("idpedidos"));
                pe.setData(rs.getDate("data"));
                pe.setStatus(rs.getString("status"));
                pe.setSessao(rs.getString("sessao"));
                ped.add(pe);
            }
        } catch (SQLException e) {
            System.err.println("erro ao listar produtos: " + e);
        } finally {
            ConnectionFactory.CloseConnection(con, stmt);
            ConnectionFactory.CloseRS(rs);
        }
        return ped;
    }
    
    //METODO PARA ATUALIZAR A TABELA
    public void update(Pedidos pe){
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
       String sql = "UPDATE pedidos SET data = ?,status = ?,sessao = ? WHERE idpedidos = ?";
       stmt = con.prepareStatement(sql);
       stmt.setDate(1, pe.getData());
       stmt.setString(2, pe.getStatus());
       stmt.setString(3, pe.getSessao());
       stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("erro ao atualizar pedido");
        }finally{
           ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    
    //METODO PARA DELETAR DA TABELA
    public void delete(Pedidos ped){
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
          String sql = "DELETE FROM pedidos WHERE idpedidos = ?";
          stmt = con.prepareStatement(sql);
          stmt.setInt(1, ped.getId());
          stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("erro ao deletar pedido "+e);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
         }
     }   
 }
    
    
    
    

