package ru.intro.notes.models;

import javax.validation.constraints.Size;

/**
 * Вспомогательный класс инкапсулирует данные для поиска заметок
 */
public class SearchRequest {

    @Size(max = Short.MAX_VALUE)
    private String txt;

    public String getTxt() {
        return txt;
    }

    /**
     * Задает строку для поиска
     */
    public void setTxt(String txt) {
        this.txt = txt;
    }

    public SearchRequest(String txt) {
        this.txt = txt;
    }

    public SearchRequest() {
    }
}
