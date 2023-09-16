package com.henrique.java.converter;


import com.henrique.java.entity.Item;
import com.henrique.java.entity.Shop;
import org.henrique.java.backend.DTO.ItemDTO;
import org.henrique.java.backend.DTO.ShopDTO;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class Converter {

    private final ModelMapper modelMapper = new ModelMapper();

    public Shop convertToShop(ShopDTO dto){
        return modelMapper.map(dto, Shop.class);
    }

    public ShopDTO convertToShopDTO(Shop shop){
        return modelMapper.map(shop, ShopDTO.class);
    }

    public Item convertToItem(ItemDTO dto){
        return modelMapper.map(dto, Item.class);
    }

    public ItemDTO convertToItemDTO(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }
}
