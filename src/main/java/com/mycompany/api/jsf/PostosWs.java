/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.jsf;

import com.mycompany.api.jsf.model.Usuario;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Felipe
 */
@WebService(serviceName = "PostosWs")
public class PostosWs {

    @WebMethod(operationName = "salvarUsuario")
    public void saveUser(@WebParam(name = "nome") String nome, @WebParam(name = "email") String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_access");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
