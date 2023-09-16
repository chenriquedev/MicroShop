package com.henrique.java.service;


import com.henrique.java.converter.Converter;
import com.henrique.java.entity.Shop;
import com.henrique.java.repository.ReportRepository;
import com.henrique.java.repository.ShopRepository;
import org.henrique.java.backend.DTO.*;
import org.henrique.java.backend.Exception.ProductNotFoundException;
import org.henrique.java.backend.Exception.UserErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ShopService {

   @Autowired
    private final ShopRepository repository;

   private final ReportRepository reportRepository;

  private final ProductService productService;
  private final UserService userService;

   private final Converter converter;

    public ShopService(ShopRepository repository, ReportRepository reportRepository, Converter converter,
                       ProductService productService, UserService userService) {
        this.repository = repository;
        this.reportRepository = reportRepository;
        this.converter = converter;
        this.productService = productService;
        this.userService = userService;
    }

    public List<ShopDTO> getAll(){
        List<Shop> shops = repository.findAll();
        return shops.stream().map(converter::convertToShopDTO).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = repository.findAllByUserIdentifier(userIdentifier);

        return shops.stream().map(converter::convertToShopDTO).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO dto){
        List<Shop> shops = repository.findAllByDateGreaterThanEqual(dto.getDate());
        return shops.stream().map(converter::convertToShopDTO).collect(Collectors.toList());
    }

    public ResponseEntity<ShopDTO> findByID(Long productId){
        Optional<Shop> shops = repository.findById(productId);
        return shops.map(shop ->
            ResponseEntity.ok(converter.convertToShopDTO(shop))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<ShopDTO> saveShop(ShopDTO dto, String key){
        System.out.println(dto.getUserIdentifier() + " Chave: " + key + " Items: " + dto.getItems());
        UserDTO userDTO = userService.getUserByCPF(dto.getUserIdentifier(), key);
        validateProducts(dto.getItems());


        dto.setTotal(dto.getItems().stream().map(ItemDTO::getPrice).reduce((float) 0 , Float::sum));
        dto.setDate(new Date());
        Shop shop = converter.convertToShop(dto);
        Shop shopSaved = repository.save(shop);

        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertToShopDTO(shopSaved));
    }

    private boolean validateProducts(List<ItemDTO> items){
        for (ItemDTO item: items){
            ProductDTO productDTO = productService.getProductByIdentifier(
                    item.getProductIdentifier()
            );
            if (productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public List<ShopDTO> getShopByFilter(Date dataInicio, Date dataFim, Float valorMinimo){
        List<Shop> shops = reportRepository.getShopByFilters(dataInicio,dataFim,valorMinimo);
        return shops.stream().map(converter::convertToShopDTO).collect(Collectors.toList());
    }

    public ResponseEntity<ShopReportDTO> getReportByDate(Date dataInicio, Date dataFim){
        return ResponseEntity.ok(reportRepository.getReportByDate(dataInicio, dataFim));
    }
}
