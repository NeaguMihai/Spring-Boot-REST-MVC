package com.fruitshop.api.v1.mapper;

import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE =Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "firstname", source = "FName")
    @Mapping(target = "lastname", source = "LName")
    @Mapping(target = "customer_url", source = "id", qualifiedByName = "idToUrl")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Named("idToUrl")
    default String idToUrl(Long id) {
        return "/api/v1/customers/" + id;
    }
}
