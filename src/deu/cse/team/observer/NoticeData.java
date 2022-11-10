    package deu.cse.team.observer;

    import java.util.*;

    public class NoticeData implements Subject {
            private List<Observer> observers;
            private String notice;
            private String noticeData;

            public NoticeData() {
                    observers = new ArrayList<Observer>();
            }

            public void registerObserver(Observer o) {
                    observers.add(o);
            }

            public void removeObserver(Observer o) {
                    observers.remove(o);
            }

            public void notifyObserver() {
                    for (Observer observer : observers) {
                            observer.update(notice,noticeData);
                    }
            }

            public void noticeChanged() {
                    notifyObserver();
            }

            public void setNotice(String notice,String noticeData) {
                    this.notice = notice;
                    this.noticeData = noticeData;
                    noticeChanged();
            }

            public String getNotice() {
                    return notice;
            }

            public String setNotice() {
                    return notice;
            }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }
