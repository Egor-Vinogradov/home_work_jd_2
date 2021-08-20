package by.it_academy.jd2.messenger.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String from;
    private Date sendDate;
    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", sendDate=" + sendDate +
                ", text='" + text + '\'' +
                '}';
    }
}
