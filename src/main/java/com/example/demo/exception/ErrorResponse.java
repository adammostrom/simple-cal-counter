package com.example.demo.exception;


/*
This is what gets serialized to JSON.

Example response:

{
  "status": 404,
  "message": "User with id 123 not found"
}


*/
public record ErrorResponse(
        int status,
        String message
) {}