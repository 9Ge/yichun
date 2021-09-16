package com.enercomn.web.A01EquipmentManagement.mapper;

import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentResume;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Enercomn
 */
@Repository
public interface A01TbEquipmentResumeMapper extends Mapper<A01TbEquipmentResume> {

    List<A01TbEquipmentResume> queryResume(A01TbEquipmentResume a01TbEquipmentResume);
}