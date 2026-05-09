# Interactive Presentation Backend - Level 1

Below, you'll find the instructions for getting started with your task. Please read them carefully to avoid unexpected issues. Best of luck!

## Time estimate

Between 2 and 3 hours, plus the time to set up the codebase.

## Mandatory steps before you get started

1. You should already have your project setup from the coding test start page but if not check out [this guide here](https://help.alvalabs.io/en/articles/9028914-how-to-set-up-the-codebase-for-your-coding-test) for more information.
2. Learn [how to get help](https://help.alvalabs.io/en/articles/9028899-how-to-ask-for-help-with-coding-tests) if you run into an issue with your coding test.


## The task

<!--TASK_INSTRUCTIONS_START-->

## System Description

There's a presenter on the stage. They ask the audience a series of questions. One poll per each slide. The people in the audience can vote as long as the current question is displayed.

<img width="550" src="https://user-images.githubusercontent.com/1162212/139849812-de799423-efc1-42f4-8298-e779c3aa17d7.png" />

## Sequence Diagram

The full system flows are visualised by the following [sequence diagram](https://swimlanes.io/u/mmSDwCQdM).


## What you should build

Your task is to build a backend app that fulfills **several endpoints of the Interactive Presentation API)** (See API specification below) and **make the provided API tests pass**.

<details>
<summary>Interactive Presentation API Specification</summary>

```json
{
  "openapi": "3.0.0",
  "info": {
    "title": "Interactive Presentations API",
    "version": "4.0.0"
  },
  "tags": [
    {
      "name": "Presenter",
      "description": "Operations used by the presenting webapp"
    },
    {
      "name": "Common",
      "description": "Reading current poll served to both presenter and the audience"
    },
    {
      "name": "Misc",
      "description": "Miscellaneous"
    }
  ],
  "paths": {
    "/ping": {
      "get": {
        "summary": "Healhcheck to make sure the service is up",
        "responses": {
          "200": {
            "description": "The service is up and running"
          }
        },
        "tags": [
          "Misc"
        ]
      }
    },
    "/presentations": {
      "post": {
        "summary": "Creates a new presentation",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/Presentation"
              }
            }
          }
        },
        "responses": {
          "201": {
            "description": "Presentation created.",
            "content": {
              "application/json": {
                "schema": {
                  "type": "object",
                  "properties": {
                    "presentation_id": {
                      "type": "string",
                      "format": "uuid",
                      "example": "123e4567-e89b-12d3-a456-426614174000"
                    }
                  }
                }
              }
            }
          },
          "400": {
            "description": "Mandatory body parameters missing or have incorrect type."
          },
          "405": {
            "description": "Specified HTTP method not allowed."
          },
          "415": {
            "description": "Specified content type not allowed."
          }
        },
        "tags": [
          "Presenter"
        ]
      }
    },
    "/presentations/{presentation_id}/polls/current": {
      "parameters": [
        {
          "in": "path",
          "name": "presentation_id",
          "schema": {
            "type": "string",
            "format": "uuid",
            "example": "123e4567-e89b-12d3-a456-426614174000"
          },
          "required": true
        }
      ],
      "get": {
        "summary": "Reading currently presented poll for a given presentation",
        "responses": {
          "200": {
            "description": "Returning `description`, `poll_id` and `options`, mainly for the voter to vote",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/Poll"
                }
              }
            }
          },
          "409": {
            "description": "There are no polls currently displayed"
          }
        },
        "tags": [
          "Common"
        ]
      },
      "put": {
        "summary": "Presenting the next poll",
        "responses": {
          "200": {
            "description": "The presentation successfully switched to the next slide. Responding with the poll content.",
            "content": {
              "application/json": {
                "schema": {
                  "allOf": [
                    {
                      "$ref": "#/components/schemas/Poll"
                    }
                  ]
                }
              }
            }
          },
          "404": {
            "description": "No presentation found."
          },
          "409": {
            "description": "The presentation ran out of polls."
          }
        },
        "tags": [
          "Presenter"
        ]
      }
    }
  },
  "components": {
    "schemas": {
      "Presentation": {
        "type": "object",
        "properties": {
          "current_poll_index": {
            "type": "integer",
            "readOnly": true
          },
          "polls": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Poll"
            }
          }
        },
        "required": [
          "current_poll_index",
          "polls"
        ]
      },
      "Option": {
        "type": "object",
        "properties": {
          "key": {
            "type": "string",
            "example": "A"
          },
          "value": {
            "type": "string",
            "example": "Argentina"
          }
        },
        "required": [
          "key",
          "value"
        ]
      },
      "Poll": {
        "properties": {
          "poll_id": {
            "type": "string",
            "format": "uuid",
            "example": "123e4567-e89b-12d3-a456-426614174000",
            "readOnly": true
          },
          "question": {
            "type": "string",
            "example": "Which of the countries would you like to visit the most?"
          },
          "options": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Option"
            }
          }
        },
        "required": [
          "poll_id",
          "options",
          "question"
        ]
      }
    }
  }
}
```
</details>

Please **only implement the following endpoints**:

1. `GET /ping`
2. `POST /presentations`
3. `PUT /presentations/{presentation_id}/polls/current`
4. `GET /presentations/{presentation_id}/polls/current`

<!--TASK_INSTRUCTIONS_END-->
### Solution expectations

- Do your best to make the [provided E2E tests](cypress/e2e/backend.cy.js) pass. Check out [this tutorial](https://help.alvalabs.io/en/articles/9028831-how-to-work-with-cypress) to learn how to execute these tests and analyze the results.

## When you are done

1. [Create a new Pull Request](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request) from the branch where you've committed your solution to the default branch of this repository. **Please do not merge the created Pull Request**.
2. Go to your application in [Alva Labs](https://app.alvalabs.io) and submit your test.

---

Authored by [Alva Labs](https://www.alvalabs.io/).
