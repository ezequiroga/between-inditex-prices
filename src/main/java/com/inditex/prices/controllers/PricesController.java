package com.inditex.prices.controllers;

import com.inditex.prices.dtos.PriceDTO;
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
import org.springframework.http.HttpStatus;
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
                            schema = @Schema(implementation = PriceDTO.class))}),
        @ApiResponse(responseCode = "422", description = "El formato de la fecha recibida es incorrecto",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "No se encontro una tafica para los parametros recibidos",
                content = @Content)})
    @GetMapping(value = "/")
    public ResponseEntity<Mono<PriceDTO>> getPrices(
            @Parameter(description = "Momento en que debe aplicarse la tarifa (YYYY-MM-DD-HH24.MI.SS). Ej: 1999-12-31-23.59.59")
            @RequestParam Optional<String> date,
            @Parameter(description = "Id del producto")
            @RequestParam int productId,
            @Parameter(description = "Id de la marca del producto")
            @RequestParam int brandId) {

        /**
         * Se verifica si la fecha que llega por parámetro es válida.
         */
        Optional<Boolean> valid = date.map(Utils::validDate);
        Long queryDate;

        /**
         * Si valid es vacio el parámetro no llegó en el request, entonces se
         * utiliza el datetime del momento de la petición.
         */
        if (valid.isEmpty()) {
            queryDate = Utils.generateInstantDateDB();
        } else {
            /*Si la fecha no es valida, se retorna HTTP 422*/
            if (!valid.get()) {
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .build();
            }
            queryDate = date.map(Utils::dateApiToDateDB).get();
        }

        Optional<PriceDTO> price = priceService.findPrice(queryDate, productId, brandId);

        return price.isPresent()
                ? ResponseEntity.ok(Mono.just(price.get()))
                : ResponseEntity.notFound().build();
    }
}
