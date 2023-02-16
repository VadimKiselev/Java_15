package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 95);
    private Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 95);
    private Ticket ticket3 = new Ticket(3, 2287, "VKO", "KZN", 95);
    private Ticket ticket4 = new Ticket(4, 2269, "VKO", "KZN", 95);

    @BeforeEach
    public void addTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
    }

    @Test
    public void shouldSearchByTickets() {
        Ticket[] expected = {ticket2, ticket4, ticket3};
        Ticket[] actual = manager.searchBy("VKO", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByAirportOfArrival() {
        Ticket[] expected = {ticket1, ticket2, ticket4, ticket3};
        Ticket[] actual = manager.searchBy("", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByDepartureAirport() {
        Ticket[] expected = {ticket1};
        Ticket[] actual = manager.searchBy("SVO", "");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void matchesOnlyOneSearchField() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("VKO", "ATH");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ticketDoesNotMatchSearchQuery() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("DME", "ATH");

        assertArrayEquals(expected, actual);
    }
}