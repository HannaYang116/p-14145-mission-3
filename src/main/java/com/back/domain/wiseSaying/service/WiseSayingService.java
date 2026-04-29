package com.back.domain.wiseSaying.service;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId,content,author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public int findIndexById(int id) {
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public WiseSaying findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayings.get(index);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    public boolean delete(int id) {
        int deleteIndex = findIndexById(id);

        if (deleteIndex == -1) return false;

        wiseSayings.remove(deleteIndex);

        return true;
    }
}
