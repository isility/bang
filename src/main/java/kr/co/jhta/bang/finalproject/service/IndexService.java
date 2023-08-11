package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.IndexDAO;
import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IndexService {

    @Autowired
    IndexDAO dao;

    public List<ProductListDTO> selectAllSpeaker() {
        return dao.selectAllSpeaker();
    }

    public List<ProductListDTO> selectAllEarphone() {
        return dao.selectAllEarphone();
    }

    public List<ProductListDTO> selectAllHeadphone() {
        return dao.selectAllHeadphone();
    }
}

