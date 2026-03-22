/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.kichco;

/**
 *
 * @author Asus
 */
public interface kichcoDAO extends CrudDAO<kichco, Integer> {

    kichco findByTen(String ten);

    kichco findByTenRealtheoidhoadon(String ten, int id);
}

