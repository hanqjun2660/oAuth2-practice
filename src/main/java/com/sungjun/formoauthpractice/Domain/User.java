package com.sungjun.formoauthpractice.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "MEMBER")
@Data
@NoArgsConstructor
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR"
        , sequenceName = "MEMBER_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "UNIQUENUM")
    private Long uniqueNum;

    private String userid;

    private String userpw;

    private String email;

    private String profileimg;

    private String role;

    private String provider;

    @Builder
    public User(String userid, String userpw, String email, String profileimg, String role, String provider) {
        this.userid = userid;
        this.userpw = userpw;
        this.email = email;
        this.profileimg = profileimg;
        this.role = role;
        this.provider = provider;
    }
}
