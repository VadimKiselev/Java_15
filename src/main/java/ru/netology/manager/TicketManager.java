package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] searchBy(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from) &&
                    matches2(ticket, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
                Arrays.sort(result);
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String search) {
        if (ticket.getFrom().contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matches2(Ticket ticket, String search2) {
        if (ticket.getTo().contains(search2)) {
            return true;
        } else {
            return false;
        }
    }
}
