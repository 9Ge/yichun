package com.enercomn.web.A12Repair.mapper;


import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedger;
import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedgerVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A12TbMaintenanceRecordLedgerMapper extends Mapper<A12TbMaintenanceRecordLedger> {

    List<A12TbMaintenanceRecordLedgerVo> queryLedger(A12TbMaintenanceRecordLedger ledger);
}