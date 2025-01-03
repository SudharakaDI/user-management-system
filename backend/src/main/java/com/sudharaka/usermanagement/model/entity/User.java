package com.sudharaka.usermanagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLRestriction("is_deleted = 0")
@SQLDelete(sql = "UPDATE user SET is_deleted=1 WHERE id = ?")
public class User extends AbstractEntity{

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;

}