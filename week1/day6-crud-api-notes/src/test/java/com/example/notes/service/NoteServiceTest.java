package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

class NoteServiceTest {

    private NoteRepository noteRepository;
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        noteRepository = Mockito.mock(NoteRepository.class);
        noteService = new NoteService(noteRepository);
    }

    @Test
    void testCreateNote() {
        Note note = new Note(null, "Title", "Content");
        when(noteRepository.save(note)).thenReturn(new Note(1L, "Title", "Content"));

        Note saved = noteService.createNote(note);
        assertNotNull(saved.getId());
        assertEquals("Title", saved.getTitle());
    }

    @Test
    void testGetAllNotes() {
        when(noteRepository.findAll()).thenReturn(List.of(
                new Note(1L, "Title1", "Content1"),
                new Note(2L, "Title2", "Content2")
        ));

        List<Note> notes = noteService.getAllNotes();
        assertEquals(2, notes.size());
    }

    @Test
    void testGetNoteById() {
        Note note = new Note(1L, "Title", "Content");
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Optional<Note> found = noteService.getNoteById(1L);
        assertTrue(found.isPresent());
        assertEquals("Title", found.get().getTitle());
    }

    @Test
    void testUpdateNote() {
        Note existing = new Note(1L, "Old Title", "Old Content");
        Note update = new Note(null, "New Title", "New Content");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(noteRepository.save(existing)).thenReturn(new Note(1L, "New Title", "New Content"));

        Note updated = noteService.updateNote(1L, update);
        assertEquals("New Title", updated.getTitle());
        assertEquals("New Content", updated.getContent());
    }

    @Test
    void testDeleteNote() {
        noteService.deleteNote(1L);
        verify(noteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetNotesSummary() {
        when(noteRepository.count()).thenReturn(3L);
        String summary = noteService.getNotesSummary();
        assertEquals("There are 3 notes", summary);
    }

    @Test
    void testGetAllTitlesUppercase() {
        when(noteRepository.findAll()).thenReturn(List.of(
                new Note(1L, "Title1", "Content1"),
                new Note(2L, "Title2", "Content2")
        ));
        List<String> titles = noteService.getAllTitlesUppercase();
        assertEquals(List.of("TITLE1", "TITLE2"), titles);
    }
}
