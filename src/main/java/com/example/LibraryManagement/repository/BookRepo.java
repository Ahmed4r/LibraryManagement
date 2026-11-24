package com.example.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.LibraryManagement.dto.BookSummary;
import com.example.LibraryManagement.entity.Book;

import java.lang.annotation.Native;
import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByPublicationYear(Integer publicationYear);

    List<Book> findByIsbn(String isbn);

    List<Book> findByPublicationYearGreaterThan(Integer year);

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByTitleAndPublicationYear(
            String title, Integer publicationYear);

    // Find books published between two years.
    List<Book> findByPublicationYearBetween(Integer startYear,
            Integer endYear);

    // Find books whose titles start with a specific word.
    List<Book> findByTitleStartingWith(String startingWord);

    // Find books by title, ignoring case.
    List<Book> findByTitleIgnoreCase(String bookTitle);

    // Find books published before a specific year.
    List<Book> findByPublicationYearLessThan(Integer year);

    // البحث عن الكتب حسب جنسية المؤلف nationality.
    List<Book> findByAuthor_Nationality(String nationality);

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b WHERE b.publicationYear > :year")
    List<Book> searchBooksAfterYear(@Param("year") Integer year);

    @Query("SELECT b FROM Book b WHERE b.author.nationality = :nationality")
    List<Book> searchByAuthorNationality(
            @Param("nationality") String nationality);

    // اكتب Query تجيب كل الكتب اللي:
    // - اتنشرت بعد سنة معينة year.
    // - جنسية المؤلف بتاعها nationality.
    // المطلوب استخدام AND واثنين @Param.
    @Query("SELECT b FROM Book b WHERE b.publicationYear >:year AND b.author.nationality =: author")
    List<Book> searchByYearAndNationality(@Param("year") Integer year, @Param("nationality") String nationality);

    @Query("""
            SELECT b FROM Book b
            JOIN b.categories c
            WHERE c.name = :name
            """)
    List<Book> searchByCategoryName(@Param("name") String name);

    // Your exercise
    // Inside BookRepo.java, write a Native SQL query that returns all books
    // published between two years.

    // Use:
    // - @Query
    // - nativeQuery = true
    // - BETWEEN
    // - Two named parameters: startYear and endYear.
    @Query(value = "SELECT * FROM book WHERE publication_year BETWEEN :startYear AND :endYear ", nativeQuery = true)
    List<Book> getAllBooksBetweenYears(@Param("startYear") Long startYear, @Param("end") Long endYear);

    // Write a native SQL query that returns all books belonging to an author with a
    // specific nationality.
    @Query(value = """

            SELECT b.*
              FROM book b
              JOIN author a ON b.author_id = a.id
              WHERE a.nationality = :nationality"""

            , nativeQuery = true)
    List<Book> getAllBooksBelongToAuthorNationality(
            @Param("nationality") String nationality);

    // projections
    @Query("""
            SELECT b.title AS title,
                   b.publicationYear AS publicationYear
            FROM Book b
            """)
    List<BookSummary> findAllBookSummaries();

    @Query("""
            SELECT DISTINCT b FROM Book b
            LEFT JOIN FETCH b.author
            LEFT JOIN FETCH b.categories
            """)
    List<Book> findAllWithDetails();

}
