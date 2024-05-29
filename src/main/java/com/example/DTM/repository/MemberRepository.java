package com.example.DTM.repository;

import com.example.DTM.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByName(String name);
}
