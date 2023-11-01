package com.sungjun.formoauthpractice.Service;

// 시큐리티에서 설정에서 LoginProcessingUrl("/login");
// "/login" 요청이 오면 자동으로 UserDetailsService 타입으로 loC 되어있는  loadUserByUsername 함수가 실행된다.!
// Authentication 객체로 만들어준다

import com.sungjun.formoauthpractice.Domain.PrincipalDetails;
import com.sungjun.formoauthpractice.Domain.User;
import com.sungjun.formoauthpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * 일반 로그인용 Service
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    //시큐리티 session => Authentication => UserDetails
    // 여기서 리턴 된 값이 Authentication 안에 들어간다.(리턴될때 들어간다.)
    // 그리고 시큐리티 session 안에 Authentication 이 들어간다.
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        User findUser = userRepository.findByUserid(userid);
        if(findUser!=null) {
            return new PrincipalDetails(findUser);
        }

        return null;
    }

    public int save(Map<String, String> paramMap) {
        int result;
        User user;

        user = User.builder()
                .userid(paramMap.get("userid"))
                .userpw(passwordEncoder.encode(paramMap.get("userpw")))
                .email(paramMap.get("email"))
                .provider("site")
                .role("USER")
                .build();

        Optional<User> optionalUser = Optional.of(userRepository.save(user));

        if(!optionalUser.isEmpty()) {
            result = 1;
        } else {
            return -1;
        }

        return result;
    }
}
