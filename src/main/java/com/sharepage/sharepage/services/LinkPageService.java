package com.sharepage.sharepage.services;

import com.sharepage.sharepage.dto.CreatePageRequest;
import com.sharepage.sharepage.entities.LinkItem;
import com.sharepage.sharepage.entities.LinkPage;
import com.sharepage.sharepage.entities.User;
import com.sharepage.sharepage.repositories.LinkPageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkPageService {

    private final LinkPageRepository repo;

    public LinkPageService(LinkPageRepository repo) {
        this.repo = repo;
    }

    public LinkPage create(CreatePageRequest req, User owner) {

        LinkPage page = new LinkPage();
        page.setTitle(req.title());
        page.setOwner(owner);

        List<LinkItem> items = req.links().stream()
                .map(dto -> {
                    LinkItem item = new LinkItem();
                    item.setLabel(dto.getLabel());
                    item.setUrl(dto.getUrl());
                    return item;
                }).toList();

        page.setLinks(items);

        return repo.save(page);

    }

    public List<LinkPage> list(User owner) {
        return repo.findByOwner(owner);
    }

    public LinkPage get(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
