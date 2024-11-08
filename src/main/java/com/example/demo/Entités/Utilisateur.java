package com.example.demo.Entités;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UtilisateurImage img;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
// déclaration d'une table d'association
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Rôle role;

    public UtilisateurImage getImg() {
        return img;
    }

    public void setImg(UtilisateurImage img) {
        this.img = img;
    }

    public Rôle getRole() {
        return role;
    }

    public void setRole(Rôle role) {
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
