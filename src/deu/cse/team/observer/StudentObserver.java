package deu.cse.team.observer;

public class StudentObserver implements Observer {

    private NoticeData noticeData;
    private String notice;

    public StudentObserver(NoticeData noticeData) {
        this.noticeData = noticeData;
        noticeData.registerObserver(this);
    }

    public void update(String notice) {
        this.notice = notice;
        display();
    }

    public String display() {
        return notice;

    }
}
