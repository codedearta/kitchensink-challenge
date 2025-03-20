package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Member;

import java.util.List;

@ApplicationScoped
public class MemberRepository implements PanacheRepository<Member> {

    public Member findById(Long id) {
        return find("id", id).firstResult();
    }

    public Member findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public List<Member> findAllOrderedByName() {
        return list("ORDER BY name ASC");
    }
}
