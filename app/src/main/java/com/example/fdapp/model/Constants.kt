package com.example.fdapp.model

object Constants {

    fun getTagData():ArrayList<Tag>{
        // create an arraylist of type employee class
        val tagList=ArrayList<Tag>()
        val tag1=Tag("Все меню",true)
        tagList.add(tag1)
        val tag2=Tag("Салаты",false)
        tagList.add(tag2)
        val tag3=Tag("С рисом",false)
        tagList.add(tag3)
        val tag4=Tag("С рыбой",false)
        tagList.add(tag4)

        return  tagList
    }

}