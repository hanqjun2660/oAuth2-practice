package com.sungjun.formoauthpractice.Service;

import com.sungjun.formoauthpractice.Domain.PrincipalDetails;
import com.sungjun.formoauthpractice.Domain.User;
import com.sungjun.formoauthpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * OAuth용 Service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauthUserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthorizationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes: {}", oAuth2User.getAttributes());

        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        Map<String, Object> account = oAuth2User.getAttribute("kakao_account");

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("id").toString();
        String loginId = provider + "_" + providerId;

        String email = (String) account.get("email");
        String profileImg = (String) properties.get("profile_image");

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserid(loginId));
        User user;

        if(optionalUser.isEmpty()) {
            user = User.builder()
                    .userid(loginId)
                    .role("USER")
                    .email(email)
                    .profileimg(profileImg)
                    .build();
            userRepository.save(user);
        } else {
            user = optionalUser.get();
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
