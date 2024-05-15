package com.afa.devicer.core.bl.entities;

import java.time.LocalDateTime;

public interface BaseEntity<ID> {

    static final Character ACTIVE = 'A';
    static final Character DELETED = 'D';

    void setId(ID id);
    ID getId();

    /*
    Character getRecStatus();
    void setRecStatus(Character recStatus);

    LocalDateTime getDateAdded();
    void setDateAdded(LocalDateTime dateAdded);

    LocalDateTime getDateModified();
    void setDateModified(LocalDateTime dateModified);
    */
}
