/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.utility;

import java.util.List;

/**
 *
 * @author Alexius Surya 1772043
 */
public interface DaoService<Object> {

    public int addData(Object object);

    public int updateData(Object object);

    public int deleteData(Object object);

    public List<Object> showAllData();

    public Object getOneData(Object object);
}
