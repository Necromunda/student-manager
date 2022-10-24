# Project was made with IntelliJ by Johannes Rantapää

---

## Add students, courses and courses to students with the GUI at `localhost:8080`

## All data will be saved in project root in `logs` folder as `students.json` & `courses.json` files.

## Messing with the JSON syntax will result in read/write of those files not working correctly. To fix it, either restore the syntax or delete the corrupted file.

### Example syntax of `students.json`
```
[
  {
    "Courses": [
      {
        "Course teacher": "Onni Opettaja",
        "Id": 1,
        "Course name": "Computer science",
        "Course class": "1B255"
      }
    ],
    "First name": "Kalle",
    "Grade": 5,
    "Id": 1,
    "Last name": "Koodaaja"
  },
  {
    "Courses": "[]",
    "First name": "Erkki",
    "Grade": 3,
    "Id": 2,
    "Last name": "Esimerkki"
  }
]
```

### Example syntax of `courses.json`
```
[
  {
    "Course teacher": "Onni Opettaja",
    "Id": 1,
    "Course name": "Computer science",
    "Course class": "1B255"
  }
]
```