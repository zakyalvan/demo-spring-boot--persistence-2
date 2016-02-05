package id.tomatech.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.tomatech.learn.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
