package cn.hippo4j.console.controller;

import cn.hippo4j.common.constant.Constants;
import cn.hippo4j.common.monitor.Message;
import cn.hippo4j.common.monitor.MessageWrapper;
import cn.hippo4j.common.toolkit.MessageConvert;
import cn.hippo4j.common.web.base.Result;
import cn.hippo4j.common.web.base.Results;
import cn.hippo4j.config.model.biz.monitor.MonitorActiveRespDTO;
import cn.hippo4j.config.model.biz.monitor.MonitorQueryReqDTO;
import cn.hippo4j.config.model.biz.monitor.MonitorRespDTO;
import cn.hippo4j.config.monitor.QueryMonitorExecuteChoose;
import cn.hippo4j.config.service.biz.HisRunDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Monitor controller.
 *
 * @author chen.ma
 * @date 2021/12/7 22:29
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(Constants.BASE_PATH + "/monitor")
public class MonitorController {

    private final HisRunDataService hisRunDataService;

    private final QueryMonitorExecuteChoose queryMonitorExecuteChoose;

    @GetMapping
    public Result<List<MonitorRespDTO>> queryMonitor(MonitorQueryReqDTO reqDTO) {
        List<MonitorRespDTO> monitorRespList = hisRunDataService.query(reqDTO);
        return Results.success(monitorRespList);
    }

    @PostMapping("/info")
    public Result<MonitorActiveRespDTO> querInfoThreadPoolMonitor(@RequestBody MonitorQueryReqDTO reqDTO) {
        MonitorActiveRespDTO monitorRespList = hisRunDataService.queryInfoThreadPoolMonitor(reqDTO);
        return Results.success(monitorRespList);
    }

    @PostMapping
    public Result dataCollect(@RequestBody MessageWrapper messageWrapper) {
        Message message = MessageConvert.convert(messageWrapper);
        queryMonitorExecuteChoose.chooseAndExecute(message);
        return Results.success();
    }

}
