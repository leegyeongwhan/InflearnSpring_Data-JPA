package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;

    protected Member() {

    }

    public Member(String name) {
        this.userName = name;
    }


}
