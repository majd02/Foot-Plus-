/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Suptech.IService;

import com.Suptech.Entite.produit;
import java.sql.SQLException;
import java.util.List;

/**
 */
public interface IServiceproduit<T> {
     void ajouter(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(produit n) throws SQLException;
   // List<Note> rechercheParNoteExamen(String nom_matiere)throws SQLException;
    List<produit> trier() throws SQLException;
    List<T> readAll() throws SQLException;
   
    
    }

