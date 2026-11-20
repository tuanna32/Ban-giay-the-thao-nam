/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.loaidegiay;



/**
 *
 * @author Admin
 */
public interface loaidegiayDAO extends CrudDAO<loaidegiay, Integer>{
    loaidegiay findByTen(String ten);
}
