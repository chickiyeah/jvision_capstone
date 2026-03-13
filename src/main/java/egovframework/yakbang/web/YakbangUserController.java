package egovframework.yakbang.web;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.yakbang.service.UserService;
import egovframework.yakbang.service.vo.UserVO;

@RestController
@RequestMapping("/yakbang/api/user")
public class YakbangUserController {

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody UserVO userVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        userService.insertUser(userVO);
        resultMap.put("result", "success");
        return resultMap;
    }

    @GetMapping("/check-id")
    public Map<String, Object> checkDuplicateId(@RequestParam("loginId") String loginId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        int count = userService.checkDuplicateId(loginId);
        resultMap.put("count", count);
        resultMap.put("isDuplicate", count > 0);
        return resultMap;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserVO userVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        EgovMap loginUser = userService.actionLogin(userVO);
        
        if (loginUser != null && !loginUser.isEmpty()) {
            resultMap.put("result", "success");
            resultMap.put("user", loginUser);
        } else {
            resultMap.put("result", "fail");
            resultMap.put("message", "Invalid ID or Password");
        }
        return resultMap;
    }

    @PutMapping("/profile")
    public Map<String, Object> updateUserProfile(@RequestBody UserVO userVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        userService.updateUserProfile(userVO);
        resultMap.put("result", "success");
        return resultMap;
    }

    @DeleteMapping("/{userId}")
    public Map<String, Object> deleteUser(@PathVariable("userId") int userId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        userService.deleteUser(userId);
        resultMap.put("result", "success");
        return resultMap;
    }
}