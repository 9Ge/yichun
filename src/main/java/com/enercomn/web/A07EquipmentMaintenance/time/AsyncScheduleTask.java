package com.enercomn.web.A07EquipmentMaintenance.time;

import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.service.A07TbEquipmentMaintenanceService;
import com.enercomn.web.A09SpotCheck.service.A09TbSpotCheckService;
import com.enercomn.web.A10CheckPlan.service.A10TbEquipmentCheckPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2021/8/13 16:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component
@EnableScheduling
@EnableAsync
@SuppressWarnings({"all"})
public class AsyncScheduleTask {

    @Autowired
    private A07TbEquipmentMaintenanceService maintenanceService;
    @Autowired
    private A09TbSpotCheckService checkService;
    @Autowired
    private A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService;

    @Async
    //@Scheduled(cron = "0 0 5 * * ?")  //每天5点
    @Scheduled(cron = "0 0,32 16 * * ? ")  //每天5点
    public void everyDay() throws InterruptedException {
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(1);
        checkService.batchGenerateCheckByMaintenance(maintenanceList);
    }

    @Async
    //@Scheduled(cron="0 0 5 ? * MON") //每周一五点
    @Scheduled(cron = "0 0,32 16 * * ? ")  //每天5点
    public void everyWeek(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(2);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };

    @Async
    @Scheduled(cron="0 0 4 1 * ?") //每月1号4点
    public void everyMonth(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(3);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };

    @Async
    @Scheduled(cron= "0 0 3 1 1,4,7,10 *") //每季度1号3点
    public void everyQuarter(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(4);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };

    @Async
    @Scheduled(cron= "0 10 5 1 1 *") //每年1月1号5点10分
    public void everyYear(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(6);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };


}
