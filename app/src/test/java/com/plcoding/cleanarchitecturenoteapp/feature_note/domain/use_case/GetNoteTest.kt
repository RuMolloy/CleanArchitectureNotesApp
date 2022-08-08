package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNoteTest {

    private lateinit var getNote: GetNote
    private lateinit var fakeRepo: FakeNoteRepository

    @Before
    fun setup() {
        val note1 = Note(
            title = "title",
            content = "content",
            timestamp = 0,
            color = 0,
            id = 100,
        )

        val note2 = Note(
            title = "title",
            content = "content",
            timestamp = 0,
            color = 0,
            id = 200,
        )

        fakeRepo = FakeNoteRepository()
        runBlocking {
            fakeRepo.insertNote(note1)
            fakeRepo.insertNote(note2)
        }
        getNote = GetNote(fakeRepo)
    }

    @Test
    fun `get note by id`() {
        runBlockingTest {
            val retrievedNote1 = getNote(100)
            assertEquals(retrievedNote1!!.id, 100)

            val retrievedNote2 = getNote(200)
            assertEquals(retrievedNote2!!.id, 200)
        }
    }

}