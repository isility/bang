package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.DeptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface DeptDAO {
    public List<DeptDTO> getAll();

}
