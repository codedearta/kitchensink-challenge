package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.acme.entity.Member;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class MemberResourceTest {

    @Inject
    EntityManager entityManager;

    @Transactional
    void clearDatabase() {
        entityManager.createQuery("DELETE FROM Member").executeUpdate();
    }

    @Test
    public void testListAllMembers() {
        clearDatabase();

        // Add test users
        Member member1 = new Member();
        member1.setName("Alice");
        member1.setEmail("alice@example.com");
        member1.setPhoneNumber("1111111111");
        entityManager.persist(member1);

        Member member2 = new Member();
        member2.setName("Bob");
        member2.setEmail("bob@example.com");
        member2.setPhoneNumber("2222222222");
        entityManager.persist(member2);

        entityManager.flush();

        // Verify the list of members
        given()
            .when().get("/members")
            .then()
            .statusCode(200)
            .body(containsString("Alice"))
            .body(containsString("Bob"));
    }

    @Test
    public void testGetMemberByIdNotFound() {
        given()
            .when().get("/members/999")
            .then()
            .statusCode(404)
            .body(is(""));
    }

    @Test
    public void testCreateMember() {
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhoneNumber("1234567890");

        given()
            .contentType("application/json")
            .body(member)
            .when().post("/members")
            .then()
            .statusCode(201)
            .header("Location", containsString("/members/"));
    }
}
