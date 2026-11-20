/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.controller;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author honga
 */
public interface Duan1Controller {

    default void showJDialog(JDialog dialog) {
        dialog.setVisible(true);
    }
    
}
