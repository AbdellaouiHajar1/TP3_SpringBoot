package com.example.demo.Repositories;

import com.example.demo.Entités.Rôle;
import com.example.demo.Entités.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Utilisateur findByEmail(String email);
   @Query("SELECT u FROM Utilisateur u WHERE u.role = :role")
   List<Utilisateur> SelectUsersByRole(@Param("role") Rôle role);

}
