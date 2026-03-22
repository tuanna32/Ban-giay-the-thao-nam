/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.diachikhachhang;

/**
 *
 * @author Asus
 */
public interface diachikhachhangDAO extends CrudDAO<diachikhachhang, Integer>{
 public void add(diachikhachhang dc);
  public diachikhachhang findByDCCT(String diachi);
}
