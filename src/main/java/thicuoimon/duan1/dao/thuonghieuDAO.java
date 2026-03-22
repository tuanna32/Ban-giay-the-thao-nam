/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.thuonghieu;

/**
 *
 * @author Asus
 */
public interface thuonghieuDAO extends CrudDAO<thuonghieu, Integer>{
    thuonghieu findByTen(String ten);
     thuonghieu findByTenRealtheoidhoadon(String ten,int id);
}
