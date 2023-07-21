package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.DeptDAO;
import kr.co.jhta.bang.finalproject.dto.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class templeserivce {

    @Autowired
    DeptDAO dao;

    public List<DeptDTO> readAll(){
        return dao.getAll();
    }
}
