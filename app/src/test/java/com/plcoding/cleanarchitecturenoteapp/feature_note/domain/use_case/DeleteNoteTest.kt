package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteNoteTest {

    private lateinit var deleteNote: DeleteNote
    private lateinit var fakeRepo: FakeNoteRepository
    private lateinit var note: Note

    @Before
    fun setup() {
        fakeRepo = FakeNoteRepository()
        deleteNote = DeleteNote(fakeRepo)

        note = Note(
            title = "title",
            content = "content",
            timestamp = 0,
            color = 0,
            id = 100,
        )

        runBlockingTest {
            fakeRepo.insertNote(note)
        }
    }

    @Test
    fun `delete note`() = runBlockingTest {
        var noteRepoSize = fakeRepo.getNotes().first().size
        assert(noteRepoSize == 1)

        deleteNote(note)

        noteRepoSize = fakeRepo.getNotes().first().size
        assert(noteRepoSize == 0)

    }

}