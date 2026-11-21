package com.example.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LibraryManagement.dto.AuthorRequest;
import com.example.LibraryManagement.dto.AuthorResponse;
import com.example.LibraryManagement.entity.Author;
import com.example.LibraryManagement.exception.AuthorNotFoundException;
import com.example.LibraryManagement.mapper.AuthorMapper;
import com.example.LibraryManagement.repository.AuthorRepo;


@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public AuthorResponse addAuthor(AuthorRequest request) {
        Author entity = AuthorMapper.toEntity(request);
        Author savedEntity = authorRepo.save(entity);
        return AuthorMapper.toResponse(savedEntity);
    }

    // get
    @Transactional(readOnly = true)
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(author -> AuthorMapper.toResponse(author))
                .toList();
    }

    // delete
    @Transactional
    public void removeAuthor(Long authorId) {
        if (!authorRepo.existsById(authorId)) {
            throw new AuthorNotFoundException(authorId);
        }

        authorRepo.deleteById(authorId);
    }

    // update
    @Transactional
    public AuthorResponse updateAuthor(Long id, AuthorRequest request) {

        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(request.getName());
        author.setNationality(request.getNationality());

        Author updatedAuthor = authorRepo.save(author);

        return AuthorMapper.toResponse(updatedAuthor);
    }

}
