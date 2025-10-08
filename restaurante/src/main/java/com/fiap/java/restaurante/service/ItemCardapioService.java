package com.fiap.java.restaurante.service;

import com.fiap.java.restaurante.DTO.ItemCardapioDTO;
import com.fiap.java.restaurante.DTO.ItemCardapioEditaDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.models.ItemCardapio;
import com.fiap.java.restaurante.models.Restaurante;
import com.fiap.java.restaurante.repository.ItemCardapioRepository;
import com.fiap.java.restaurante.service.mapper.RespostaMapper;

public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;
    private final RespostaMapper respostaMapper = new RespostaMapper();

    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

   public ItemCardapioDTO salvar(ItemCardapioDTO itemCardapioDTO, Restaurante restaurante) {
       ItemCardapio itemCardapio = new ItemCardapio();
       itemCardapio.setNome(itemCardapioDTO.getNome());
       itemCardapio.setDescricao(itemCardapioDTO.getDescricao());
       itemCardapio.setPreco(itemCardapioDTO.getPreco());
       itemCardapio.setDisponivel(itemCardapioDTO.getDisponivel());
       itemCardapio.setFoto(itemCardapioDTO.getFoto());
       itemCardapio.setRestaurante(restaurante);

       itemCardapioRepository.save(itemCardapio);
       return respostaMapper.mapItemCardapioToItemCardapioDTO(itemCardapio);
   }

    public void excluir(Long id) {
        ItemCardapio itemCardapio = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item do cardapio não encontrado"));
        itemCardapioRepository.delete(itemCardapio);
    }

    public ItemCardapioDTO buscarPorId(Long id) {
        ItemCardapio itemCardapio = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardapio não encontrado - ID: " + id));
        return respostaMapper.mapItemCardapioToItemCardapioDTO(itemCardapio);
    }

    public RespostaDTO editarDados(Long id, ItemCardapioEditaDTO itemCardapioEditaDTO) {
        ItemCardapio itemCardapio = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item do cardápio não encontrado - ID: " + id));

        if (itemCardapioEditaDTO.getNome() != null) {
            itemCardapio.setNome(itemCardapioEditaDTO.getNome());
        }
        if (itemCardapioEditaDTO.getDescricao() != null) {
            itemCardapio.setDescricao(itemCardapioEditaDTO.getDescricao());
        }
        if (itemCardapioEditaDTO.getPreco() != null) {
            itemCardapio.setPreco(itemCardapioEditaDTO.getPreco());
        }
        if (itemCardapioEditaDTO.getDisponivel() != null) {
            itemCardapio.setDisponivel(itemCardapioEditaDTO.getDisponivel());
        }

        itemCardapioRepository.save(itemCardapio);
        return respostaMapper.mapItemCardapioAtualizadoToRespostaDTO(itemCardapio.getId());
    }

}
