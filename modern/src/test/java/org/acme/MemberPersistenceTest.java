package org.acme;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entity.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class MemberPersistenceTest {

    @Inject
    EntityManager entityManager;

    @Test
    @TestTransaction
    public void testPersistAndRetrieveMember() {
        // Create and persist a new Member
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhoneNumber("1234567890");
        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        // Retrieve the member by ID
        Member retrievedMember = entityManager.find(Member.class, member.getId());

        // Verify the member was persisted and retrieved correctly
        assertNotNull(retrievedMember);
        assertEquals("John Doe", retrievedMember.getName());
        assertEquals("john.doe@example.com", retrievedMember.getEmail());
        assertEquals("1234567890", retrievedMember.getPhoneNumber());
    }
}
