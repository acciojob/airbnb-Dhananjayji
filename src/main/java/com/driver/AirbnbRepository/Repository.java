package com.driver.AirbnbRepository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Repository {

    HashMap<String, Booking> bookingDb= new HashMap<>();

    HashMap<String, Hotel> hotelDb= new HashMap<>();

    HashMap<Integer, User> userDb= new HashMap<>();
    // adharcard, int countOfBooking
    HashMap<Integer, Integer> countOfBooking= new HashMap<>();

    public String addHotel(Hotel hotel) {
        if(hotel==null&& hotel.getHotelName()==null){
            return "FAILURE";
        }
        if(hotelDb.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hotelDb.put(hotel.getHotelName(),hotel);
        return hotel.getHotelName();
    }

    public int addUser(User user) {
        userDb.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMaxFacility(){
        int facilities=0;

        String hotelName="";
        for(Hotel hotel: hotelDb.values()){
            if(hotel.getFacilities().size()>facilities){
                facilities= hotel.getFacilities().size();
                hotelName= hotel.getHotelName();
            }
            else if (hotel.getFacilities().size()==facilities){
                if(hotel.getHotelName().compareTo(hotelName)<0){
                    hotelName= hotel.getHotelName();
                }
            }
        }

        return hotelName;
    }

    public int bookARoom(Booking booking) {
        String key= UUID.randomUUID().toString();
        booking.setBookingId(key);
        String hotelName=booking.getHotelName();
        Hotel hotel= hotelDb.get(hotelName);

        int availablerooms= hotel.getAvailableRooms();
        if(availablerooms<booking.getNoOfRooms()){
            return -1;
        }
        int amountTobePaid= hotel.getPricePerNight()*booking.getNoOfRooms();
        booking.setAmountToBePaid(amountTobePaid);

        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());

        bookingDb.put(key,booking);
        hotelDb.put(hotelName,hotel);

        int adharCard= booking.getBookingAadharCard();
        Integer currentBooking= countOfBooking.get(adharCard);
        countOfBooking.put(adharCard, Objects.nonNull(currentBooking)?1+currentBooking:1);
        return amountTobePaid;

    }
    public int getAadhar(int aadhar) {
        return countOfBooking.get(aadhar);
    }


    public Hotel putFacility(List<Facility> newFacilities, String hotelName) {

        List<Facility> oldFacilities= hotelDb.get(hotelName).getFacilities();
        for(Facility facility:newFacilities){
            if(oldFacilities.contains(facility)){
                continue;
            }else{
                oldFacilities.add(facility);
            }
        }

        Hotel hotel= hotelDb.get(hotelName);
        hotel.setFacilities(oldFacilities);
        hotelDb.put(hotelName,hotel);
        return hotel;
    }
}
