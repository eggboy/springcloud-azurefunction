package io.jaylee.springcloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "user_master")
@ToString
public class User {

    @Id
    private String userId;
    private String lastName;
    private String firstName;
    private String email;
    private ZonedDateTime createdOn;
}