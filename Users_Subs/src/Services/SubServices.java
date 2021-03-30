/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.User;
import Entities.sub;
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
public class SubServices {
    Connection c = DBConnexion.getInstance().getCnx();
    
  public void AddSub(sub m)throws SQLException{
        Statement st;
        try{
        st = c.createStatement();
        String rec = "INSERT INTO `subs`(`user_id`,`stade_id`,`date_debut`,`date_fin`,`prix`,`photo`) VALUES ('"+m.getUser_id()+"','"+m.getStade_id()+"','"+m.getDate_debut()+"','"+m.getDate_fin()+"','"+m.getPrix()+"','"+m.getPhoto()+"')";
        st.executeUpdate(rec);
        System.out.println("Sub Added Successfully");
        }catch(SQLException ex){
        Logger.getLogger(SubServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<sub>getAll()throws SQLException{
        List<sub> us = new ArrayList<>();
        PreparedStatement p;
        p=c.prepareStatement("SELECT * FROM subs");
        ResultSet rs= p.executeQuery();
        while(rs.next()){
        sub m = new sub(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getFloat(6),rs.getString(7));
        us.add(m);
        }
        return us;
    }
   public void ReadSub(){
    PreparedStatement pt;
    try{
    pt = c.prepareStatement("SELECT * FROM subs");
    ResultSet rs = pt.executeQuery();
    while (rs.next()){
    System.out.println("sub [  num_sub : " + rs.getInt(1) +   ",  user_id  " + rs.getInt(2) +   ",  stade_id " + rs.getString(3) +", date_debut "  +rs.getDate(4)+" date_fin "+rs.getDate(5)+" prix "+rs.getFloat(6)+" photo "+ rs.getString(7));
    }
    }catch(SQLException ex){
    Logger.getLogger(SubServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   
       public void UpdateSub(int id, sub m) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE subs SET user_id=?,stade_id=?,date_debut=?,date_fin=?,prix=?,photo=? where num_sub = ?");

            pt.setInt(1, m.getUser_id());
            pt.setString(2, m.getStade_id());
            pt.setDate(3,m.getDate_debut());
            pt.setDate(4,m.getDate_fin());
            pt.setFloat(5,m.getPrix());
            pt.setString(6, m.getPhoto());              
            pt.setInt(7, id);
            pt.executeUpdate();
            System.err.println("subscription Updated Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(SubServices.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    public void DeleteSub(sub m) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("delete from subs where num_sub=? ");
            pt.setInt(1, m.getNum_sub());
            pt.executeUpdate();
            System.out.println("subscription Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(SubServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public List<sub>  SearchSub(int user_id){
        List<sub> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from subs where user_id ='"+user_id+"'");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    sub m = new sub(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getFloat(6),rs.getString(7));
    us.add(m);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(SubServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return us;
    }
    
    public List<sub>  SortListSubs(){
        List<sub> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from subs order by name ASC");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    sub m = new sub(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getFloat(6),rs.getString(7));
    us.add(m);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(SubServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return us;
    }
    
}
