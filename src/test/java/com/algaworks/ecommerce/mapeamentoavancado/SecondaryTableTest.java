package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class SecondaryTableTest extends EntityManagerTest {

    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joana Dark");
        cliente.setCpf("123");
        cliente.setSexo(SexoCliente.FEMININO);
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao.getSexo());
    }
}
