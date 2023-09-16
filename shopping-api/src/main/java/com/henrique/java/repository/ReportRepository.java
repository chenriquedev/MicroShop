package com.henrique.java.repository;

import com.henrique.java.entity.Shop;
import org.henrique.java.backend.DTO.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

    ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);
}
