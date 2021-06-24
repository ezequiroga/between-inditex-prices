package com.inditex.prices.controllers;

import com.inditex.prices.dtos.PriceDTO;
import com.inditex.prices.exceptions.DatePriceFormatException;
import com.inditex.prices.exceptions.PriceNotFoudException;
import com.inditex.prices.services.PriceService;
import com.inditex.prices.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author Eze Q.
 */
@RestController
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private PriceService priceService;

    @Operation(summary = "Obtener tarifa aplicable para un momento, "
            + "marca y producto determinado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La tarifa que debe aplicarse.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceDTO.class))})
    })
    @GetMapping(value = "/")
    public ResponseEntity<Mono<PriceDTO>> getPrices(
            @Parameter(description = "Momento en que debe aplicarse la tarifa (YYYY-MM-DD-HH24.MI.SS). Ej: 1999-12-31-23.59.59")
            @RequestParam Optional<String> date,
            @Parameter(description = "Id del producto")
            @RequestParam int productId,
            @Parameter(description = "Id de la marca del producto")
            @RequestParam int brandId) throws DatePriceFormatException, PriceNotFoudException {

        Optional<PriceDTO> price = priceService.findPrice(date, productId, brandId);

        return ResponseEntity.ok(
            Mono.just(price.orElseThrow(() -> new PriceNotFoudException("No hay datos para los parametros enviados")))
        );
    }
}
