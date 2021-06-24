package com.inditex.prices.services;

import com.inditex.prices.dtos.PriceDTO;
import com.inditex.prices.exceptions.DatePriceFormatException;
import com.inditex.prices.repositories.PriceRepository;
import com.inditex.prices.utils.Utils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eze Q.
 */
@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<PriceDTO> findPrice(Optional<String> dateParam, int productId, int brandId) throws DatePriceFormatException {
        Long date = getDateDB(dateParam);
        return priceRepository
                .findPrice(date, productId, brandId)
                .stream()
                .findFirst()
                .map(p -> new PriceDTO(
                p.getProductId(),
                p.getBrand().getId(),
                p.getId(),
                Utils.dateDBToDateApi(p.getStartDate()),
                Utils.dateDBToDateApi(p.getEndDate()),
                p.getPrice())
                );
    }

    private Long getDateDB(Optional<String> date) throws DatePriceFormatException {
        Long queryDate;

        /**
         * Se verifica si la fecha que llega por parámetro es válida.
         */
        Optional<Boolean> valid = date.map(Utils::validDate);

        /**
         * Si valid es vacio el parámetro no llegó en el request, entonces se
         * utiliza el datetime del momento de la petición.
         */
        if (valid.isEmpty()) {
            queryDate = Utils.generateInstantDateDB();
        } else {
            /*Si la fecha no es valida, se retorna HTTP 422*/
            if (!valid.get()) {
                throw new DatePriceFormatException("Error al procesar el parametro date."
                        + " Formato de fecha incorrecto.");
            }
            queryDate = date.map(Utils::dateApiToDateDB).get();
        }
        return queryDate;
    }

}
