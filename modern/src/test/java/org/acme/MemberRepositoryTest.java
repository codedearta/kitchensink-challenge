package org.acme;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.entity.Member;
import org.acme.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class MemberRepositoryTest {

    @Inject
    MemberRepository memberRepository;

    @Test
    @TestTransaction
    public void testFindById() {
        // Create and persist a new Member
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhoneNumber("1234567890");
        memberRepository.persist(member);

        // Retrieve the member by ID
        Member retrievedMember = memberRepository.findById(member.getId());

        // Verify the member was retrieved correctly
        assertNotNull(retrievedMember);
        assertEquals("John Doe", retrievedMember.getName());
    }

    @Test
    @TestTransaction
    public void testFindByEmail() {
        // Create and persist a new Member
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("0987654321");
        memberRepository.persist(member);

        // Retrieve the member by email
        Member retrievedMember = memberRepository.findByEmail("jane.doe@example.com");

        // Verify the member was retrieved correctly
        assertNotNull(retrievedMember);
        assertEquals("Jane Doe", retrievedMember.getName());
    }

    @Test
    @TestTransaction
    public void testFindAllOrderedByName() {
        // Create and persist multiple Members
        Member member1 = new Member();
        member1.setName("Alice");
        member1.setEmail("alice@example.com");
        member1.setPhoneNumber("1111111111");
        memberRepository.persist(member1);

        Member member2 = new Member();
        member2.setName("Bob");
        member2.setEmail("bob@example.com");
        member2.setPhoneNumber("2222222222");
        memberRepository.persist(member2);

        // Retrieve all members ordered by name
        List<Member> members = memberRepository.findAllOrderedByName();

        // Verify the members were retrieved in the correct order
        assertEquals(2, members.size());
        assertEquals("Alice", members.get(0).getName());
        assertEquals("Bob", members.get(1).getName());
    }
}
