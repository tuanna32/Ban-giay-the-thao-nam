/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.mausac;

/**
 *
 * @author Asus
 */
public interface mausacDAO extends CrudDAO<mausac,Integer>{
    mausac findByTen(String ten);
     mausac findByTenRealtheoidhoadon(String ten,int id);
}

