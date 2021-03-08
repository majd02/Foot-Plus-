/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.models.JavaMailUtil;

/**
 *
 * @author USER
 */
public class JavaMail {
    public static void main(String[] args) throws Exception {
        JavaMailUtil.sendMail("majd.idani@esprit.tn");
    }
}
