package com.huawei.todolist

import android.app.Application
import androidx.lifecycle.LiveData
import com.huawei.todolist.utils.subscribeOnBackground

class NoteRepository(application: Application) {

    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    private val database = NoteDatabase.getInstance(application)

    init {
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
//        Single.just(noteDao.insert(note))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()
        subscribeOnBackground {
            noteDao.insert(note)
        }
    }

    fun update(note: Note) {
        subscribeOnBackground {
            noteDao.update(note)
        }
    }

    fun delete(note: Note) {
        subscribeOnBackground {
            noteDao.delete(note)
        }
    }

    fun deleteAllNotes() {
        subscribeOnBackground {
            noteDao.deleteAllNotes()
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }



}