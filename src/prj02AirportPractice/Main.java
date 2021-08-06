package prj02AirportPractice;

import com.skillbox.airport.*;
import org.w3c.dom.ls.LSOutput;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        List<Aircraft> aircrafts = airport.getAllAircrafts();

        System.out.println("Airport info:");
        System.out.println(airport);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        Date next2Hours = calendar.getTime();

        for(Terminal terminal : terminals){
            System.out.println("terminal " + terminal.getName());
            List<Flight> flights = terminal.getFlights();
            for(Flight flight : flights){
                Date dateFlight = flight.getDate();
                if(next2Hours.getTime() >= dateFlight.getTime() && now.getTime() <= dateFlight.getTime())
                    System.out.println("\t" + dateFlight + "/ model aircraft = " + flight.getAircraft());
            }
        }

        System.out.println("\n\n\n out with stream");
        for(Terminal terminal : terminals){
            System.out.println("Terminal " + terminal.getName());
            List<Flight> flights = terminal.getFlights();
            flights.stream()
                    .filter(flight -> flight.getDate().getTime() >= now.getTime())
                    .filter(flight -> flight.getDate().getTime() <= next2Hours.getTime())
                    .forEach(flight -> System.out.println("\t" + flight.getDate() + "/model aircraft = " + flight.getAircraft()));
        }


    }
}

