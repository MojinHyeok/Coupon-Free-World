package com.ssafy.backend.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.user.model.UserModel;
import com.ssafy.backend.user.service.JwtServiceImpl;
import com.ssafy.backend.user.service.LoginService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/login")
public class LoginController {
	
	// 토큰 암호화를 위한 Jwt Service
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private LoginService service;
	
	// 로그인 URI(RequestBody에 id, pw 전송)
	@PostMapping("/confirm")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserModel model) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		try {
			UserModel loginUser = service.login(model);
			
			if(loginUser != null) {	// 사용자 존재할 시
				String token = jwtService.create("userid", loginUser.getUserID(), "access-token");
				resultMap.put("access-token", token);
				resultMap.put("message", token);
			} else {	// 사용자 없을 시
				resultMap.put("message", "fail");
			}
			status = HttpStatus.ACCEPTED;
			
		} catch(Exception e) {	// 
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	// 사용자 정보 확인
	@GetMapping("/info/{userid}")
    public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userID") String userID, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            try {
//				로그인 사용자 정보.
                UserModel model = service.userInfo(userID);
                
                resultMap.put("userInfo", model);
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
