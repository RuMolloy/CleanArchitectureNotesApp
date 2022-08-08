package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var getNote: GetNote
    private lateinit var fakeRepo: FakeNoteRepository

    @Before
    fun setup() {
        fakeRepo = FakeNoteRepository()
        addNote = AddNote(fakeRepo)
    }

    @Test
    fun `insert note success`() = runBlockingTest {
        val note = Note(
            title = "title",
            content = "content",
            timestamp = 0,
            color = 0,
            id = 100,
        )

        addNote(note)
        val noteRetrieved = fakeRepo.getNoteById(note.id!!)

        assertThat(noteRetrieved!!.id == 100)
    }

    @Test
    fun `insert note fail, empty note title`() {
        val note = Note(
            title = "",
            content = "content",
            timestamp = 0,
            color = 0
        )
        val exception = Assert.assertThrows(
            InvalidNoteException::class.java) {
            runBlockingTest { addNote(note) }
        }
        assertEquals(exception.message, "The title of the note cannot be empty!")
    }

    @Test
    fun `insert note fail, empty note content`() {
        val note = Note(
            title = "title",
            content = "",
            timestamp = 0,
            color = 0
        )

        val exception = Assert.assertThrows(
            InvalidNoteException::class.java) {
            runBlockingTest { addNote(note) }
        }
        assertEquals(exception.message, "The content of the note cannot be empty!")
    }

}