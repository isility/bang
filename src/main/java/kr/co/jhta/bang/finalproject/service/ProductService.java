package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.ImageDTO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductListDAO dao;

    public List<ProductListDTO> selectAll(int categoryNumber){
        return dao.selectAll(categoryNumber);
    }

    public int getTotal(int categoryNumber) {
        return dao.getTotal(categoryNumber);
    }

    public ProductListDTO selectOne(int productNumber){
        return dao.selectOne(productNumber);
    }

    public List<ImageDTO> selectList(int productNumber){
        return dao.selectList(productNumber);
    }

}
