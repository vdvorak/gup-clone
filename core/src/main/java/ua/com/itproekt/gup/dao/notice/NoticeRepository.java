package ua.com.itproekt.gup.dao.notice;

import ua.com.itproekt.gup.model.notice.Notice;
import ua.com.itproekt.gup.model.notice.NoticeFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;


public interface NoticeRepository {
        void createNotice(Notice notice);
        Notice findById(String id);
        Notice findNoticeAndUpdate(Notice notice);
        void update(Notice notice);
        int deleteNoticeById(String id);
        boolean noticeExists(String id);
        EntityPage<Notice> findWihOptions(NoticeFilterOptions noticeFilterOptions);
}
