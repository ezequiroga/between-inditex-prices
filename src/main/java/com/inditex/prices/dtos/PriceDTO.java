package com.inditex.prices.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * Modela la información que retorna el endpoint de precios.
 *
 * @author Eze Q.
 */
public class PriceDTO {

    @Schema(description = "Id del producto")
    private int productId;
    @Schema(description = "Id de la marca del producto")
    private int brandId;
    @Schema(description = "Id de la tarifa que debe aplicarse")
    private int priceList;
    @Schema(description = "Fecha de entrada en vigencia de la tarifa (YYYY-MM-DD-HH24.MI.SS)")
    private String startDate;
    @Schema(description = "Fecha de fin de vigencia de la tarifa (YYYY-MM-DD-HH24.MI.SS)")
    private String endDate;
    @Schema(description = "Valor de la tarifa")
    private BigDecimal price;

    public PriceDTO() {
    }

    public PriceDTO(int productId, int brandId, int id, String startDate, String endDate, BigDecimal price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getPriceList() {
        return priceList;
    }

    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
