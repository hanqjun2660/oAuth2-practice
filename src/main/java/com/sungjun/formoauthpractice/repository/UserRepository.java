package com.sungjun.formoauthpractice.repository;

import com.sungjun.formoauthpractice.Domain.MemberDTO;
import com.sungjun.formoauthpractice.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    public User findByUserid(String userid);
}
