package com.sharepage.sharepage.repositories;

import com.sharepage.sharepage.entities.LinkPage;
import com.sharepage.sharepage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LinkPageRepository extends JpaRepository<LinkPage, Long> {
    List<LinkPage> findByOwner(User user);
}
