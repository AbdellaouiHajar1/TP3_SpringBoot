package com.example.demo.Repositories;

import com.example.demo.Entités.Rôle;
import com.example.demo.Entités.Utilisateur;
import com.example.demo.Entités.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Rôle,Long> {
    public Rôle findByNom(String role_name);
}
