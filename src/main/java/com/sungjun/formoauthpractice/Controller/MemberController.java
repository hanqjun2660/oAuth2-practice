package com.sungjun.formoauthpractice.Controller;

import com.sungjun.formoauthpractice.Service.PrincipalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final PasswordEncoder passwordEncoder;

    private final PrincipalService principalService;


    @GetMapping("/join")
    public String join(Model model) {

        List<String> domainList = new ArrayList<>();
        domainList.add("@naver.com");
        domainList.add("@daum.net");
        domainList.add("@google.com");

        model.addAttribute("emailList", domainList);

        return "/member/join";
    }

    @PostMapping("/regist")
    @ResponseBody
    public Map<String, String> registMember(@RequestBody Map<String, String> paramMap, RedirectAttributes rttr) {

        int result = principalService.save(paramMap);
        Map<String, String> response = new HashMap<>();

        if(result == 1) {
            response.put("msg", "회원가입에 성공하였습니다.");
        } else {
            response.put("msg", "회원가입에 실패하였습니다.");
        }

        return response;
    }

}
