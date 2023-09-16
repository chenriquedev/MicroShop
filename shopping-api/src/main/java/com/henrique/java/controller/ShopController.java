package com.henrique.java.controller;


import com.henrique.java.service.ShopService;
import jakarta.validation.Valid;
import org.henrique.java.backend.DTO.ShopDTO;
import org.henrique.java.backend.DTO.ShopReportDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shop-api")
public class ShopController {

    private final ShopService service;


    public ShopController(ShopService service) {
        this.service = service;
    }

    @GetMapping("/shopping")
    public ResponseEntity<List<ShopDTO>> getShops(){
        List<ShopDTO> produtos = service.getAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public ResponseEntity<List<ShopDTO>> getShopsByUSer(@PathVariable("userIdentifier") String userIdentifier){
        List<ShopDTO> produtos = service.getByUser(userIdentifier);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/shopping/shopByDate")
    public ResponseEntity<List<ShopDTO>> getShopsByDate(@RequestBody ShopDTO dto){
        List<ShopDTO> produtos = service.getByDate(dto);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/shopping/{id}")
    public ResponseEntity<ShopDTO> findById(@PathVariable("id") Long id){
        return service.findByID(id);
    }

    @PostMapping("/shopping")
    public ResponseEntity<ShopDTO> newShop(
            @RequestHeader(name = "key", required = true) String key,
            @Valid @RequestBody ShopDTO dto){

        return service.saveShop(dto, key);
    }

    @GetMapping("/shopping/search")
    public ResponseEntity<List<ShopDTO>> getShopByFilter(
            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
            @RequestParam(name = "valorMinimo", required = false) Float valorMinimo
            ){
        return ResponseEntity.ok(service.getShopByFilter(dataInicio, dataFim, valorMinimo));
    }

    @GetMapping("/shopping/report")
    public ResponseEntity<ShopReportDTO> getReportByDate(
            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim
    ){
        return service.getReportByDate(dataInicio, dataFim);
    }

}
