package com.anushka.roomdemo.db

class SubscriberRepository(private val dao: SubscriberDAO) {
    val subscriber = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber): Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber): Int {
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber): Int {
        return dao.deleteSubscribe(subscriber)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}