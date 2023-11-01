package com.sungjun.formoauthpractice.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String userid;
    private String email;
    private String userpw;
}
