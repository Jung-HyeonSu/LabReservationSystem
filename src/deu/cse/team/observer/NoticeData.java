package deu.cse.team.observer;

import java.util.*;

public class NoticeData implements Subject {

    private List<Observer> observers;
    private String notice;

    public NoticeData() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(notice);
        }
    }

    public void noticeChanged() {
        notifyObservers();
    }

    public void setNotice(String notice) {
        this.notice = notice;
        noticeChanged();
    }

    public String getNotice() {
        return notice;
    }
}
