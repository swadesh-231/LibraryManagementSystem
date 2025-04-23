package com.project.librarymanagement.controller;

import com.project.librarymanagement.model.IssueRecord;
import com.project.librarymanagement.service.IssueRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issueRecords")
public class IssueRecordController {
    private final IssueRecordService issueRecordService;
    public IssueRecordController(IssueRecordService issueRecordService) {
        this.issueRecordService = issueRecordService;
    }
    @PostMapping("/issue/{bookId}")
    public ResponseEntity<IssueRecord> issueBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
    }

    @PostMapping("/return/{issueRecordId}")
    public ResponseEntity<IssueRecord> returnBook(@PathVariable Long issueRecordId) {
        return ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
    }
}
