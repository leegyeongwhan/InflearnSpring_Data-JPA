package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;
import study.datajpa.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

  //  @Query(name = "Member.findByUsername")
 //   List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username " +
            "and m.age = :age")
    List<Member> findUser(@Param("username") String username,
                          @Param("age") int age);

    @Query("select m.username from Member  m")
    List<String> findUsernameList();


    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) " +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

//    List<Member> findByUsername(String name); //컬렉션
//    Member findByUsername(String name); //단건
//    Optional<Member> findByUsername(String name); //단건 Optional

    @Query(value = "select m from Member m left join m.team t", countQuery = "select count(m) from Member  m")
    Page<Member> findByAge(int age, Pageable pageable);
}
