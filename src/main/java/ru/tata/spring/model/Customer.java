package ru.tata.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
@SuppressWarnings("UnusedDeclaration")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("customer_id")
    private long id;
    @NotNull
    @Length(min = 4, max = 25)
    @JsonProperty("first_name")
    private String firstName;
    @NotNull
    @Length(min = 4, max = 25)
    @JsonProperty("last_name")
    private String lastName;
    @Min(0)
    private int age;

    public Customer() {
    }

    public Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("id", id)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("age", age)
            .toString();
    }
}

