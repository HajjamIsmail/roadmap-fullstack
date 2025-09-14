package com.example.notes.controller;

import com.example.notes.dto.NoteDTO;
import com.example.notes.entity.Note;
import com.example.notes.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateNote() throws Exception {
        NoteDTO dto = new NoteDTO(null, "Title", "Content");
        Note saved = new Note(1L, "Title", "Content");

        when(noteService.createNote(new Note(null, dto.title(), dto.content()))).thenReturn(saved);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void testGetAllNotes() throws Exception {
        when(noteService.getAllNotes()).thenReturn(List.of(
                new Note(1L, "Title1", "Content1"),
                new Note(2L, "Title2", "Content2")
        ));

        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetNoteById() throws Exception {
        Note note = new Note(1L, "Title", "Content");
        when(noteService.getNoteById(1L)).thenReturn(Optional.of(note));

        mockMvc.perform(get("/api/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void testUpdateNote() throws Exception {
        NoteDTO dto = new NoteDTO(null, "Updated Title", "Updated Content");
        Note updated = new Note(1L, "Updated Title", "Updated Content");

        when(noteService.updateNote(eq(1L), any(Note.class))).thenReturn(updated);

        mockMvc.perform(put("/api/notes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteNote() throws Exception {
        doNothing().when(noteService).deleteNote(1L);

        mockMvc.perform(delete("/api/notes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetSummary() throws Exception {
        when(noteService.getNotesSummary()).thenReturn("There are 2 notes");

        mockMvc.perform(get("/api/notes/summary"))
                .andExpect(status().isOk())
                .andExpect(content().string("There are 2 notes"));
    }

    @Test
    void testGetAllTitlesUppercase() throws Exception {
        when(noteService.getAllTitlesUppercase()).thenReturn(List.of("TITLE1", "TITLE2"));

        mockMvc.perform(get("/api/notes/titles/uppercase"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("TITLE1"));
    }
}
