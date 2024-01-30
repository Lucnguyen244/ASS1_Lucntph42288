package com.example.ass1_lucntph42288.DTO;

public class CongViecDTO {

    int id;
    String name;
    String content;
    int status;
    String start;
    String end;
    int user_id;

    public CongViecDTO() {
    }

    public CongViecDTO(String name, String content, int status, String start, String end, int user_id) {
        this.name = name;
        this.content = content;
        this.status = status;
        this.start = start;
        this.end = end;
        this.user_id = user_id;
    }

    public CongViecDTO(int id, String name, String content, int status, String start, String end, int user_id) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.status = status;
        this.start = start;
        this.end = end;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
