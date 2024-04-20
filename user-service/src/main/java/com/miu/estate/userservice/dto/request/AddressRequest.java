package com.miu.estate.userservice.dto.request;

import com.miu.estate.userservice.model.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AddressRequest {
    @NotNull(message = "Street cannot empty")
    private String street;
    @NotNull(message = "City cannot empty")
    private String city;
    @NotNull(message = "State cannot empty")
    private String state;
    @NotNull(message = "Zip cannot empty")
    private String zip;

    public Address getAddressEntity() {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        return address;
    }
}
