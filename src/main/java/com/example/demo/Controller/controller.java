package com.example.demo.Controller;

import com.example.demo.Entités.Rôle;
import com.example.demo.Entités.Utilisateur;
import com.example.demo.Entités.UtilisateurImage;
import com.example.demo.Service.service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class controller {
    @Autowired
    service serv;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return serv.AllUsers();
    }

    @PostMapping
    public void createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        serv.createUser(utilisateur);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = serv.getUserById(id);
        if (utilisateur != null) {
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{utilisateurId}/image")
    public ResponseEntity<UtilisateurImage> addImage(@Valid @PathVariable Long utilisateurId, @RequestBody UtilisateurImage image) {
        UtilisateurImage addedImage = serv.ajoutimage_user(utilisateurId, image);
        return addedImage != null ? new ResponseEntity<>(addedImage, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        serv.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{utilisateurId}/role/{roleId}")
    public ResponseEntity<Rôle> addRole(@PathVariable Long utilisateurId, @PathVariable Long roleId) {
        Rôle r = serv.ajoutrole_userExistant(utilisateurId, roleId);
        return r != null ? new ResponseEntity<>(r, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Long id) {
        serv.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public ResponseEntity<Void> deleteimage(@PathVariable Long utilisateurId, @PathVariable Long imageId) {
        serv.deleteimageByUser(utilisateurId, imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  // Méthode non demandée, ajoutée pour tester l'ajout  pour un utilisateur existant et la suppression d'un rôle
    @PostMapping("/role")
    public ResponseEntity<Rôle> ajoutrole(@Valid @RequestBody Rôle role) {
        Rôle R = serv.ajoutrole(role);
        return R != null ? new ResponseEntity<>(R, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

