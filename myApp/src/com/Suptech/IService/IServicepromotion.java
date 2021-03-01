/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Suptech.IService;

import com.Suptech.Entite.produit;
import com.Suptech.Entite.promotion;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author moham
 */
public interface IServicepromotion <L> {
     void ajouter(L t) throws SQLException;
    boolean delete(int id_promo) throws SQLException;
    boolean update(promotion p) throws SQLException;
   // List<Note> rechercheParNoteExamen(String nom_matiere)throws SQLException;
    List<L> readAll() throws SQLException;
    
}
