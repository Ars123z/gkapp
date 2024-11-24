package com.example.gkapp.data

data class Question(
    val body: String,
    val answer: String,
    val options: List<String>,
)

val questions = listOf(
    Question(
        "What is the capital of France?",
        "Paris",
        listOf("London", "Berlin", "Paris")
    ),
    Question("What is the capital of India?",
        "New Delhi",
        listOf("Mumbai", "Kolkata", "New Delhi")
    ),
    Question(
        "What is the capital of Australia?",
        "Canberra",
        listOf("Sydney", "Melbourne", "Canberra")
    ),
    Question(
        "What is the capital of Japan?",
        "Tokyo",
        listOf("Beijing", "Shanghai", "Tokyo")
    ),
    Question(
        "What is the capital of Brazil?",
        "Brasília",
        listOf("São Paulo", "Rio de Janeiro", "Brasília")
    ),
    Question(
        "What is the capital of Italy?",
        "Rome",
        listOf("Milan", "Naples", "Rome")
    ),
    Question(
        "What is the capital of Switzerland?",
        "Bern",
        listOf("Zurich", "Geneva", "Bern")
    ),
    Question(
        "What is the capital of Germany?",
        "Berlin",
        listOf("Munich", "Frankfurt", "Berlin")
    ),
    Question(
        "What is the capital of Russia?",
        "Moscow",
        listOf("Saint Petersburg", "Novosibirsk", "Moscow")
    ),
    Question(
        "What is the capital of China?",
        "Beijing",
        listOf("Shanghai", "Guangzhou", "Beijing")
    ),
    Question(
        "What is the capital of Canada?",
        "Ottawa",
        listOf("Vancouver", "Toronto", "Ottawa")
    ),
    Question(
        "What is the capital of United Kingdom?",
        "London",
        listOf("Manchester", "Birmingham", "London")
    ),
    Question(
        "What is the capital of United States?",
        "Washington, D.C.",
        listOf("New York", "Los Angeles", "Washington, D.C.")
    ),
    Question(
        "What is the capital of France?",
        "Paris",
        listOf("London", "Berlin", "Paris")
    ),
    Question(
        "What is the capital of Pakistan?",
        "New Delhi",
        listOf("Islamabad", "Faisalabad", "Karachi")
    ),
    Question(
        "What is the capital of Bangladesh?",
        "Dhaka",
        listOf("Chittagong", "Dhaka", "Kolkata")
    ),
)