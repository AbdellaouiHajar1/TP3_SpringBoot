package com.example.demo.Repositories;

import com.example.demo.Entités.Utilisateur;
import com.example.demo.Entités.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurImageRepository extends JpaRepository<UtilisateurImage,Long> {

    @Query("SELECT i FROM UtilisateurImage i WHERE i.user.id = :utilisateurId")
    UtilisateurImage SelectImageByUser(@Param("utilisateurId") Long utilisateurId);
}

