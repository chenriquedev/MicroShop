package com.henrique.java.service;


import com.henrique.java.converter.Converter;
import com.henrique.java.entity.Shop;
import com.henrique.java.repository.ReportRepository;
import com.henrique.java.repository.ShopRepository;
import org.henrique.java.backend.DTO.*;
import org.henrique.java.backend.Exception.ProductNotFoundException;
import org.henrique.java.backend.Exception.ShopBadRequestException;
import org.henrique.java.backend.Exception.ShopNotFoundException;
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
    private final ShopRepository shopRepository;

   private final ReportRepository reportRepository;

  private final ProductService productService;
  private final UserService userService;

   private final Converter converter;

    public ShopService(ShopRepository shopRepository, ReportRepository reportRepository, Converter converter,
                       ProductService productService, UserService userService) {
        this.shopRepository = shopRepository;
        this.reportRepository = reportRepository;
        this.converter = converter;
        this.productService = productService;
        this.userService = userService;
    }

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(converter::convertToShopDTO).toList();
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);

        return shops.stream().map(converter::convertToShopDTO).toList();
    }

    public List<ShopDTO> getByDate(ShopDTO dto){
        List<Shop> shops = shopRepository.findAllByDateGreaterThanEqual(dto.getDate());
        return shops.stream().map(converter::convertToShopDTO).toList();
    }

    public ShopDTO findByID(Long productId){
        Optional<Shop> shops = shopRepository.findById(productId);
        return shops.map(converter::convertToShopDTO)
                .orElseThrow(() -> new ShopNotFoundException("Shop Not Found"));
    }

    public ShopDTO saveShop(ShopDTO dto, String key){
        UserDTO userDTO = userService.getUserByCPF(dto.getUserIdentifier(), key);
        validateProducts(dto.getItems());

        dto.setTotal(dto.getItems().stream().map(ItemDTO::getPrice).reduce((float) 0 , Float::sum));
        dto.setDate(new Date());
        Shop shop = converter.convertToShop(dto);
        Shop shopSaved = shopRepository.save(shop);
        return converter.convertToShopDTO(shopSaved);
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

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim){
        ShopReportDTO report = reportRepository.getReportByDate(dataInicio, dataFim);
        if (report.getCount().equals(0)){
            throw new ShopNotFoundException("Not Found for this date");
        }
        return report;
    }
}
