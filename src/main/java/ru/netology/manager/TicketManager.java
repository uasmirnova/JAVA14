package ru.netology.manager;

import ru.netology.repository.TicketRepository;
import ru.netology.tickets.Ticket;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {

    protected TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.add(ticket);
    }

    public Ticket[] findAll() {
        return repo.findAll();
    }

    public Ticket[] removeById(int id) {
        repo.removeById(id);
        return new Ticket[0];
    }

    public Ticket[] searchBy(String departureAirport, String arrivalAirport, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (matches(ticket, departureAirport, arrivalAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public boolean matches(Ticket ticket, String departureAirport, String arrivalAirport) {
        if (ticket.getDepartureAirport().equals(departureAirport)) {
            if (ticket.getArrivalAirport().equals(arrivalAirport)) {
                return true;
            }
        }
        return false;
    }
}

