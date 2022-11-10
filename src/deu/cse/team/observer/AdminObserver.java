    package deu.cse.team.observer;

    public class AdminObserver implements Observer{
            private NoticeData noticeData;
    private String notice;
            public AdminObserver(NoticeData noticeData) {
                    this.noticeData = noticeData;
                    noticeData.registerObserver(this);
            }

            public void update(String notice, String noticeData) {

            }
    }
