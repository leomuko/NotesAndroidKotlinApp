package com.example.notesapp.helpers

import com.example.notesapp.models.NotesData

object sampleData{

    val NOTES = ArrayList<NotesData>()

    private var COUNT = 3

    private var dummy_notes_detail = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,"

    init {
        //Adding some sample items
        var newNote1 = NotesData()
        newNote1.id = 1
        newNote1.title = "My Week 1"
        newNote1.noteContent = dummy_notes_detail
        NOTES.add(newNote1)

        var newNote2 = NotesData()
        newNote2.id = 2
        newNote2.title = "My Week 2"
        newNote2.noteContent = dummy_notes_detail
        NOTES.add(newNote2)

        var newNote3 = NotesData()
        newNote3.id = 3
        newNote3.title = "My Week 3"
        newNote3.noteContent = dummy_notes_detail
        NOTES.add(newNote3)
}
    fun addNote(item: NotesData){
        item.id = COUNT +1
        NOTES.add(item)
        COUNT += 1
    }
    fun deleteNote(id: Int?){
        var itemToRemove: NotesData ? = null
        for(i in NOTES.indices){
            if(NOTES[i].id == id){
                 itemToRemove = NOTES[i]
            }
        }
        if (itemToRemove != null){
            NOTES.remove(itemToRemove)
        }

    }
    fun editNote(note: NotesData){
        for(i in NOTES.indices){
            if (NOTES[i].id == note.id){
                NOTES[i].noteContent = note.noteContent
               NOTES[i].title =  note.title
            }
        }
    }



}