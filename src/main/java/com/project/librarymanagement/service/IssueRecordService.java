package com.project.librarymanagement.service;

import com.project.librarymanagement.model.IssueRecord;

public interface IssueRecordService {
    IssueRecord issueTheBook(Long bookId);
    IssueRecord returnTheBook(Long issueRecordId);
}
