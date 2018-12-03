package com.m2i.flexiflex.model;

import com.m2i.flexiflex.properties.UserProperties;
import jdk.nashorn.internal.objects.annotations.Property;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    private String email;
    private String password;
    private Date inscriptionDate;
    private String validationToken;
    private Boolean emailValidation;
    private String uuid;
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, insertable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false, length = 255, updatable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = UserProperties.PASSWORD, nullable = false, updatable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = UserProperties.INSCRIPTION_DATE)
    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    @Column(name = UserProperties.VALIDATION_TOKEN)
    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    @Column(name = UserProperties.EMAIL_VALIDE)
    public Boolean getEmailValidation() {
        return emailValidation;
    }

    public void setEmailValidation(Boolean isValidated) {
        this.emailValidation = isValidated;
    }

    @Column(name = UserProperties.UUID)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(inscriptionDate, that.inscriptionDate) &&
                Objects.equals(validationToken, that.validationToken) &&
                Objects.equals(emailValidation, that.emailValidation) &&
                Objects.equals(uuid, that.uuid);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", inscriptionDate=" + inscriptionDate +
                ", validationToken='" + validationToken + '\'' +
                ", emailValidation=" + emailValidation +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, inscriptionDate, validationToken, emailValidation, uuid);
    }
}