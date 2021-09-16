package com.enercomn.web.A07EquipmentMaintenance.controller;

import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.service.A07TbEquipmentMaintenanceService;
import com.enercomn.web.A07EquipmentMaintenance.time.AsyncScheduleTask;
import com.enercomn.web.A09SpotCheck.service.A09TbSpotCheckService;
import com.enercomn.web.A10CheckPlan.service.A10TbEquipmentCheckPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date: 2021/9/2 16:41<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@RestController
@RequestMapping("timing")
public class TimeTestController {

    @Autowired
    private A07TbEquipmentMaintenanceService maintenanceService;
    @Autowired
    private A09TbSpotCheckService checkService;
    @Autowired
    private A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService;

    @Async
    @GetMapping("/everyDay")
    public void everyDay() throws InterruptedException {
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(1);
        checkService.batchGenerateCheckByMaintenance(maintenanceList);
    }

    @Async
    @GetMapping("/everyWeek")
    public void everyWeek(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(2);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };

    @Async
    @GetMapping("/everyMonth")
    public void everyMonth(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(3);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };

    @Async
    @GetMapping("/everyQuarter")
    public void everyQuarter(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(4);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };

    @Async
    @GetMapping("/everyYear")
    public void everyYear(){
        final List<A07TbEquipmentMaintenance> maintenanceList = maintenanceService.getMaintenanceByWeek(6);
        a10TbEquipmentCheckPlanService.generateCheckPlanByMaintenance(maintenanceList);
    };
}
