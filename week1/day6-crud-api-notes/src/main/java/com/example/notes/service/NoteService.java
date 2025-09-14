package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    // Modern JDK21: switch expression
    public String getNotesSummary() {
        int count = (int) noteRepository.count();
        return switch (count) {
            case 0 -> "No notes available";
            case 1 -> "There is 1 note";
            default -> "There are %d notes".formatted(count);
        };
    }

    // Modern streams
    public List<String> getAllTitlesUppercase() {
        return noteRepository.findAll()
                .stream()
                .map(Note::getTitle)
                .map(String::toUpperCase)
                .toList();
    }
}
