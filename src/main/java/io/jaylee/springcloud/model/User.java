package io.jaylee.springcloud.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
