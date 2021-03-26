/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Youssef
 */
public class UserServices {
    Connection c = DBConnexion.getInstance().getCnx();
    
        public void AddUser(User m)throws SQLException{
        Statement st;
        try{
        st = c.createStatement();
        String rec = "INSERT INTO `users`(`name`,`lastname`,`email`,`password`,`gender`,`photo`,`phone`,`birthday`) VALUES ('"+m.getName()+"','"+m.getLastname()+"','"+m.getEmail()+"','"+m.getPassword()+"','"+m.getGender()+"','"+m.getPhoto()+"','"+m.getPhone()+"','"+m.getBirthday()+"')";
        st.executeUpdate(rec);
        System.out.println("User Added Successfully");
        }catch(SQLException ex){
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public List<User>getAll()throws SQLException{
        List<User> us = new ArrayList<>();
        PreparedStatement p;
        p=c.prepareStatement("SELECT * FROM users");
        ResultSet rs= p.executeQuery();
        while(rs.next()){
        User m = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getDate(9));
        us.add(m);
        }
        return us;
    }
     public void ReadUser(){
    PreparedStatement pt;
    try{
    pt = c.prepareStatement("SELECT * FROM users");
    ResultSet rs = pt.executeQuery();
    while (rs.next()){
    System.out.println("User [  id : " + rs.getInt(1) +   ",  name  " + rs.getString(2) +   ",  lastname " + rs.getString(3) +", email "  +rs.getString(4)+" password "+rs.getString(5)+" gender "+rs.getString(6)+" photo "+ rs.getString(7)+" phone "+rs.getInt(8)+" birthday "+rs.getDate(9));
    }
    }catch(SQLException ex){
    Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void UpdateUser(int id, User m) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE users SET name=?,lastname=?,email=?,password=?,gender=?,photo=?,phone=?,birthday=? where id = ?");

            pt.setString(1, m.getName());
            pt.setString(2, m.getLastname());
            pt.setString(3,m.getEmail());
            pt.setString(4,m.getPassword());
            pt.setString(5,m.getGender());
            pt.setString(6, m.getPhoto());
            pt.setInt(7, m.getPhone());
            pt.setDate(8,m.getBirthday());              
            pt.setInt(9, id);
            pt.executeUpdate();
            System.err.println("User Updated Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void DeleteUser(User m) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("delete from users where id=? ");
            pt.setInt(1, m.getId());
            pt.executeUpdate();
            System.out.println("User Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<User>  SearchMembre(String name,String lastname){
        List<User> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from users where name ='"+name+"' and lastname = '"+lastname+"'");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    User m = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getDate(9));
    us.add(m);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return us;
    }
    
    public List<User>  SortListUsers(){
        List<User> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from users order by name ASC");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    User m = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getDate(9));
    us.add(m);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return us;
    }
}
