/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.chatlieu;

/**
 *
 * @author Asus
 */
public interface chatlieuDAO extends CrudDAO<chatlieu,Integer>{
    chatlieu findByTen(String ten);
    chatlieu findByTenRealtheoidhoadon(String ten,int id);
}

