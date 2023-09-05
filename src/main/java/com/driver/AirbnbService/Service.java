package com.driver.AirbnbService;

import com.driver.AirbnbRepository.Repository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.List;

public class Service {

    Repository repository = new Repository();

    public  String addHotel( Hotel hotel) {
        return repository.addHotel(hotel);

    }

    public int addUser(User user) {
        return repository.addUser(user);
    }

    public String getHotelWithMaxFacility() {
        return repository.getHotelWithMaxFacility();
    }

    public int bookARoom(Booking booking) {
        return repository.bookARoom(booking);
    }

    public int getAadhar(int aadharCard) {
        return repository.getAadhar(aadharCard);
    }


    public Hotel putFacility(List<Facility> newFacilities, String hotelName) {
        return repository.putFacility(newFacilities, hotelName);
    }
}
