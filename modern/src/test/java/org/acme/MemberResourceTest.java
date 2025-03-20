package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.acme.entity.Member;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class MemberResourceTest {

    @Test
    public void testListAllMembers() {
        given()
            .when().get("/members")
            .then()
            .statusCode(200)
            .body(is("[]")); // Assuming no members exist initially
    }

    @Test
    public void testGetMemberByIdNotFound() {
        given()
            .when().get("/members/999")
            .then()
            .statusCode(404)
            .body(containsString("Member with ID 999 not found"));
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
