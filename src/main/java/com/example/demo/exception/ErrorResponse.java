package com.example.demo.exception;

import java.time.LocalDateTime;

/*
This is what gets serialized to JSON.

Example response:

{
  "status": 404,
  "message": "User with id 123 not found",
  "timestamp": localtime
}
*/
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime time
) {}