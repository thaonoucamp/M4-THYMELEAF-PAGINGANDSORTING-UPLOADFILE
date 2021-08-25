package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "Ten toi thieu 2 ky tu va toi da 20")
    private String name;

    @Pattern(regexp = "^[a-z0-9]+@[a-z]+\\.[a-z]+$")
    private String email;

    @ManyToOne
    private ClassRoom classRoom;
    private Date birthday;
    private String avatar;

    public Student() {
    }

    public Student(Long id, String name, String email, ClassRoom classRoom, Date birthday, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.classRoom = classRoom;
        this.birthday = birthday;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
