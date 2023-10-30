package com.sungjun.formoauthpractice.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "MEMBER")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UNIQUENUM")
    private Long uniqueNum;

    private String userid;

    private String userpw;

    private String email;

    private String profileimg;

    private String role;

    @Builder
    public User(/*Long uniqueNum,*/ String userid, String userpw, String email, String profileimg, String role) {
        /*this.uniqueNum = uniqueNum;*/
        this.userid = userid;
        this.userpw = userpw;
        this.email = email;
        this.profileimg = profileimg;
        this.role = role;
    }
}
