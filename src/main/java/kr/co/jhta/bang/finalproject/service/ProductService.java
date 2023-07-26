package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductListDAO dao;

    public List<ProductListDTO> selectAll(int category_number){
        return dao.selectAll(category_number);
    }

    public int getTotal(int category_number) {
        return dao.getTotal(category_number);
    }
}
