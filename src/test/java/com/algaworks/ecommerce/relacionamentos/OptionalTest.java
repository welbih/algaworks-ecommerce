package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class OptionalTest extends EntityManagerTest {

    public void verificarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);


    }
}
