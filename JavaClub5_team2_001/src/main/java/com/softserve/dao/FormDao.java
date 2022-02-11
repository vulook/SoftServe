package com.softserve.dao;

import com.softserve.entity.Form;

import java.util.List;

public interface FormDao {

    List<Form> getAllByUser();

    List<Form> getAll();

    Form save(Form t);

    Form delete(long id);

    Form getByID(long id);

//    void request(long id);

}
