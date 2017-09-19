package ua.com.gup.repository.dao.notice;

import ua.com.gup.model.notice.Notice;
import ua.com.gup.model.notice.NoticeFilterOptions;
import ua.com.gup.util.EntityPage;


public interface NoticeRepository {
        void createNotice(Notice notice);
        Notice findById(String id);
        Notice findNoticeAndUpdate(Notice notice);
        void update(Notice notice);
        int deleteNoticeById(String id);
        boolean noticeExists(String id);
        EntityPage<Notice> findWihOptions(NoticeFilterOptions noticeFilterOptions);
}
