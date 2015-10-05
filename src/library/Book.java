/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author desarrollador
 */
public class Book extends Conection{
    
    public int create(int ID, String NAME){
        int num_result = 0;
        
        this.conectar();
        
        try {
            this.query = this.conection.prepareStatement("INSERT INTO book VALUES(?,?)");
            this.query.setInt(1, ID);
            this.query.setString(2, NAME);
            num_result = this.query.executeUpdate();
            this.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return num_result;
        }
        return num_result;
    }
    
    public String[] all(){
        int num = 0;
        
        this.conectar();
        
        num = this.num_registers_books();
        
        String[] books = new String[num];
        String[] data = new String[num];
        
        try {
            this.query = this.conection.prepareStatement("SELECT * FROM book");
            this.data = this.query.executeQuery();
            
            int count = 0;
            
            while(this.data.next()){
                books[count] = "| ID: \t" + this.data.getInt("id") + "\t| Name:  "+ this.data.getString("name");
                data[count] = books[count];
                count++;
            }
            
            this.desconectar();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return data;
    }
    
    public int num_registers_books(){
        int num_reg = 0;
        
        try {
            this.query = this.conection.prepareStatement("SELECT COUNT(*) FROM book");
            this.data = this.query.executeQuery();
            
            while(this.data.next()){
                num_reg = Integer.parseInt(this.data.getString(1));
            }
            
            this.num_registers = num_reg;
        } catch (SQLException e) {
            return 0;
        }
        return num_reg;
    }

    public int find(int id_book) {        
        int res = 0;
        
        this.conectar();
        
        try {
            this.query = this.conection.prepareStatement("SELECT * FROM book WHERE id = ?");
            this.query.setInt(1, id_book);
            
            this.data = this.query.executeQuery();
            int count = 0;
            String[] $data = new String[2];
            
            while(this.data.next()){
                count++;
                $data[0] = "" + this.data.getInt("id");
                $data[1] = "" + this.data.getString("name");
            }
            
            this.data_book = $data;
            
            res = count;
            
            this.desconectar();
        } catch (Exception e) {
            return res;
        }
        return res;
    }
    
    public String[] data(){
        return this.data_book;
    }
    
    public ResultSet data_books(){        
        try {
            this.conectar();
            this.query = this.conection.prepareStatement("SELECT * FROM book");
            this.data = this.query.executeQuery();
            return this.data;
        } catch (Exception e) {
            return null;
        }
    }

    public int update(int id_book, String name_book) {
        int num_res = 0;
        
        this.conectar();
        
        try {
            this.query = this.conection.prepareStatement("UPDATE book SET name = ? WHERE id = ?");
            this.query.setString(1, name_book);
            this.query.setInt(2, id_book);
            
            num_res = this.query.executeUpdate();
            
            this.desconectar();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return num_res;
    }

    public int delete(int id_book) {
        int num_res = 0;
        
        this.conectar();
        
        try {
            this.query = this.conection.prepareStatement("DELETE FROM book WHERE id = ?");
            this.query.setInt(1, id_book);
            
            num_res = this.query.executeUpdate();
            
            this.desconectar();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return num_res;
    }
    
}
