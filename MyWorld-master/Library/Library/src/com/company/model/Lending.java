package com.company.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "LENDING_TABLE")
public class Lending {

    public enum Status { ONDATE, OUTOFDATE, RETURNED }
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID lendingUuid;
    @Transient
    private User user;
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;
    @Transient
    private Ejemplar ejemplar;
    @Column(columnDefinition = "BINARY(16)")
    private UUID ejemplarId;
    private LocalDate lendingDate;
    private LocalDate returnLimitDate;
    private LocalDate returnRealDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Lending() {
        this.lendingUuid = UUID.randomUUID();
        this.lendingDate = LocalDate.now();
        this.returnLimitDate = this.lendingDate.plusDays(7);
    }

    public Lending(UUID userId, UUID ejemplarId) {
        this.lendingUuid = UUID.randomUUID();
        this.userId = userId;
        this.ejemplarId = ejemplarId;
        this.lendingDate = LocalDate.now();
        this.returnLimitDate = this.lendingDate.plusDays(7);
        this.status = status.ONDATE;
    }

    public boolean devolution() {
        this.returnRealDate = LocalDate.now();
        this.status = status.RETURNED;
        return true;
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

    public void setLendingUuid(UUID lendingUuid) {
        this.lendingUuid = lendingUuid;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public void setReturnLimitDate(LocalDate returnLimitDate) {
        this.returnLimitDate = returnLimitDate;
    }

    public void setReturnRealDate(LocalDate returnRealDate) {
        this.returnRealDate = returnRealDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getUserID() {
        return this.getUser().getIdNumber();
    }


    public UUID getEjemplarID() {
        return this.getEjemplar().getSku();
    }

}
