/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Stade;

/**
 *
 * @author asus
 */
public class ServiceStade implements IServices<Stade> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Ajouter(Stade t) {
        try{
        String requete = "INSERT INTO stade(nom,adresse,prixph,contact,image) VALUES (?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getAdresse());
        pst.setInt(3, t.getPrixph());
        pst.setString(4, t.getContact());
        pst.setString(5, t.getImage());
        pst.executeUpdate();
        System.out.println("Stade ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(Stade t) {
        try{
        String requete = "DELETE FROM stade WHERE id_stade=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId_stade());
        pst.executeUpdate();
        System.out.println("Stade Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    public int deleteStade(int id_stade) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "delete from stade where id_stade=" + id_stade;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void Modifier(Stade t) {
       try{
        String requete = "UPDATE stade SET nom=?,adresse= ?,prixph=?,contact=?,image=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getAdresse());
        pst.setInt(3, t.getPrixph());
        pst.setString(4, t.getContact());
        pst.setString(5, t.getImage());
        pst.executeUpdate();
        System.out.println("Stade ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Stade> Afficher() {
         List<Stade> list = new ArrayList<>();
        try{
        String requete = "SELECT * FROM stade";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            list.add(new Stade(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
        }
        System.out.println("Stade !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }
    
}
