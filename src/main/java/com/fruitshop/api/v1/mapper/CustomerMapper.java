package com.fruitshop.api.v1.mapper;

import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;


@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE =Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "firstname", source = "firstName")
    @Mapping(target = "lastname", source = "lastName")
    @Mapping(target = "customer_url", source = "id", qualifiedByName = "idToUrl")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "customer_url", target = "id", qualifiedByName = "urlToId")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    @Named("idToUrl")
    default String idToUrl(Long id) {
        return "/api/v1/customers/" + id;
    }

    @Named("urlToId")
    default Long urlToId(String url) {
        if (url == null)
            return null;
        String [] tok = url.split("/");

        return Long.valueOf(tok[tok.length-1]);
    }
}
