package com.zhumingbei.babybei_server.bean;

public class HelpInfo {
    private int id;
    private int type;
    private String question;
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "HelpInfo{" +
                "id=" + id +
                ", type=" + type +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
