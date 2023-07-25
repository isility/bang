package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductListDAO dao;

    public List<ProductDTO> selectAll(){
        return dao.selectAll();
    }

}
