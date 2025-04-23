package com.project.librarymanagement.service.impl;

import com.project.librarymanagement.exception.BookNotFoundException;
import com.project.librarymanagement.model.Book;
import com.project.librarymanagement.model.IssueRecord;
import com.project.librarymanagement.model.User;
import com.project.librarymanagement.repository.BookRepository;
import com.project.librarymanagement.repository.IssueRecordRepository;
import com.project.librarymanagement.repository.UserRepository;
import com.project.librarymanagement.service.IssueRecordService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordServiceImpl implements IssueRecordService {
    private final IssueRecordRepository issueRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public IssueRecordServiceImpl(IssueRecordRepository issueRecordRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.issueRecordRepository = issueRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IssueRecord issueTheBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book", "id", bookId));
        if (book.getQuantity() <= 0 || !book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBook(book);
        issueRecord.setUser(user);
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(10));
        issueRecord.setIsReturned(false);

        book.setQuantity(book.getQuantity()-1);
        if (book.getQuantity() == 0){
            book.setAvailable(false);
        }
        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);

    }

    @Override
    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        if (issueRecord.getIsReturned()) {
            throw new RuntimeException("Book has already been returned");
        }
        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        book.setAvailable(true);
        bookRepository.save(book);
        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);
        return issueRecordRepository.save(issueRecord);
    }
}
