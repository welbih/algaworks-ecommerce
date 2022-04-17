package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void buscarPedidosComProdutoEspecifico() {
        String jqpl = "select p from Pedido p join p.itens i join i.produto pro on pro.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jqpl, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.get(0).getItens().get(0).getProduto().getId().equals(1));
    }

    @Test
    public void usarPathExpressions() {
        String jpql = "select p.cliente.nome from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
