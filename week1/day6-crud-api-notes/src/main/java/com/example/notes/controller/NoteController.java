package com.example.notes.controller;

import com.example.notes.dto.NoteDTO;
import com.example.notes.entity.Note;
import com.example.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // CREATE
    @PostMapping
    public NoteDTO createNote(@RequestBody NoteDTO noteDTO) {
        Note saved = noteService.createNote(new Note(null, noteDTO.title(), noteDTO.content()));
        return new NoteDTO(saved.getId(), saved.getTitle(), saved.getContent());
    }

    // READ ALL
    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes()
                .stream()
                .map(note -> new NoteDTO(note.getId(), note.getTitle(), note.getContent()))
                .toList();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(note -> ResponseEntity.ok(new NoteDTO(note.getId(), note.getTitle(), note.getContent())))
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        Note updated = noteService.updateNote(id, new Note(id, noteDTO.title(), noteDTO.content()));
        return ResponseEntity.ok(new NoteDTO(updated.getId(), updated.getTitle(), updated.getContent()));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    // SUMMARY using switch expression (JDK21)
    @GetMapping("/summary")
    public String getSummary() {
        return noteService.getNotesSummary();
    }

    // GET all titles in uppercase (using Streams JDK21)
    @GetMapping("/titles/uppercase")
    public List<String> getAllTitlesUppercase() {
        return noteService.getAllTitlesUppercase();
    }
}
