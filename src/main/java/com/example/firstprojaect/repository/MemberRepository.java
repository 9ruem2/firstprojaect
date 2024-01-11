package com.example.firstprojaect.repository;

import com.example.firstprojaect.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
