/**
 * @author winic
 */
package com.wbarcellosn.service;

import com.wbarcellosn.dao.IProdutoDAO;
import com.wbarcellosn.domain.Produto;
import com.wbarcellosn.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, Long> implements IProdutoService {

    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }
}
