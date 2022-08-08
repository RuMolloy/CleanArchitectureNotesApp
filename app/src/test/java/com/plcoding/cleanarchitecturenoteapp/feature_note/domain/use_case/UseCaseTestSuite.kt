package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    AddNoteTest::class,
    DeleteNoteTest::class,
    GetNotesTest::class,
    GetNoteTest::class
)
class UseCaseTestSuite