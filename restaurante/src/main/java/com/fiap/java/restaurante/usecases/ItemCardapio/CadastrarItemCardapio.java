package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.insfrastucture.entity.ItemCardapioEntity;
import com.fiap.java.restaurante.insfrastucture.entity.RestauranteEntity;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarItemCardapio {
    private final ItemCardapioRepository itemCardapioRepository;
    private final RestauranteRepository restauranteRepository;
    private final RespostaMapper respostaMapper;

    public ItemCardapio salvar(ItemCardapioDTO itemCardapioDTO) {
        Restaurante restaurante = validarRestaurante(itemCardapioDTO.getRestauranteId());

        ItemCardapio itemCardapio = new ItemCardapio(
                restaurante.getId(),
                itemCardapioDTO.getNome(),
                itemCardapioDTO.getDescricao(),
                itemCardapioDTO.getPreco(),
                itemCardapioDTO.getDisponivel(),
                itemCardapioDTO.getFoto()
        );
        return respostaMapper.itemCardapioEntitytoItemCardapio(
                itemCardapioRepository.save(respostaMapper.itemCardapioToItemCardapioEntity(itemCardapio)));
    }

    private Restaurante validarRestaurante(Long idRestaurante) {
        return respostaMapper.restauranteEntityToRestaurante(
                restauranteRepository.findById(idRestaurante)
                        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado - ID: " + idRestaurante))
        );
    }

}
