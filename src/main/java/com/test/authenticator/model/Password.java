package com.test.authenticator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Table(name = "password")
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@NoArgsConstructor
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User userId;
    private String passwordHash;
}
