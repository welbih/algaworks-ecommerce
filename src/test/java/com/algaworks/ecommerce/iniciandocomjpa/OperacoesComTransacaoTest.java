package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados() {
        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2 Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle", produtoVerificado.getNome());
    }

    @Test
    public void mostrarDiferencaPersistMerge() {
        Produto produtoPersist = new Produto();

//        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido.");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist); //Apenas persiste objeto nunca persistido antes
        produtoPersist.setNome("Processador bala");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoSalvo = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoSalvo);


        Produto produtoMerge = new Produto();

        produtoMerge.setId(5);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria");
        produtoMerge.setPreco(new BigDecimal(3000));

        entityManager.getTransaction().begin();
        entityManager.merge(produtoMerge); //Pode salvar e atualizar
        produtoMerge.setNome("Notebook Dell 2"); // Não irá atualizar. A cópia atachada está no retorno do merge.
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoSalvoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoSalvoMerge);
    }

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone rode Videmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoSalvo = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoSalvo);
    }

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2 Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle Paperwhite 2 Geração", produtoVerificado.getNome());
    }

    @Test
    public void atualizarObjeto() {
        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle.");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
        Assert.assertEquals("Kindle Paperwhite", produtoVerificado.getNome());
    }

    @Test
    public void removeObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        // entityManager.clear(); Não é necessário na asserção para operação de remoção.

        Produto produtoVerificado = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificado);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();

//        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        //entityManager.persist(produto); aguardará ser aberta uma transação.

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoSalvo = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoSalvo);
    }

    @Test
    public void abrirEFecharATransacao() {
        Produto produto = new Produto(); // Somente para o método compilar

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
