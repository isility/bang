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

    public List<ProductListDTO> selectAll(int cno){
        return dao.selectAll(cno);
    }

    public int getTotal(int cno) {
        return dao.getTotal(cno);
    }

    public ProductListDTO selectOne(int pno){
        return dao.selectOne(pno);
    }

}
