    package deu.cse.team.observer;

    public class StudentObserver implements Observer{
            private String notice;
            private NoticeData noticeData;

            public StudentObserver(NoticeData noticedata) {
                    this.noticeData = noticedata;
                    noticedata.registerObserver(this);
            }

            public void update(String notice,String noticeData) {

            }
    }