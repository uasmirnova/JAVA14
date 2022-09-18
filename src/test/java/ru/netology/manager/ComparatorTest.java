package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.TicketRepository;
import ru.netology.tickets.Ticket;
import ru.netology.tickets.TicketComparator;

public class ComparatorTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);
    TicketComparator comparator = new TicketComparator();

    Ticket ticket1 = new Ticket(1, 9500, "DME", "LED", 2);
    Ticket ticket2 = new Ticket(2, 5600, "DME", "LED", 3);
    Ticket ticket3 = new Ticket(3, 18000, "VKO", "AER", 1);
    Ticket ticket4 = new Ticket(4, 25000, "VKO", "AER", 10);
    Ticket ticket5 = new Ticket(5, 1500, "VKO", "AER", 2);


    @BeforeEach
    public void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    public void ShouldFindAll() {

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldSearchByAirportAndTime2() {

        Ticket[] expected = {ticket3, ticket5, ticket4};
        Ticket[] actual = manager.searchBy("VKO", "AER", comparator);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void ShouldSearchByName0() {

        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchBy("AER", "VKO", comparator);

        Assertions.assertArrayEquals(expected, actual);

    }

}


