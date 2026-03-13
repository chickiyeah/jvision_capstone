package egovframework.yakbang.web;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.yakbang.service.DeviceService;
import egovframework.yakbang.service.vo.ClinicalLogVO;
import egovframework.yakbang.service.vo.DeviceVO;

@RestController
@RequestMapping("/yakbang/api/device")
public class YakbangDeviceController {

    @Resource(name = "deviceService")
    private DeviceService deviceService;

    @GetMapping("/{userId}")
    public Map<String, Object> getUserDeviceDetail(@PathVariable("userId") int userId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        EgovMap deviceDetail = deviceService.selectUserDeviceDetail(userId);
        
        if (deviceDetail != null) {
            resultMap.put("result", "success");
            resultMap.put("data", deviceDetail);
        } else {
            resultMap.put("result", "fail");
            resultMap.put("message", "Device not found for this user");
        }
        return resultMap;
    }

    @PostMapping("/register")
    public Map<String, Object> registerDevice(@RequestBody DeviceVO deviceVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        deviceService.insertDevice(deviceVO);
        resultMap.put("result", "success");
        return resultMap;
    }

    @PutMapping("/status")
    public Map<String, Object> updateDeviceStatus(@RequestBody DeviceVO deviceVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        deviceService.updateDeviceStatus(deviceVO);
        resultMap.put("result", "success");
        return resultMap;
    }

    @PostMapping("/log")
    public Map<String, Object> addClinicalLog(@RequestBody ClinicalLogVO logVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        deviceService.insertClinicalLog(logVO);
        resultMap.put("result", "success");
        return resultMap;
    }
}