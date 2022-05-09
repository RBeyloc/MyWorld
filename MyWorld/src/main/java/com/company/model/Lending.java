package com.company.model;

import java.time.LocalDate;
import java.util.UUID;

public class Lending {

    public enum Status { ONDATE, OUTOFDATE, RETURNED }

    private final UUID lendingUuid;
    private final User user;
    private final Ejemplar ejemplar;
    private final LocalDate lendingDate;
    private final LocalDate returnLimitDate;
    private LocalDate returnRealDate;
    private Status status;

    public Lending(User user, Ejemplar ejemplar) {
        this.lendingUuid = UUID.randomUUID();
        this.user = user;
        this.ejemplar = ejemplar;
        this.lendingDate = LocalDate.now();
        this.returnLimitDate = this.lendingDate.plusDays(7);
        this.status = status.ONDATE;
    }

    public void devolution() {
        this.returnRealDate = LocalDate.now();
        this.status = status.RETURNED;
    }

    public void setOutOfDate() {
        if(this.returnLimitDate.isBefore(LocalDate.now())) {
            this.status = status.OUTOFDATE;
        }
    }

    @Override
    public String toString() {
        return  "\nLending:" +
                "\n  user=" + user +
                "\n  ejemplar=" + ejemplar +
                "\n  lendingDate=" + lendingDate +
                "\n  returnLimitDate=" + returnLimitDate +
                "\n  returnRealDate=" + returnRealDate +
                "\n  status=" + status +
                "\n";
    }

    public User getUser() {
        return user;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public LocalDate getReturnLimitDate() {
        return returnLimitDate;
    }

    public LocalDate getReturnRealDate() {
        return returnRealDate;
    }

    public Status getStatus() {
        return status;
    }

    public UUID getLendingUuid() {
        return lendingUuid;
    }
}
