package com.example.demo.Service;

import com.example.demo.Entités.Rôle;
import com.example.demo.Entités.Utilisateur;
import com.example.demo.Entités.UtilisateurImage;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UtilisateurImageRepository;
import com.example.demo.Repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class service {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired

    UtilisateurImageRepository utilisateurImageRepository;
    @Autowired

    RoleRepository roleRepository;

    public Utilisateur ajoutuser_role(Utilisateur utilisateur, Long roleId) {
        Rôle role = roleRepository.findById(roleId).orElse(null);
        if (role != null) {
            utilisateur.setRole(role);
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    public UtilisateurImage ajoutimage_user(Long utilisateurId, UtilisateurImage image) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            image.setUser(utilisateur);
            utilisateur.setImg(image);
            utilisateurRepository.save(utilisateur);
            return image;
        }
        return null;
    }

    public void SelectUserbyRole(Rôle role) {
        utilisateurRepository.SelectUsersByRole(role);
    }

    public List<Utilisateur> AllUsers() {
        return utilisateurRepository.findAll();
    }

    public void createUser(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }


    public Utilisateur getUserById(Long id) {
        Optional<Utilisateur> T = utilisateurRepository.findById(id);
        if (T.isPresent()) {
            return T.get();
        } else {
            return null;
        }
    }

    public Rôle ajoutrole_userExistant(Long utilisateurId, Long RoleId) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
        Optional<Rôle> role = roleRepository.findById(RoleId);
        if (utilisateurOpt.isPresent() && role.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            Rôle r = role.get();
            utilisateur.setRole(r);
            utilisateurRepository.save(utilisateur);
            return r;
        }
        return null;

    }

    public void deleteUser(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public void deleteRole(Long roleId) {
        Rôle role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        // Détache les utilisateurs associés
        role.getUsers().forEach(user -> user.setRole(null));
        utilisateurRepository.saveAll(role.getUsers());

        // Supprime ensuite le rôle
        roleRepository.delete(role);
    }


    public void deleteimageByUser(Long utilisateurId, Long ImageId) {
        UtilisateurImage utilisateurImage = utilisateurImageRepository.SelectImageByUser(utilisateurId);
        if (utilisateurImage != null && utilisateurImage.getId().equals(ImageId)) {
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
            Utilisateur utilisateur = utilisateurOpt.get();
            utilisateur.setImg(null);
            utilisateurImageRepository.deleteById(ImageId);
        }
    }

    // Méthode non demandée, ajoutée pour tester l'ajout  pour un utilisateur existant et la suppression d'un rôle
    public Rôle ajoutrole(Rôle role) {
        roleRepository.save(role);
        return role;
    }
}


