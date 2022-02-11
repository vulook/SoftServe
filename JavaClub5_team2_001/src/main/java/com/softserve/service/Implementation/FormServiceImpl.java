package com.softserve.service.Implementation;

import com.softserve.dao.FormDao;
import com.softserve.entity.Form;
import com.softserve.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    FormDao formDao;

    @Override
    public Form create(Form form) {
        return formDao.save(form);
    }

    @Override
    public Form findByID(Long id) {
        return formDao.getByID(id);
    }

    @Override
    public Form delete(Long id) {
        return formDao.delete(id);
    }


    @Override
    public List<Form> getAll() {
        return formDao.getAll();
    }

    @Override
    public List<Form> findAllByID() {
        return formDao.getAllByUser();
    }

}
