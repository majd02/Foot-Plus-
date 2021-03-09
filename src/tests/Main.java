/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Services.ServiceReservation;
import Services.ServiceStade;
import Utils.DataSource;
import models.Reservation;
import models.Stade;

/**
 *
 * @author asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource ds1 = DataSource.getInstance();
        ServiceStade sd = new ServiceStade();
        sd.Afficher().forEach(System.out::println);
        ServiceReservation sr = new ServiceReservation();
        sr.Ajouter(new Reservation(29,12));
        
    }
    
}
